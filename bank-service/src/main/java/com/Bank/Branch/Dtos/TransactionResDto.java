package com.Bank.Branch.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResDto {
    private double amount;
    private Long accountNumber;
    private double currentBalance;
    private Date transactionDate;
    private String referenceNumber;
}
