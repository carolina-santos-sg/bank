package com.bank.controller;

import com.bank.dto.TransactionDto;
import com.bank.enums.TransactionEnums;
import com.bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/listTransactions")
    public ResponseEntity<Object> listTransactions(){
        return ResponseEntity.ok(this.transactionService.listTransactions());
    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(this.transactionService.deposit(transactionDto));
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(this.transactionService.withdraw(transactionDto, TransactionEnums.WITHDRAW));
    }
    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(this.transactionService.transfer(transactionDto, TransactionEnums.TRANSFER));
    }

}