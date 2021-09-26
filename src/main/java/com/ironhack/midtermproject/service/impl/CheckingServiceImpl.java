package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dto.CheckingDto;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public void createChecking(CheckingDto checkingDto) {

        Checking checking = new Checking(new Money(new BigDecimal(checkingDto.getBalanceAmount())),null,null,
                checkingDto.getSecretKey());

        Optional<AccountHolder> storedPrimaryOwner =
                accountHolderRepository.findById((long) checkingDto.getPrimaryOwnerId());

        Optional<AccountHolder> storedSecondaryOwner;

        //add primary Owner to the account;
        if(storedPrimaryOwner.isEmpty()) {
            throw new EntityNotFoundException("There is no AccountHolder with id:"+checkingDto.getPrimaryOwnerId());
        } else {
            checking.setPrimaryOwner(storedPrimaryOwner.get());
        }

        //add secondary Owner to the account;
        if(checkingDto.getSecondaryOwnerId() != null) {
            storedSecondaryOwner = accountHolderRepository.findById((long) checkingDto.getSecondaryOwnerId());
            if(storedSecondaryOwner.isEmpty()) {
                throw new EntityNotFoundException("There is no AccountHolder with id:"+checkingDto.getPrimaryOwnerId());
            } else {
                checking.setSecondaryOwner(storedPrimaryOwner.get());
            }
        }

        checkingRepository.save(checking);
    }
}
