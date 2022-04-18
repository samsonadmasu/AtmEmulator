package com.Bank.ATM.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountValidationResDto {
    private String fistName;
    private String lastName;
    private Long accountNumber;
    private Double balance;
}
