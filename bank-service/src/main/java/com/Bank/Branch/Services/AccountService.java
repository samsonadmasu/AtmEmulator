package com.Bank.Branch.Services;

import com.Bank.Branch.Dtos.AccountDto;
import com.Bank.Branch.Dtos.AccountValidationResDto;
import com.Bank.Branch.Models.Account;
import com.Bank.Branch.Models.BankUser;
import com.Bank.Branch.Repositories.AccountRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, Long>{

    private final BankUserService bankUserService;
    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository, BankUserService bankUserService){
        super(repository, new Account());
        this.bankUserService = bankUserService;
        this.repository = repository;
    }

    public Account create(AccountDto request){
        BankUser bankUser = new BankUser();
        bankUser.setFistName(request.getFistName());
        bankUser.setLastName(request.getLastName());
        bankUser.setUsername(request.getUsername());
        bankUser.setPin(request.getPin());
        bankUser = bankUserService.create(bankUser);

        Account account = new Account();
        account.setId(request.getId());
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        account.setOwner(bankUser);
        return super.create(account);
    }

    public Account update(AccountDto request){
        if(find(request.getId()) != null){
            return create(request);
        }
        throw badRequest();
    }


    public AccountValidationResDto validateAccount(Long accountNumber) {
        try {
            Account account = repository.findByAccountNumber(accountNumber);
            AccountValidationResDto response = new AccountValidationResDto();
            response.setAccountNumber(account.getAccountNumber());
            response.setFistName(account.getOwner().getFistName());
            response.setLastName(account.getOwner().getLastName());
            response.setBalance(account.getBalance());
            return response;
        } catch (Exception e) {
            throw errorWhileProcessing();
        }
    }

    public Account findByAccountNumber(Long accountNumber){
        try{
            return repository.findByAccountNumber(accountNumber);
        }catch (Exception e){
            throw errorWhileProcessing();
        }
    }
}
