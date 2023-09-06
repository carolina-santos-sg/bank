package com.bank.service;

import com.bank.dto.BankAccountDto;
import com.bank.dto.TransactionDto;
import com.bank.enums.TransactionEnums;
import com.bank.repository.TransactionsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    TransactionsRepository transactionsRepository;
    final BankAccountDto bankAccountRepository;

    public TransactionService(BankAccountDto bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public ResponseEntity<Object> listTransactions(){       //listar transferências
        return ResponseEntity.ok(this.transactionsRepository.findAll());
    }

    public ResponseEntity<Object> deposit(TransactionDto transactionDto, TransactionEnums transactionEnums){

//        BankAccountDto sourceAccountDto = new BankAccountDto();
//        sourceAccountDto = this.bankAccountRepository.findById(transactionDto.getSourceAccountId());
//        BankAccountDto targetAccountDto = this.bankAccountRepository.findById(transactionDto.getTargetAccountId());



        return ResponseEntity.ok("");
    }

    public ResponseEntity<Object> withdraw(TransactionDto transactionDto, TransactionEnums transactionEnums){
        return ResponseEntity.ok("");
    }

    public ResponseEntity<Object> transfer(TransactionDto transactionDto, TransactionEnums transactionEnums){
        return ResponseEntity.ok("");
    }

}