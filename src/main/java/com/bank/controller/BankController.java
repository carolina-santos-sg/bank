package com.bank.controller;

import com.bank.model.Bank;
import com.bank.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/registerBank")
    public ResponseEntity<Object> registerBank(@RequestBody Bank bank){
        return ResponseEntity.ok(this.bankService.registerBank(bank));
    }

    @GetMapping("/listBank")
    public ResponseEntity<Object> listBank(){
        return ResponseEntity.ok(this.bankService.listBank());
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<Object> searchBank(long id){
//        return ResponseEntity.ok(this.bankService.searchBank(id));
//    }

}
