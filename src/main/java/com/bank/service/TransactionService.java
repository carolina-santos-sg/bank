package com.bank.service;

import com.bank.dto.TransactionDto;
import com.bank.enums.TransactionEnums;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.TransactionsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
public class TransactionService {
    private  final TransactionsRepository transactionsRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountService bankAccountService;

    public TransactionService(TransactionsRepository transactionsRepository, BankAccountRepository bankAccountRepository, BankAccountService bankAccountService) {
        this.transactionsRepository = transactionsRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountService = bankAccountService;
    }

    public ResponseEntity<Object> listTransactions(){       //listar transferências
        return ResponseEntity.ok(this.transactionsRepository.findAll());
    }

    public Object deposit(TransactionDto transactionDto){
        //precisa ter conta de origem e conta destino
        if (Objects.isNull(transactionDto.getTargetAccountId()) && Objects.isNull(transactionDto.getSourceAccountId())){
            throw new RuntimeException("É preciso informar um bankAccount!");
        }

        //valor não pode ser nulo
        if (Objects.isNull(transactionDto.getTransactionValue())){
            throw new RuntimeException("É preciso informar o valor da operação!");
        }

        //valor da operação precisa ser maior do que 0
        if (transactionDto.getTransactionValue().compareTo(new BigDecimal("0")) <= 0){
            throw new RuntimeException("Valor da operação inválido!");
        }

        //settando dados da transação
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionDto.getTransactionValue());
        transaction.setTargetAccountId(this.bankAccountService.findById(transactionDto.getTargetAccountId()));
        transaction.setTransactionType(TransactionEnums.DEPOSIT.getTransactionEnums());
        transaction.setDateTransaction(new Date());

        //validação da conta destino do depósito
        BankAccount targetAccount  = this.bankAccountRepository.findById(transactionDto.getTargetAccountId()).orElseThrow(()-> new RuntimeException("Account não encontrada!"));

        //operação do depósito
        targetAccount.setBalance(targetAccount.getBalance().add(transactionDto.getTransactionValue()));

        //registrando a operação
        this.transactionsRepository.save(transaction);

        //retornando o registro do bankAccount atualizado
        return ResponseEntity.ok(this.bankAccountRepository.save(targetAccount));
    }

    public ResponseEntity<Object> withdraw(TransactionDto transactionDto, TransactionEnums transactionEnums){
        validations(transactionDto, transactionEnums.toString());
        return ResponseEntity.ok("");
    }

    public ResponseEntity<Object> transfer(TransactionDto transactionDto, TransactionEnums transactionEnums){
        validations(transactionDto, transactionEnums.toString());

        //operações na sourceAccount
        BankAccount sourceAccount  = this.bankAccountRepository.findById(transactionDto.getSourceAccountId()).orElseThrow(()-> new RuntimeException("SourceAccount não encontrada!"));
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionDto.getTransactionValue()));
        this.bankAccountRepository.save(sourceAccount);

        //operações na targetAccount
        BankAccount targetAccount  = this.bankAccountRepository.findById(transactionDto.getTargetAccountId()).orElseThrow(()-> new RuntimeException("TargetAccount não encontrada!"));
        targetAccount.setBalance(targetAccount.getBalance().add(transactionDto.getTransactionValue()));
        this.bankAccountRepository.save(targetAccount);

        //registrando a transação
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionDto.getTransactionValue());
        transaction.setSourceAccountId(this.bankAccountService.findById(transactionDto.getSourceAccountId()));
        transaction.setTargetAccountId(this.bankAccountService.findById(transactionDto.getTargetAccountId()));
        transaction.setTransactionType(TransactionEnums.TRANSFER.getTransactionEnums());
        transaction.setDateTransaction(new Date());

        return ResponseEntity.ok(this.transactionsRepository.save(transaction));

    }

    public void validations(TransactionDto transactionDto, String transactionEnums){
        //precisa ter conta de origem e conta destino
        if (Objects.isNull(transactionDto.getTargetAccountId()) && Objects.isNull(transactionDto.getSourceAccountId())){
            throw new RuntimeException("É preciso informar um bankAccount!");
        }

        //sourceAccount != targetAccount
        if (transactionDto.getTargetAccountId() == transactionDto.getSourceAccountId()){
            throw new RuntimeException("As contas de origem e de destino devem ser diferentes!");
        }

        //valor não pode ser nulo
        if (Objects.isNull(transactionDto.getTransactionValue())){
            throw new RuntimeException("É preciso informar o valor da operação!");
        }

        //valor da operação precisa ser maior do que 0
        if (transactionDto.getTransactionValue().compareTo(new BigDecimal("0")) <= 0){
            throw new RuntimeException("Valor da operação inválido!");
        }

        //limite de transação diário
        if (this.transactionsRepository.countTransactionByDateTransaction(transactionDto.getSourceAccountId(),transactionEnums) > 0
            && transactionEnums.compareTo("WITHDRAW") >= 0){
            throw new RuntimeException("Limite diário atingido para esse tipo de operação!");
        } else if (this.transactionsRepository.countTransactionByDateTransaction(transactionDto.getSourceAccountId(),transactionEnums) > 0
                && transactionEnums.compareTo("TRANSFER") >= 0) {
            throw new RuntimeException("Limite diário atingido para esse tipo de operação!");
        }

        //valor limite da transação
        BigDecimal maxLimit = new BigDecimal("0");
        BigDecimal zero = new BigDecimal("0");

            //WITHDRAW
        if (this.transactionsRepository.totalValueTransactionByTypeAndSourceAccount(transactionDto.getSourceAccountId(), TransactionEnums.WITHDRAW.getTransactionEnums()).compareTo(zero) > 0){
            maxLimit = maxLimit.add(this.transactionsRepository.totalValueTransactionByTypeAndSourceAccount(transactionDto.getSourceAccountId(), "WITHDRAW"));
        }
            //DEPOSIT
        if (this.transactionsRepository.totalValueTransactionByTypeAndTargetAccount(transactionDto.getTargetAccountId(), TransactionEnums.DEPOSIT.toString()).compareTo(BigDecimal.ZERO) > 0){
            maxLimit = maxLimit.subtract(this.transactionsRepository.totalValueTransactionByTypeAndTargetAccount(transactionDto.getTargetAccountId(), TransactionEnums.DEPOSIT.toString()));
        }
            //TRANSFER
        Transaction transaction = this.transactionsRepository.dataTransaction(TransactionEnums.TRANSFER.toString());

        if (Objects.nonNull(transaction) && transaction.getSourceAccountId().equals(transactionDto.getSourceAccountId())){
            maxLimit = maxLimit.add(this.transactionsRepository.totalValueTransactionByTypeAndSourceAccount(transactionDto.getSourceAccountId(), TransactionEnums.TRANSFER.toString()));
        }
//        if (this.transactionsRepository.dataTransaction(TransactionEnums.TRANSFER.toString()).getSourceAccountId().equals(transactionDto.getSourceAccountId())){
//            maxLimit = maxLimit.add(this.transactionsRepository.totalValueTransactionByTypeAndSourceAccount(transactionDto.getSourceAccountId(), TransactionEnums.TRANSFER.toString()));
//        }
        if (this.transactionsRepository.dataTransaction(TransactionEnums.TRANSFER.toString()).getSourceAccountId().equals(transactionDto.getTargetAccountId())){
            maxLimit = maxLimit.add(this.transactionsRepository.totalValueTransactionByTypeAndTargetAccount(transactionDto.getTargetAccountId(), TransactionEnums.TRANSFER.toString()));
        }

            //validando maxLimit
        if (transactionDto.getTransactionValue().compareTo(maxLimit.multiply(BigDecimal.valueOf(0.3))) > 0){
            throw new RuntimeException("O valor da transação extrapola o limite diário!");
        }
    }

}