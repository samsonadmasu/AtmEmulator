package com.Bank.Branch.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends Model {
    private Long accountNumber;
    private Double balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_user_id")
    private BankUser owner;

}
