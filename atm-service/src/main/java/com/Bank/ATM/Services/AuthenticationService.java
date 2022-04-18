package com.Bank.ATM.Services;

import com.Bank.ATM.Dto.Account;
import com.Bank.ATM.Dto.AccountValidationResDto;
import com.Bank.ATM.Dto.AuthReqDto;
import com.Bank.ATM.Dto.AuthResDto;
import com.Bank.ATM.RequestHandlers.BankRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final BankRequestHandler requestHandler;
    private final AccountService accountService;

    public AuthResDto authenticate(String username, String pin){
       return requestHandler.authenticate(username, pin);
    }

    public AccountValidationResDto validateCard(Long accountNumber){
       return accountService.validateAccount(accountNumber);
    }

}
