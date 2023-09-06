package com.bank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionDto {
    private String transactionType;
    private BigDecimal transactionValue = new BigDecimal("0");
    private long sourceAccountId;
    private long targetAccountId;
}