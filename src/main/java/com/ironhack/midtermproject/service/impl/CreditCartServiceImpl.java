package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.CreditCard;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dto.CreditCardDto;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import com.ironhack.midtermproject.service.AccountHolderService;
import com.ironhack.midtermproject.service.CreditCardService;
import com.ironhack.midtermproject.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCartServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @Override
    public void createCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = new CreditCard(new Money(creditCardDto.getBalanceAmount()),
                creditCardDto.getCreditLimit(),null,null);


        AccountHolder primaryOwner = accountHolderService.findById((long) creditCardDto.getPrimaryOwnerId());
        creditCard.setPrimaryOwner(primaryOwner);

        if(creditCardDto.getSecondaryOwnerId()!=null) {
            creditCard.setSecondaryOwner(accountHolderService.findById((long) creditCardDto.getSecondaryOwnerId()));
        }


        if(creditCardDto.getInterestRate()!=null) {
            creditCard.setInterestRate(creditCardDto.getInterestRate());
        }

        if(creditCardDto.getCreditLimit()!=null) {
            creditCard.setCreditLimit(creditCardDto.getCreditLimit());
        }

        creditCardRepository.save(creditCard);
    }
}
