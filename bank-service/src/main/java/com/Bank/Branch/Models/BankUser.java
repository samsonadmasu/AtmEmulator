package com.Bank.Branch.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUser extends Model {
    private String fistName;
    private String lastName;
    private String username;
    private String pin;
    @OneToOne
    private Account account;
}
