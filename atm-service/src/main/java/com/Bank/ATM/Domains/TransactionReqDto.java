package com.Bank.ATM.Domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReqDto {
    private long amount;
    private long accountNumber;
}
