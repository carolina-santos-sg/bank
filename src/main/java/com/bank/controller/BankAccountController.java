package com.bank.controller;

import com.bank.dto.BankAccountDto;
import com.bank.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankAccountController {
    private final BankAccountService bankAccountService;
//    private final BankAccountDto bankAccountDto;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
//        this.bankAccountDto = bankAccountDto;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(@RequestBody BankAccountDto bankAccountDto){
        return ResponseEntity.ok(this.bankAccountService.createAccount(bankAccountDto));
    }

    @GetMapping("/listAccount")
    public ResponseEntity<Object> listAccount(){
        return ResponseEntity.ok(this.bankAccountService.listAccount());
    }

}