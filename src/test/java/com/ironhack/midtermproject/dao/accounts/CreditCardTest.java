package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.exceptions.IncorrectCreditLimitValueException;
import com.ironhack.midtermproject.exceptions.IncorrectInterestRateValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreditCardTest {

    private Money money;
    private AccountHolder owner;
    private LocalDate creationDate;


    @BeforeEach
    void setUp() {
        money = new Money(new BigDecimal("100.00"));
        owner = new AccountHolder();
        creationDate = LocalDate.now();
    }

    @Test
    void CreditCard_defaultValues() {
        CreditCard creditCard = new CreditCard(money, owner, null, creationDate);

        Assertions.assertEquals(creditCard.getCreditLimit(), new BigDecimal("100.00"));
        Assertions.assertEquals(creditCard.getInterestRate(), new BigDecimal("0.20"));
    }

    @Test
    void CreditCard_setCreditLimit_customValues() {
        CreditCard creditCard = new CreditCard(money, new BigDecimal("10000"), owner, null, new BigDecimal("0.100001"), creationDate);

        Assertions.assertEquals(creditCard.getCreditLimit(), new BigDecimal("10000"));
        Assertions.assertEquals(creditCard.getInterestRate(), new BigDecimal("0.100001"));
    }

    @Test
    void CreditCard_setCreditLimit_greaterThanDefault() {
        CreditCard creditCard = new CreditCard(money, new BigDecimal("10000"), owner, null, new BigDecimal("0.100001"), creationDate);

        Assertions.assertThrows(IncorrectCreditLimitValueException.class, () -> creditCard.setCreditLimit(new BigDecimal("100001")));
        Assertions.assertThrows(IncorrectCreditLimitValueException.class, () -> {
            new CreditCard(money, new BigDecimal("100001"), owner, null, creationDate);
        });
    }

    @Test
    void CreditCard_setCreditLimit_lessThanZero() {
        CreditCard creditCard = new CreditCard(money, new BigDecimal("10000"), owner, null, new BigDecimal("0.100001"), creationDate);

        Assertions.assertThrows(IncorrectCreditLimitValueException.class,
                () -> creditCard.setCreditLimit(new BigDecimal("-0.0000001")));
        Assertions.assertThrows(IncorrectCreditLimitValueException.class, () -> {
            new CreditCard(money, new BigDecimal("-0.0000001"), owner, null, creationDate);
        });
    }

    @Test
    void CreditCard_setInterestRate_lessDefault() {
        CreditCard creditCard = new CreditCard(money, owner, null, new BigDecimal("0.12"), creationDate);

        Assertions.assertThrows(IncorrectInterestRateValueException.class, () -> creditCard.setInterestRate(new BigDecimal("0.0999999")));
        Assertions.assertThrows(IncorrectInterestRateValueException.class, () -> {
            new CreditCard(money, owner, null, new BigDecimal("0.099999"), creationDate);
        });
    }

    @Test
    void CreditCard_getBalance_creationDateMonthIsNotPassed() {
        CreditCard creditCard = new CreditCard(money, owner, null, new BigDecimal("0.12"), creationDate);

        assertEquals(new BigDecimal("100.00"), creditCard.getBalance().getAmount());
        assertNull(creditCard.getInterestUpdateDate());
    }

    @Test
    void CreditCard_getBalance_monthAfterCreationDate() {
        LocalDate date = LocalDate.of(LocalDate.now().getYear() - 1, 6, 6);
        CreditCard creditCard = new CreditCard(money, owner, null, new BigDecimal("0.12"), date);

        assertEquals(new BigDecimal("112.00"), creditCard.getBalance().getAmount());
        assertEquals(LocalDate.now(), creditCard.getInterestUpdateDate());
        assertEquals(new BigDecimal("112.00"), creditCard.getBalance().getAmount());   // second check - another
        // invoke of getBalance shouldn't return greater balance
    }
}