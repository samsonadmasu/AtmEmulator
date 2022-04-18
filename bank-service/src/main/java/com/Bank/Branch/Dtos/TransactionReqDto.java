package com.Bank.Branch.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReqDto {
    private double amount;
    private Long accountNumber;
}
