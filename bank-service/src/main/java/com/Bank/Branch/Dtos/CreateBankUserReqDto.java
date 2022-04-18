package com.Bank.Branch.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankUserReqDto {
    private Long id;
    private String username;
    private String pin;
}
