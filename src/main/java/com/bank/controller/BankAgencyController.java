package com.bank.controller;

import com.bank.dto.BankAgencyDto;
import com.bank.model.BankAgency;
import com.bank.repository.BankAgencyRepository;
import com.bank.service.BankAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankAgencyController {

    final BankAgencyService agencyService;
    final BankAgencyRepository bankAgencyRepository;

    public BankAgencyController(BankAgencyService agencyService, BankAgencyRepository bankAgencyRepository) {
        this.agencyService = agencyService;
        this.bankAgencyRepository = bankAgencyRepository;
    }

    @GetMapping("/listAgency")
    public ResponseEntity<Object> listAgency(){
        return ResponseEntity.ok(this.agencyService.listAgency());
    }

    @PostMapping("/registerAgency")
    public ResponseEntity<Object> registerAgency(@RequestBody BankAgencyDto bankAgencyDto){
        return ResponseEntity.ok(this.agencyService.registerAgency(bankAgencyDto));
    }

    @DeleteMapping("/deleteAgency")
    public void deleteAgency(@RequestBody BankAgency bankAgency){
        bankAgencyRepository.delete(bankAgency);
    }

    @PutMapping("/updateAgency/{id}")
    public ResponseEntity<Object> updateAgency(@PathVariable("id") long id, @RequestBody BankAgencyDto bankAgencyDto){
        return ResponseEntity.ok(agencyService.updateAgency(id, bankAgencyDto));
    }
}