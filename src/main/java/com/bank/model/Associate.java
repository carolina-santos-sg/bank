package com.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "associates")
public class Associate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "associate_id")
    private long id;

    @Column(name = "name_associate")
    private String nameAssociate;

    @Column(name = "phone_number")
    private long   phoneNumber;

    @Column(name = "document_number")
    private long   documentNumber;

    @Column(name = "salary")
    private BigDecimal salary = new BigDecimal("0");

    public Associate findAllById(long associateId) {
        return null;
    }
}
