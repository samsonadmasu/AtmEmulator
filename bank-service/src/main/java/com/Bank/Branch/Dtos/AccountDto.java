package com.Bank.Branch.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String fistName;
    private String lastName;
    private double balance;
    private Long accountNumber;
    private String username;
    private String pin;
}
