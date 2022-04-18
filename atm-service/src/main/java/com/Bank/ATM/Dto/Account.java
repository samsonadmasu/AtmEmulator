package com.Bank.ATM.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends Model {
    private Long accountNumber;
    private double balance;
    private double credit;
    private BankUser owner;

}
