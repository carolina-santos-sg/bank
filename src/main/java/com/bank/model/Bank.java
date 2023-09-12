package com.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_number")
    private long bankNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "full_balance_transaction")
    private BigDecimal fullBalanceTransaction;
}
