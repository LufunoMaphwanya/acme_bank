package com.acme.test01.lufunomaphwanya.acme_bank_demo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.Account;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.CurrentAccount;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.models.SavingsAccount;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.repositories.AccountRepository;
import com.acme.test01.lufunomaphwanya.acme_bank_demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//mark class as Controller
@RestController
public class AccountController
{
    //autowire the BooksService class
    @Autowired
    AccountService accountsService;
    //creating a get mapping that retrieves all the books detail from the database
    @GetMapping("/accounts")
    private List<Account> getAllBooks()
    {
        return accountsService.list();
    }

    @PostMapping("/accounts")
    private Map createAccount(@RequestBody Map<String,Object> body) throws Exception {
        String accountType = body.get("type").toString();
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        if(accountType.equalsIgnoreCase("savings")){
            String deposit = body.get("deposit").toString();
            Long longDeposit = Long.valueOf(deposit);

            if(longDeposit < 2000 ) throw new Exception("Deposit too small.");

            accountsService.openSavingsAccount(id, longDeposit);
        }
        else if(accountType.equalsIgnoreCase("current")){
             accountsService.openCurrentAccount(id);
        } else {
            throw new Exception("Account type does not exist");

        }
        return Collections.singletonMap("id", id);

    }

    @PutMapping("/accounts")
    private Map update(@RequestBody Map<String,Object> body) throws Exception {
        String id = body.get("account_number").toString();
        int deposit = Integer.valueOf(body.get("deposit").toString());

        System.out.println("deposit");
        System.out.println(deposit);

        if(deposit > 0 ){
             accountsService.deposit(Long.valueOf(id), deposit);
        }else {
             accountsService.withdraw(Long.valueOf(id), deposit);

        }



        return Collections.singletonMap("id", id);

    }



}