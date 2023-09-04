package com.bank.dto;   //data transfer to object

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class BankAgencyDto {
    @NotNull
    private long bankNumber;
    @NotNull
    private long agencyNumber;

}