package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dao.accounts.CreditCard;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.dto.CreditCardDto;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import com.ironhack.midtermproject.service.CheckingService;
import com.ironhack.midtermproject.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/credit-card")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    CreditCardRepository creditCardRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChecking(@RequestBody @Valid CreditCardDto creditCardDto) {

        creditCardService.createCreditCard(creditCardDto);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CreditCard> getCreditCard(@PathVariable(name="id") Long id) {
        return creditCardRepository.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }
}
