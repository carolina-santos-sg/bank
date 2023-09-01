package com.bank.service;

import com.bank.model.Bank;
import com.bank.model.BankAgency;
import com.bank.repository.BankAgencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ResourceBundle;

@Service
public class BankAgencyService {
    final                                   //instanciando
    BankAgencyRepository agencyRepository;

    //construction
    public BankAgencyService(BankAgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public ResponseEntity<Object> listAgency(){
        return ResponseEntity.ok(this.agencyRepository.findAll());
    }

    @Transactional
    public BankAgency registerAgency(@RequestBody BankAgency bankAgency){
        this.existsAgency(bankAgency);
        return agencyRepository.save(bankAgency);
    }

    private void existsAgency(BankAgency bankAgency){
        if (this.agencyRepository.countByAgencyNumber(bankAgency.getAgencyNumber()) &&
            this.agencyRepository.countByBankNumber(bankAgency.getBankNumber().getBankNumber())){

        }
    }

}