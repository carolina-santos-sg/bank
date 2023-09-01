package com.bank.service;

import com.bank.model.Associates;
import com.bank.repository.AssociateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssociateService {
    final
    AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Transactional
    public Associates registerAssociate(@RequestBody Associates associates){
        this.existsAssociate(associates);
        return associateRepository.save(associates);
    }

    public ResponseEntity<Object> listAssociates (){
        return ResponseEntity.ok(this.associateRepository.findAll());
    }

    public void existsAssociate(@NotNull Associates associates){
        if (this.associateRepository.countByDocumentNumber(associates.getDocumentNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já cadastrado!");
        }
    }
}