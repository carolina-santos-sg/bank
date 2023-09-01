package com.bank.service;

import com.bank.model.Bank;
import com.bank.repository.BankRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;


@Service
public class BankService {

    final
    BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {

        this.bankRepository = bankRepository;
    }

    @Transactional
    public Bank registerBank(@RequestBody Bank bank){
        this.existsBank(bank);
        return bankRepository.save(bank);
    }

    public ResponseEntity<Object> listBank(){
        return ResponseEntity.ok(bankRepository.findAll());
    }

//    public ResponseEntity<Object> searchBank(@PathVariable("id") long id){
//        return ResponseEntity.ok(bankRepository.findAllById(id));
//    }

    private void existsBank(@NotNull Bank bank){
        if (this.bankRepository.countBankByName(bank.getBankName()) ||
                this.bankRepository.countBankByNumber(bank.getBankNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Banco já registrado!");
        }
    }
}