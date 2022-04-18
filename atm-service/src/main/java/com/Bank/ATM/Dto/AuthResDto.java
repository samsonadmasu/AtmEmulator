package com.Bank.ATM.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResDto {
    private String access_token;
    private String refresh_token;
}
