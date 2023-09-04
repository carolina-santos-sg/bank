package com.bank.service;

import com.bank.model.Associate;
import com.bank.repository.AssociateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssociateService {
    final
    AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Transactional
    public Associate registerAssociate(@RequestBody Associate associate){
        this.existsAssociate(associate);
        return associateRepository.save(associate);
    }

    public ResponseEntity<Object> listAssociates (){
        return ResponseEntity.ok(this.associateRepository.findAll());
    }

    public void existsAssociate(@NotNull Associate associate){
        if (this.associateRepository.countByDocumentNumber(associate.getDocumentNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já cadastrado!");
        }
    }

    public boolean findAllById(long id){
        return associateRepository.findById(id);
    }
}