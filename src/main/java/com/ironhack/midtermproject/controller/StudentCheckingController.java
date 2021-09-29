package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dao.accounts.StudentChecking;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
import com.ironhack.midtermproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/student-checking")
public class StudentCheckingController {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    CheckingService checkingService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChecking(@RequestBody @Valid CheckingDto checkingDto) {

        checkingService.createChecking(checkingDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<StudentChecking> getChecking(@PathVariable(name="id") Long id) {
        return studentCheckingRepository.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> getAllChecking() {
        return studentCheckingRepository.findAll();
    }
}
