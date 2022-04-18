package com.Bank.ATM.Controllers;

import com.Bank.ATM.Domains.TransactionReqDto;
import com.Bank.ATM.Domains.TransactionResDto;
import com.Bank.ATM.Dto.AccountValidationResDto;
import com.Bank.ATM.Dto.AuthReqDto;
import com.Bank.ATM.Dto.AuthResDto;
import com.Bank.ATM.Services.AccountService;
import com.Bank.ATM.Services.AuthenticationService;
import com.Bank.ATM.Services.TransactionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm")
@Tag(name = "ATM")
@RequiredArgsConstructor
public class AtmController {

    private final AccountService accountService;
    private final AuthenticationService authenticationService;
    private final TransactionService transactionService;

    @GetMapping("/validate-card")
    @CircuitBreaker(name = "BANK-SERVICE", fallbackMethod = "serviceFallBack")
    private AccountValidationResDto validateCard(Long accountNumber){
        return accountService.validateAccount(accountNumber);
    }

    @PostMapping("/login")
    @CircuitBreaker(name = "BANK-SERVICE", fallbackMethod = "serviceFallBack")
    private AuthResDto authenticate(@RequestParam String username, @RequestParam String pin){
        return authenticationService.authenticate(username, pin);
    }

    @PostMapping("/transact")
    @CircuitBreaker(name = "BANK-SERVICE", fallbackMethod = "serviceFallBack")
    private TransactionResDto transact(TransactionReqDto transactionReqDto){
        return transactionService.transact(transactionReqDto);
    }

    @GetMapping("/check-balance")
    @CircuitBreaker(name = "BANK-SERVICE", fallbackMethod = "serviceFallBack")
    private Double balanceEnquiry(Long accountNumber){
        return accountService.balanceEnquiry(accountNumber);
    }

    public String serviceFallBack(Exception e){
        return "System is Down, please try again";
    }
}
