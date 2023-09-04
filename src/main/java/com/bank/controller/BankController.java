package com.bank.controller;

import com.bank.model.Bank;
import com.bank.model.BankAgency;
import com.bank.repository.BankRepository;
import com.bank.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankController {

    private final BankService bankService;
    final BankRepository bankRepository;

    public BankController(BankService bankService, BankRepository bankRepository) {
        this.bankService = bankService;
        this.bankRepository = bankRepository;
    }

    @PostMapping("/registerBank")
    public ResponseEntity<Object> registerBank(@RequestBody Bank bank){
        return ResponseEntity.ok(this.bankService.registerBank(bank));
    }

    @GetMapping("/listBank")
    public ResponseEntity<Object> listBank(){
        return ResponseEntity.ok(this.bankService.listBank());
    }

    @DeleteMapping("/deleteBank")
    public void deleteBank(@RequestBody Bank bank){
        bankRepository.delete(bank);
    }

    @PutMapping("/updateBank")
    public Bank updateBank(@RequestBody Bank bank){
        return bankRepository.save(bank);
    }
}
