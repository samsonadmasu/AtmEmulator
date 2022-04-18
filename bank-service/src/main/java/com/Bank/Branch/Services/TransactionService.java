package com.Bank.Branch.Services;

import com.Bank.Branch.Dtos.TransactionReqDto;
import com.Bank.Branch.Dtos.TransactionResDto;
import com.Bank.Branch.Models.Account;
import com.Bank.Branch.Models.Transaction;
import com.Bank.Branch.Repositories.TransactionRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionService extends BaseService<Transaction, Long> {

    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository repository, AccountService accountService){
        super(repository, new Transaction());
        this.accountService = accountService;
    }

    @Override
    @Hidden
    public Transaction create(Transaction obj) {
        return super.create(obj);
    }

    public TransactionResDto create(TransactionReqDto request) {
        Account account = accountService.findByAccountNumber(request.getAccountNumber());
        double amount = request.getAmount();
        if( amount < 0  &&  (Math.abs(amount) > account.getBalance() )){
            throw badRequest(); //insufficient balance
        }else if(amount < 0 && (Math.abs(amount) < account.getBalance() )){
            account.setBalance(account.getBalance() + amount);
        }else if(amount > 0){
            account.setBalance(account.getBalance() + amount);
        }

        account = accountService.update(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(request.getAmount());
        String refNumber = UUID.randomUUID().toString();
        transaction.setReferenceNumber(refNumber);
        transaction = super.create(transaction);

        TransactionResDto response = new TransactionResDto();
        response.setAmount(amount);
        response.setAccountNumber(account.getAccountNumber());
        response.setTransactionDate(new Date());
        response.setAmount(transaction.getAmount());
        response.setCurrentBalance(account.getBalance());
        response.setReferenceNumber(transaction.getReferenceNumber());

       return  response;
    }
}
