package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {


    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public AccountHolder findById(Long id) {
        Optional<AccountHolder> storedAccountHolder = accountHolderRepository.findById(id);

        if(storedAccountHolder.isPresent()) {
            return storedAccountHolder.get();
        } else throw new EntityNotFoundException("Not found account holder with id: "+id);
    }
}
