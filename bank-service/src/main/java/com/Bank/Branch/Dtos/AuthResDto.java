package com.Bank.Branch.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResDto {
    private String access_token;
    private String refresh_token;
}
