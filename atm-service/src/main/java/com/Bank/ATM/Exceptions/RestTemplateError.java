package com.Bank.ATM.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestTemplateError {
    private String timestamp;
    private String status;
    private String error;
    private String path;
    private String message;
}
