package com.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number_account")
    private long numberAccount;

//    @JoinColumn(name = "bank_number")
//    @OneToMany
//    private long bankNumber;

//    @JoinColumn(name = "agency_number")
//    @OneToMany
//    private long agencyNumber;

//    @JoinColumn(name = "associate_id")
//    @OneToMany
//    private long associateId;

    @Column(name = "balance")
    private BigDecimal balance = new BigDecimal("0");

//    @JoinColumn(name = "transaction_limit")
//    @OneToMany
//    private BigDecimal transactionLimit = new BigDecimal("0");

    @Column(name = "source_account")
    private long sourceAccount;

    @Column(name = "target_account")
    private long targetAccount;

}
