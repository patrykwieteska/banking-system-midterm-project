package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/checking")
public class CheckingController {

    @Autowired
    CheckingService checkingService;

    @Autowired
    CheckingRepository checkingRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChecking(@RequestBody @Valid CheckingDto checkingDto) {

        checkingService.createChecking(checkingDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Checking> getChecking(@PathVariable(name="id") Long id) {
        return checkingRepository.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAllChecking() {
        return checkingRepository.findAll();
    }

}
