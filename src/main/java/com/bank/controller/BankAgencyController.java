package com.bank.controller;

import com.bank.dto.BankAgencyDto;
import com.bank.model.BankAgency;
import com.bank.service.BankAgencyService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BankAgencyController {

    final BankAgencyService agencyService;
    final BankAgencyDto bankAgencyDto = null;

    public BankAgencyController(BankAgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/listAgency")
    public ResponseEntity<Object> listAgency(){
        return ResponseEntity.ok(this.agencyService.listAgency());
    }

    @PostMapping("/registerAgency")
    public ResponseEntity<Object> registerAgency(@RequestBody BankAgencyDto bankAgencyDto){
        return ResponseEntity.ok(this.agencyService.registerAgency(bankAgencyDto));
    }
}