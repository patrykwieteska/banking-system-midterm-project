package com.ironhack.midtermproject.service;

import com.ironhack.midtermproject.dao.owners.AccountHolder;

import java.util.Optional;

public interface AccountHolderService {

    AccountHolder findById(Long id);
}
