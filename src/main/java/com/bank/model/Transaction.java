package com.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaction")
    private long id;

    @Column(name = "type")
    private String transactionType;

    @Column(name = "value")
    private BigDecimal transactionValue = new BigDecimal("0");

    @Column(name = "date")
    Date dateTransaction = new Date();

    @JoinColumn(name = "source_account")
    @ManyToOne
    private BankAccount sourceAccountId;

    @JoinColumn(name = "target_account")
    @ManyToOne
    private BankAccount targetAccountId;
}
