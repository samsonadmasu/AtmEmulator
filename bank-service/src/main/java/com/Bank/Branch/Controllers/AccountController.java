package com.Bank.Branch.Controllers;

import com.Bank.Branch.Dtos.AccountDto;
import com.Bank.Branch.Dtos.AccountValidationResDto;
import com.Bank.Branch.Models.Account;
import com.Bank.Branch.Services.AccountService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
@Tag(name = "Accounts")
public class AccountController extends BaseController<Account, Long> {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service){
        super(service);
        this.service = service;
    }

    @PostMapping("/create")
    public  Account create(AccountDto request){
       return service.create(request);
    }

    @PostMapping("/update")
    public Account update(AccountDto request) {
        return service.update(request);
    }

    @GetMapping("/validate-account-number")
    public AccountValidationResDto validateAccountNumber(Long accountNumber){
        return service.validateAccount(accountNumber);
    }

}
