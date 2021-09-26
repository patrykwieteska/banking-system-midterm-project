package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts/checking")
public class CheckingController {


    @Autowired
    CheckingService checkingService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChecking(@RequestBody @Valid CheckingDto checkingDto) {

        checkingService.createChecking(checkingDto);

    }

}
