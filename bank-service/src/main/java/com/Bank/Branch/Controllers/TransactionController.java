package com.Bank.Branch.Controllers;

import com.Bank.Branch.Dtos.TransactionReqDto;
import com.Bank.Branch.Dtos.TransactionResDto;
import com.Bank.Branch.Models.Transaction;
import com.Bank.Branch.Services.TransactionService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@Tag(name = "Transactions")
public class TransactionController extends BaseController<Transaction, Long> {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service){
        super(service);
        this.service = service;
    }

    @Hidden
    @Override
    public Transaction create(Transaction obj) {
        return super.create(obj);
    }

    @PostMapping("/create")
    public TransactionResDto create(@RequestBody TransactionReqDto request) {
        return service.create(request);
    }

}
