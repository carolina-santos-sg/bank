package com.bank.service;

import com.bank.dto.BankAccountDto;
import com.bank.dto.BankAgencyDto;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.BankAgencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class BankAccountService {
    final BankAccountRepository bankAccountRepository;
    final AssociateService associateService;
    final BankAgencyService bankAgencyService;
    BankAgencyDto bankAgencyDto;
    final BankAgencyRepository bankAgencyRepository;


    public BankAccountService(BankAccountRepository bankAccountRepository, AssociateService associateService, BankAgencyService bankAgencyService, BankAgencyRepository bankAgencyRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.associateService = associateService;
        this.bankAgencyService = bankAgencyService;
        this.bankAgencyRepository = bankAgencyRepository;
    }


    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccountDto bankAccountDto){
        if (Objects.isNull(bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Agency Number vazio!");
        }


        long bankNumber = this.bankAgencyRepository.selectBankNumberByAgencyId(bankAccountDto.getAgencyId());

        if (this.bankAccountRepository.countByAssociateAndBank(bankNumber, bankAccountDto.getAssociateId())){
            throw new RuntimeException("Associado já possui conta no banco!");
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumberAccount(bankAccountDto.getNumberAccount());
        bankAccount.setBalance(bankAccountDto.getBalance());
        bankAccount.setAgencyId(this.bankAgencyService.findAgencyById(bankAccountDto.getAgencyId()));
        bankAccount.setAssociateId(this.associateService.findAssociateById(bankAccountDto.getAssociateId()));

        return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
    }

    public List<BankAccount> listAccount(){
        return bankAccountRepository.findAll();
    }

    public ResponseEntity<Object> updateAccount(long id, BankAccountDto bankAccountDto){
        if (Objects.isNull(id)){
            throw new RuntimeException("É preciso informar um bankAccount!");
        }

        if (this.bankAccountRepository.countByNumberAccountAndNumberAgency(bankAccountDto.getNumberAccount(), bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Account já registrada!");
        }

        bankAccountDto.setBankNumber(bankAgencyDto.getBankNumber());

        if (bankAccountRepository.countByAssociateAndBank(bankAgencyDto.getBankNumber(), bankAccountDto.getAssociateId())){
            throw new RuntimeException("Associado já possui conta no banco!");
        }


        BankAccount bankAccount = this.bankAccountRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Account não encontrada!");
        });

        bankAccount.setNumberAccount(bankAccountDto.getNumberAccount());
        bankAccount.setAgencyId(this.associateService.findAgencyById(bankAccountDto.getAgencyId()));
        bankAccount.setAssociateId(this.associateService.findAssociateById(bankAccountDto.getAssociateId()));

        return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
    }

    public BankAccount findById(long id){
        return this.bankAccountRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Account Id não encontrado!");
        });
    }

}
