package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    private Checking checking;

    private Money money;
    private AccountHolder owner;
    @BeforeEach
    void setUp() {
        money = new Money(new BigDecimal("0"));
        owner = new AccountHolder();

        checking = new Checking(money,owner,null,"dasda3dawcsdasd", LocalDate.now(), Status.ACTIVE);

    }



    @Test
    void Checking_setMinimumBalance_defaultValue() {
        assertEquals(checking.getMinimumBalance(),new BigDecimal("250.00"));
    }

    @Test
    void setMonthlyMaintenanceFee_defaultValue() {
        assertEquals(checking.getMonthlyMaintenanceFee(),new BigDecimal("12.00"));

    }
}