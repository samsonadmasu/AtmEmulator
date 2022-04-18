package com.Bank.Branch.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Model {
    private double amount;
    private String referenceNumber;
    @ManyToOne
    private Account account;
}
