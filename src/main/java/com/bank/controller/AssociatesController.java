package com.bank.controller;

import com.bank.model.Associates;
import com.bank.service.AssociateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AssociatesController {

    private final AssociateService associateService;

    public AssociatesController(AssociateService associateService) { this.associateService = associateService; }

    @GetMapping("/listAssociate")
    public ResponseEntity<Object> listAssociate(){ return ResponseEntity.ok(this.associateService.listAssociates()); }

    @PostMapping("/registerAssociate")
    public ResponseEntity<Object> registerAssociate(@RequestBody Associates associate){
        return ResponseEntity.ok(this.associateService.registerAssociate(associate));
    }
}
