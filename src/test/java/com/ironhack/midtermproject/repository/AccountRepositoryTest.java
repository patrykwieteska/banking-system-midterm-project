package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.Address;
import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
import com.ironhack.midtermproject.dao.accounts.Checking;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OwnerRepository ownerRepository;

    Account account;
    Money money;
    AccountHolder owner;
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address("France", "Paris", "423423", "Street", "123");
        owner = new AccountHolder("The owner", LocalDate.of(1992, 2, 20), address);
        money = new Money(new BigDecimal("100000.00"));
        account = new Checking(money, owner, null, "");

        addressRepository.save(address);
        ownerRepository.save(owner);
        accountRepository.save(account);

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

    }

    @Test
    public void AccountRepository_findAllInformationAboutAccount() {

        Assertions.assertEquals(1,addressRepository.findAll().size());
        Assertions.assertEquals(1,ownerRepository.findAll().size());
        Assertions.assertEquals(1,accountRepository.findAll().size());

    }
}