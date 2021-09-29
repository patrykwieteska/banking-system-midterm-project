package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dao.accounts.Savings;
import com.ironhack.midtermproject.dao.accounts.StudentChecking;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dto.SavingsDto;
import com.ironhack.midtermproject.repository.SavingsRepository;
import com.ironhack.midtermproject.service.AccountHolderService;
import com.ironhack.midtermproject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @Override
    public void createSaving(SavingsDto savingsDto) {

        Savings savings = new Savings(new Money(savingsDto.getBalanceAmount()),null,null,savingsDto.getSecretKey(),
                savingsDto.getStatus());
        AccountHolder secondaryOwner;

        //add primary Owner to the account;
        AccountHolder primaryOwner = accountHolderService.findById((long) savingsDto.getPrimaryOwnerId());
        savings.setPrimaryOwner(primaryOwner);

        //add secondary Owner to the account;
        if(savingsDto.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderService.findById((long) savingsDto.getSecondaryOwnerId());
            savings.setSecondaryOwner(secondaryOwner);

        }

        if (savingsDto.getStatus()!=null)
            savings.setStatus(savingsDto.getStatus());

        if(savingsDto.getInterestRate()!=null)
            savings.setInterestRate(savingsDto.getInterestRate());

        if(savingsDto.getMinimumBalance()!=null)
            savings.setMinimumBalance(savingsDto.getMinimumBalance());


        savingsRepository.save(savings);

    }
}
