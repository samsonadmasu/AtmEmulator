package com.Bank.Branch.RequestHandlers;

import com.Bank.Branch.Http.HttpClient;
import com.Bank.Branch.Http.RequestHandler;
import com.Bank.Branch.Models.Transaction;
import com.Bank.Branch.Utils.ServiceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AtmRequestHandler extends RequestHandler {

    private final HttpClient httpClient;

    @Autowired
    public AtmRequestHandler(HttpClient httpClient){
        super(httpClient, ServiceNames.ATM);
        this.httpClient = httpClient;
    }

    public Transaction getById(Long id){
        return super.get("employment",Transaction.class, id);
    }


    public Transaction[] getAllTransactions(){
         String path = "http://" + ServiceNames.ATM + "/employment/findAllActive";
         ResponseEntity<Transaction[]> response = httpClient.get().getForEntity(path, Transaction[].class);
         return response.getBody();
    }

}
