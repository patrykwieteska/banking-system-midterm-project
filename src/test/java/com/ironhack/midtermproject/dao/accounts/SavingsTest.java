package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.exceptions.IncorrectInterestRateValueException;
import com.ironhack.midtermproject.exceptions.IncorrectMinimumBalanceValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTest {

    private Money money;
    private AccountHolder owner;
    @BeforeEach
    void setUp() {
        money = new Money(new BigDecimal("90000"));
        owner = new AccountHolder();
    }

    @Test
    void Savings_setInterestRate_valueGreaterThanDefault() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",
                Status.ACTIVE,null);
        assertThrows(IncorrectInterestRateValueException.class, () -> savings.setInterestRate(new BigDecimal("1.00")));
        assertThrows(IncorrectInterestRateValueException.class, () -> {
            new Savings(this.money,this.owner,null,"qwerfq234fasd",new BigDecimal("900"),
                    Status.ACTIVE,new BigDecimal("0.51"),null);
        });

    }

    @Test
    void Savings_setInterestRate_valueLessThanDefault() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",
                Status.ACTIVE,null);
        assertThrows(IncorrectInterestRateValueException.class,
                () -> savings.setInterestRate(new BigDecimal("-0.0000001")));
        assertThrows(IncorrectInterestRateValueException.class, () -> {
            new Savings(this.money,this.owner,null,"qwerfq234fasd",new BigDecimal("900"),
                    Status.ACTIVE,new BigDecimal("-0.0000001"),null);
        });

    }

    @Test
    void Savings_defaultValues() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",
                Status.ACTIVE,null);

        assertEquals(savings.getInterestRate(), new BigDecimal("0.0025"));
        assertEquals(savings.getMinimumBalance(), new BigDecimal("1000.00"));
    }

    @Test
    void Savings_setInterestRate_customValue() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",
                Status.ACTIVE,new BigDecimal("0.004"),null);

        assertEquals(savings.getInterestRate(),new BigDecimal("0.004"));
    }

    @Test
    void Savings_setMinimumBalance_lessThanDefault() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",
                Status.ACTIVE,null);

        assertThrows(IncorrectMinimumBalanceValueException.class, () -> savings.setMinimumBalance(new BigDecimal("99")));
        assertThrows(IncorrectMinimumBalanceValueException.class, () -> {
            new Savings(this.money,this.owner,null,"qwerfq234fasd",new BigDecimal("99"),
                    Status.ACTIVE,new BigDecimal("0.51"),null);
        });

    }

    @Test
    void Savings_setMinimumBalance_customValue() {
        Savings savings = new Savings(this.money,this.owner,null,"qwerfq234fasd",new BigDecimal("150"),
                Status.ACTIVE,null);

        assertEquals(savings.getMinimumBalance(),new BigDecimal("150"));
    }

    @Test
    void Savings_getBalance_creationDateGreaterThanAYear() {
        Savings savings = new Savings(money,owner,null,"test",Status.ACTIVE,
                null);

        savings.setCreationDate(LocalDate.now().minusYears(1).minusDays(1));

        assertEquals(new BigDecimal("90225.00"),savings.getBalance().getAmount());
        assertEquals(savings.getInterestUpdateDate(),LocalDate.now());

        assertEquals(new BigDecimal("90225.00"),savings.getBalance().getAmount()); // second check - another invoke
        // of getBalance shouldn't return greater balance

    }

    @Test
    void Savings_getBalance_creationDateLessThanAYear() {
        LocalDate date = LocalDate.now().minusYears(1);
        Savings savings = new Savings(money,owner,null,"test",Status.ACTIVE,null);

        savings.setCreationDate(date);

        assertEquals(new BigDecimal("90000.00"),savings.getBalance().getAmount());
        assertNull(savings.getInterestUpdateDate());
    }

    @Test
    void Savings_getBalance_interestUpdateDateGreaterThanAYear() {
        Savings savings = new Savings(money,owner,null,"test",Status.ACTIVE,
                LocalDate.now().minusYears(2).minusDays(1));
        savings.setCreationDate(LocalDate.of(2019,10,1));

        assertEquals(new BigDecimal("90225.00"),savings.getBalance().getAmount());
        assertEquals(savings.getInterestUpdateDate(),LocalDate.now());
    }
}