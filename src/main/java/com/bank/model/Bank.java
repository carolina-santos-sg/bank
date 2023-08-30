package com.bank.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_number")
    private long bankNumber;

    @Column(name = "bank_name")
    private String bankName;

//    @JoinColumn(name = "agency_number")
//    @OneToMany
//    private long agencyNumber;

//    @JoinColumn(name = "bank_account")
//    @ManyToOne
//    private long bankAccount;
}
