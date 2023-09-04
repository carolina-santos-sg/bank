package com.bank.controller;

import com.bank.model.Associate;
import com.bank.service.AssociateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AssociateController {

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) { this.associateService = associateService; }

    @GetMapping("/listAssociate")
    public ResponseEntity<Object> listAssociate(){ return ResponseEntity.ok(this.associateService.listAssociates()); }

    @PostMapping("/registerAssociate")
    public ResponseEntity<Object> registerAssociate(@RequestBody Associate associate){
        return ResponseEntity.ok(this.associateService.registerAssociate(associate));
    }
}
