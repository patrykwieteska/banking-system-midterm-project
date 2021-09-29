package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.Account;
import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> getAccount(@PathVariable(name="id") Long id) {
        return accountRepository.findById(id);
    }


//    @GetMapping("/{ownerId}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Account> getAccountByOwner(@PathVariable(name="ownerId") Long id) {
//        return accountRepository.findByPrimaryOwnerId(id);
//    }

}
