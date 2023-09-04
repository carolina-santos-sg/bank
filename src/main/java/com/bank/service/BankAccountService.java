package com.bank.service;

import com.bank.dto.BankAccountDto;
import com.bank.model.Associate;
import com.bank.model.BankAccount;
import com.bank.model.BankAgency;
import com.bank.repository.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class BankAccountService {
    final BankAccountRepository bankAccountRepository;
//    final BankAccountDto bankAccountDto;
    final AssociateService associateService;
    private BankAgency bankAgencyService;


    public BankAccountService(BankAccountRepository bankAccountRepository,  AssociateService associateService) {
        this.bankAccountRepository = bankAccountRepository;
//        this.bankAccountDto = bankAccountDto;
        this.associateService = associateService;
    }

    //@Transactional
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccountDto bankAccountDto){
        if (Objects.isNull(bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Number vazio!");
        }


        if (this.bankAccountRepository.countByNumberAccountAndNumberAgency(bankAccountDto.getNumberAccount(), bankAccountDto.getAgencyId())){
            throw new RuntimeException("Bank Account já registrada!");
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumberAccount(bankAccountDto.getNumberAccount());
        bankAccount.setBalance(bankAccountDto.getBalance());
        bankAccount.setTransactionLimit(bankAccountDto.getTransactionLimit());
//        bankAccount.setAgencyId(bankAgencyService.findAgencyById());


        return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
    }

    public List<BankAccount> listAccount(){
        return bankAccountRepository.findAll();
    }
}
