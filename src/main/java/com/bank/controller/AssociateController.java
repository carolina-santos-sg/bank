package com.bank.controller;

import com.bank.model.Associate;
import com.bank.repository.AssociateRepository;
import com.bank.service.AssociateService;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AssociateController {

    private final AssociateService associateService;
    final AssociateRepository associateRepository;

    public AssociateController(AssociateService associateService, AssociateRepository associateRepository) {
        this.associateService = associateService;
        this.associateRepository = associateRepository;
    }

    @GetMapping("/listAssociate")
    public ResponseEntity<Object> listAssociate(){ return ResponseEntity.ok(this.associateService.listAssociates()); }

    @PostMapping("/registerAssociate")
    public ResponseEntity<Object> registerAssociate(@RequestBody Associate associate){
        return ResponseEntity.ok(this.associateService.registerAssociate(associate));
    }

    @DeleteMapping("/deleteAssociate")
    public void deleteAssociate(@RequestBody Associate associate){
        associateRepository.delete(associate);
    }

    @PutMapping("/updateAssociate")
    public ResponseEntity<Object> updateAssociate(@RequestBody Associate associate){
        return ResponseEntity.ok(this.associateService.updateAssociate(associate));
    }
}
