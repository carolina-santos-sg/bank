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
    @Column(name = "id")
    private long id;

    @Column(name = "number_account")
    private long numberAccount;

    @Column(name = "balance")
    private BigDecimal balance = new BigDecimal("0");

    @Column(name = "transaction_limit")
    private BigDecimal transactionLimit = new BigDecimal("0");

    @JoinColumn(name = "agency_id")
    @OneToOne
    private BankAgency agencyNumber;

    @JoinColumn(name = "associate_id")
    @ManyToOne
    private Associates associateId;
}