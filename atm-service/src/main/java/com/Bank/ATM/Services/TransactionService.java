package com.Bank.ATM.Services;

import com.Bank.ATM.Domains.TransactionReqDto;
import com.Bank.ATM.Domains.TransactionResDto;
import com.Bank.ATM.RequestHandlers.BankRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final BankRequestHandler bankRequestHandler;

    public TransactionResDto transact(TransactionReqDto transactionReqDto){
        return bankRequestHandler.createTransaction(transactionReqDto);
    }
}
