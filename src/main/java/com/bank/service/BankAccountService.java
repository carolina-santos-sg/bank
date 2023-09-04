package com.bank.service;

import com.bank.dto.BankAccountDto;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
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


    public BankAccountService(BankAccountRepository bankAccountRepository, AssociateService associateService, BankAgencyService bankAgencyService) {
        this.bankAccountRepository = bankAccountRepository;
        this.associateService = associateService;
        this.bankAgencyService = bankAgencyService;
    }

    //@Transactional
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccountDto bankAccountDto){
        if (Objects.isNull(bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Agency Number vazio!");
        }


        if (this.bankAccountRepository.countByNumberAccountAndNumberAgency(bankAccountDto.getNumberAccount(), bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Account já registrada!");
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumberAccount(bankAccountDto.getNumberAccount());
        bankAccount.setBalance(bankAccountDto.getBalance());
        bankAccount.setTransactionLimit(bankAccountDto.getTransactionLimit());
        bankAccount.setAgencyId(this.bankAgencyService.findAgencyById(bankAccount.getId()));
        bankAccount.setAssociateId(this.associateService.findAssociateById(bankAccount.getAssociateId().getId()));

        return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
    }

    public List<BankAccount> listAccount(){
        return bankAccountRepository.findAll();
    }
}
