package com.bank.service;

import com.bank.model.Associate;
import com.bank.model.BankAgency;
import com.bank.repository.AssociateRepository;
import com.bank.repository.BankAgencyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AssociateService {
    final
    AssociateRepository associateRepository;
    final BankAgencyRepository bankAgencyRepository;

    public AssociateService(AssociateRepository associateRepository, BankAgencyRepository bankAgencyRepository) {
        this.associateRepository = associateRepository;
        this.bankAgencyRepository = bankAgencyRepository;
    }

    @Transactional
    public Associate registerAssociate(@RequestBody Associate associate){
        this.existsAssociate(associate);
        return associateRepository.save(associate);
    }

    public ResponseEntity<Object> listAssociates (){
        return ResponseEntity.ok(this.associateRepository.findAll());
    }

    public void existsAssociate(Associate associate){
        if (Objects.isNull(associate.getId())){
            throw new RuntimeException("É preciso informar um associado!");
        }
        if (this.associateRepository.countByDocumentNumber(associate.getDocumentNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já cadastrado!");
        }
    }

    public Associate findAssociateById(long id){
        return this.associateRepository.findById(id).orElseThrow(() -> new RuntimeException("Associate não encontrado!"));
    }
    public BankAgency findAgencyById(long id){
        return this.bankAgencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Agency não encontrado!"));
    }

    @Transactional
    public ResponseEntity<Object> updateAssociate(Associate associate){
        findAssociateById(associate.getId());

        return ResponseEntity.ok(this.associateRepository.save(associate));
    }

}