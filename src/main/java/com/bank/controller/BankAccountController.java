package com.bank.controller;

import com.bank.dto.BankAccountDto;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    final BankAccountRepository bankAccountRepository;

    public BankAccountController(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository) {
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(@RequestBody BankAccountDto bankAccountDto){
        return ResponseEntity.ok(this.bankAccountService.createAccount(bankAccountDto));
    }

    @GetMapping("/listAccount")
    public ResponseEntity<Object> listAccount(){
        return ResponseEntity.ok(this.bankAccountService.listAccount());
    }

    @DeleteMapping("/deleteAccount")
    public void deleteAccount(@RequestBody BankAccount bankAccount){
        bankAccountRepository.delete(bankAccount);
    }
}