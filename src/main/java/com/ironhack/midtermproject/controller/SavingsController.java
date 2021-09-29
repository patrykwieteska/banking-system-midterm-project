package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.CreditCard;
import com.ironhack.midtermproject.dao.accounts.Savings;
import com.ironhack.midtermproject.dto.SavingsDto;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import com.ironhack.midtermproject.repository.SavingsRepository;
import com.ironhack.midtermproject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/savings")
public class SavingsController {

    @Autowired
    SavingsService creditCardService;

    @Autowired
    SavingsRepository savingsRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChecking(@RequestBody @Valid SavingsDto savingsDto) {

        creditCardService.createSaving(savingsDto);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Savings> findById(@PathVariable(name="id") Long id) {
        return savingsRepository.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAllSavings() {
        return savingsRepository.findAll();
    }
}
