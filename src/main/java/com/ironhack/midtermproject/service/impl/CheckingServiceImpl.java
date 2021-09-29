package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dao.accounts.StudentChecking;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
import com.ironhack.midtermproject.service.AccountHolderService;
import com.ironhack.midtermproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @Override
    @Transactional
    public void createChecking(CheckingDto checkingDto) {

        Checking checking = new Checking(new Money(checkingDto.getBalanceAmount()),null,null,
                checkingDto.getSecretKey());

        AccountHolder secondaryOwner;

        //add primary Owner to the account;
        AccountHolder primaryOwner = accountHolderService.findById((long) checkingDto.getPrimaryOwnerId());
        checking.setPrimaryOwner(primaryOwner);

        //add secondary Owner to the account;
        if(checkingDto.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderService.findById((long) checkingDto.getSecondaryOwnerId());
            checking.setSecondaryOwner(secondaryOwner);

        }

        if (checkingDto.getStatus()!=null)
            checking.setStatus(checkingDto.getStatus());

        if(primaryOwner.getAge()<24) {
            studentCheckingRepository.save(new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),
                    checking.getSecondaryOwner(),checking.getSecretKey(),checking.getStatus()));
        } else {
            checkingRepository.save(checking);
        }
    }
}
