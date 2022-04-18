package com.Bank.ATM.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestServiceException extends RuntimeException {
    private String serviceName;
    private HttpStatus statusCode;
    private String error;
}
