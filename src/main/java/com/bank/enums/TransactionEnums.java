package com.bank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public enum TransactionEnums {
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW"),
    TRANSFER("TRANSFER");

    public String transactionEnums;
    TransactionEnums(String enums){
        transactionEnums = enums;
    }

}