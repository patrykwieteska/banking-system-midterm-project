package com.ironhack.midtermproject.service;

import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dto.CheckingDto;
import org.springframework.http.ResponseEntity;

public interface CheckingService {
    void createChecking(CheckingDto checkingDto);
}
