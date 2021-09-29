package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dao.owners.Admin;
import com.ironhack.midtermproject.dao.owners.ThirdPart;
import com.ironhack.midtermproject.repository.OwnerRepository;
import com.ironhack.midtermproject.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @PostMapping("/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
        ownerService.createAccountHolder(accountHolder);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountHolder(@RequestBody @Valid Admin admin) {
        ownerService.createAdmin(admin);
    }

    @PostMapping("/third-part")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountHolder(@RequestBody @Valid ThirdPart thirdPart) {
        ownerService.createThirdPart(thirdPart);
    }




}
