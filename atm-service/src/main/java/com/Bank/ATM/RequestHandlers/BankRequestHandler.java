package com.Bank.ATM.RequestHandlers;
import com.Bank.ATM.Domains.TransactionReqDto;
import com.Bank.ATM.Domains.TransactionResDto;
import com.Bank.ATM.Dto.AccountValidationResDto;
import com.Bank.ATM.Dto.AuthReqDto;
import com.Bank.ATM.Dto.AuthResDto;
import com.Bank.ATM.Http.HttpClient;
import com.Bank.ATM.Http.RequestHandler;
import com.Bank.ATM.Utils.ServiceNames;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Component
public class BankRequestHandler extends RequestHandler {

    @Autowired
    public BankRequestHandler(HttpClient httpClient){
        super(httpClient, ServiceNames.BANK);
    }

    public AccountValidationResDto getAccount(Long accountNumber){
        return get("accounts/validate-account-number?accountNumber={accountNumber}",AccountValidationResDto.class, accountNumber);
    }

    public TransactionResDto createTransaction(TransactionReqDto transaction){
        return post("transactions/create", TransactionResDto.class, transaction);
    }
//transactions/create?amount=100&accountNumber=1
    public AuthResDto authenticate(String username,String pin){
        System.out.println("username " + username + " " + "pin " + pin);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pin", pin);
        return post("auth/login?username={username}&pin={pin}", AuthResDto.class, params);
    }

}
