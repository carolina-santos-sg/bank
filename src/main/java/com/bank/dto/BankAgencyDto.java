package com.bank.dto;   //data transfer to object

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAgencyDto {
    public long bankNumber;

    public long agencyNumber;
}