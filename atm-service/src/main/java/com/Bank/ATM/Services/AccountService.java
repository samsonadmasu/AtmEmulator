package com.Bank.ATM.Services;

import com.Bank.ATM.Dto.Account;
import com.Bank.ATM.Dto.AccountValidationResDto;
import com.Bank.ATM.RequestHandlers.BankRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankRequestHandler bankRequestHandler;

    public AccountValidationResDto validateAccount(Long accountNumber){
        return bankRequestHandler.getAccount(accountNumber);
    }

    public Double balanceEnquiry(Long accountNumber){
        return bankRequestHandler.getAccount(accountNumber).getBalance();
    }
}
