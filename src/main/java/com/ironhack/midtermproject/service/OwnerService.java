package com.ironhack.midtermproject.service;

import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dao.owners.Admin;
import com.ironhack.midtermproject.dao.owners.ThirdPart;
import org.springframework.http.ResponseEntity;

public interface OwnerService {
    void createAccountHolder(AccountHolder accountHolder);

    void createAdmin(Admin admin);

    void createThirdPart(ThirdPart thirdPart);
}
