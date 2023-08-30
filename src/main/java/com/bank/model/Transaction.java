package com.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private String type;

    @Column(name = "value")
    private BigDecimal value = new BigDecimal("0");

    @Column(name = "date")
    private Date data;

    @Column(name = "source_account")
    private long sourceAccount;

    @Column(name = "target_account")
    private long targetAccount;
}
