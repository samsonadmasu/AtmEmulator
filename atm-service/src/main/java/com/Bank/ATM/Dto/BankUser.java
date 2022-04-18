package com.Bank.ATM.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUser extends Model {
    private String userName;
    private String pin;
    private Account account;
}
