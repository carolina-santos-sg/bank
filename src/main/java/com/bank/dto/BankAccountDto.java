package com.bank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BankAccountDto {

    private Long numberAccount;

    private BigDecimal balance;

    private BigDecimal transactionLimit;

    private Long agencyId;

    private Long associateId;

    private Long bankNumber;
}
