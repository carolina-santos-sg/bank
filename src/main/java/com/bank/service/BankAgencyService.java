package com.bank.service;


import com.bank.dto.BankAgencyDto;
import com.bank.model.BankAgency;
import com.bank.repository.BankAgencyRepository;
import com.bank.repository.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Service
public class BankAgencyService {
    final                                   //instanciando
    BankAgencyRepository agencyRepository;
    final BankRepository bankRepository;
    final BankService bankService;

    //construction
    public BankAgencyService(BankAgencyRepository agencyRepository, BankRepository bankRepository, BankService bankService) {
        this.agencyRepository = agencyRepository;
        this.bankRepository = bankRepository;
        this.bankService = bankService;
    }

    public ResponseEntity<Object> listAgency(){
        return ResponseEntity.ok(this.agencyRepository.findAll());
    }

    //@Transactional
    public ResponseEntity<BankAgency> registerAgency(@RequestBody BankAgencyDto bankAgencyDto){
        if (Objects.isNull(bankAgencyDto.getBankNumber())){
            throw new RuntimeException("Bank Number vazio!");
        }

        if (!this.bankRepository.countBankByNumber(bankAgencyDto.getBankNumber())){
            throw new RuntimeException("Bank não existe!");
        }

        if (this.agencyRepository.countAgencyAndBankByNumber(bankAgencyDto.getAgencyNumber(), bankAgencyDto.getBankNumber())){
           throw new RuntimeException("Bank Agency já registrada!");
        }

        BankAgency bankAgency = new BankAgency();
        bankAgency.setAgencyNumber(bankAgencyDto.getAgencyNumber());
        bankAgency.setBankNumber(this.bankService.findBankById(bankAgencyDto.getBankNumber()));

        return ResponseEntity.ok(agencyRepository.save(bankAgency));
    }

    public BankAgency findAgencyById(Long id){
        return this.agencyRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Agency não encontrado!");
        });
    }

    public ResponseEntity<Object> updateAgency(long id, BankAgencyDto bankAgencyDto){
        if (this.agencyRepository.countAgencyAndBankByNumber(bankAgencyDto.getAgencyNumber(), bankAgencyDto.getBankNumber())){
            throw new RuntimeException("Agência não encontrada!");
        }

        BankAgency agency = this.agencyRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Agência não encontrada!");
        });

        agency.setAgencyNumber(bankAgencyDto.getAgencyNumber());

        return ResponseEntity.ok(this.agencyRepository.save(agency));
    }
}