package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.common.Validator;
import com.ironhack.midtermproject.dao.Address;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dao.owners.Admin;
import com.ironhack.midtermproject.dao.owners.ThirdPart;
import com.ironhack.midtermproject.repository.AddressRepository;
import com.ironhack.midtermproject.repository.OwnerRepository;
import com.ironhack.midtermproject.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    @Transactional
    public void createAccountHolder(AccountHolder accountHolder) {

        Address primaryAddress = accountHolder.getPrimaryAddress();
        Address mailingAddress = accountHolder.getMailingAddress();

        AccountHolder owner = new AccountHolder(accountHolder.getName(), accountHolder.getDateOfBirth(),
                accountHolder.getPrimaryAddress(), accountHolder.getMailingAddress());

        List<Address> storedAddresses = addressRepository.findAll();

        long existingPrimaryAddress = Validator.checkExistingAddress(storedAddresses, primaryAddress);
        long existingMailingAddress = Validator.checkExistingAddress(storedAddresses, mailingAddress);

        if (existingPrimaryAddress == 0) addressRepository.save(primaryAddress);
        else owner.setPrimaryAddress(addressRepository.findById(existingPrimaryAddress).get());


        if (mailingAddress != null) {
            if (existingMailingAddress == 0) addressRepository.save(mailingAddress);
            else owner.setMailingAddress(addressRepository.findById(existingMailingAddress).get());
        }

        ownerRepository.save(owner);

    }

    @Override
    public void createAdmin(Admin admin) {
        ownerRepository.save(admin);
    }

    @Override
    public void createThirdPart(ThirdPart thirdPart) {
        ownerRepository.save(thirdPart);
    }
}
