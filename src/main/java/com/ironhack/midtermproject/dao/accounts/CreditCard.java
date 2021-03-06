package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.common.Calculator;
import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.AccountType;
import com.ironhack.midtermproject.exceptions.IncorrectCreditLimitValueException;
import com.ironhack.midtermproject.exceptions.IncorrectInterestRateValueException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends Account {

    @Digits(integer = 6,fraction = 2)
    @DecimalMax(value = "100000.0", message = "Credit limit cannot be greater tha 100,000.00")
    private BigDecimal creditLimit;

    @DecimalMin(value = "0.0", message = "Interest rate cannot be less than 0.00")
    @DecimalMax(value = "0.2", message = "Interest rate cannot be greater than 0.20")
    @Digits(integer = 1,fraction = 2)
    private BigDecimal interestRate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate interestUpdateDate;


    public CreditCard(Money balance, BigDecimal creditLimit, AccountHolder primaryOwner, AccountHolder secondaryOwner
            , BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, AccountType.CREDIT_CARD,null);
        this.setCreditLimit(creditLimit);
        this.setInterestRate(interestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, AccountType.CREDIT_CARD,null);
        this.creditLimit = new BigDecimal("100.00");
        this.setInterestRate(interestRate);
    }

    public CreditCard(Money balance, BigDecimal creditLimit, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner, AccountType.CREDIT_CARD,null);
        this.setCreditLimit(creditLimit);
        this.interestRate = new BigDecimal("0.20");
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner, AccountType.CREDIT_CARD,null);
        this.creditLimit = new BigDecimal("100.00");
        this.interestRate = new BigDecimal("0.20");
    }


    public void setCreditLimit(BigDecimal creditLimit) {
        if(creditLimit.compareTo(new BigDecimal("0.00"))<0)
            throw new IncorrectCreditLimitValueException("Credit limit cannot be less than 0");

        if(creditLimit.compareTo(new BigDecimal("100000.00"))>0)
            throw new IncorrectCreditLimitValueException("Credit limit cannot be greater than 100,000");

        this.creditLimit = creditLimit;
    }

    public void setInterestRate(BigDecimal interestRate) {

        if(interestRate.compareTo(new BigDecimal("0.10"))<0)
            throw new IncorrectInterestRateValueException("Interest rate cannot be less than 0.10");

        this.interestRate = interestRate;
    }


    @Override
    // Updating balance by interestRate according to requirements - after balance access it will check if the year
    // after last update or create is passed
    public Money getBalance() {
        BigDecimal currentInterestValue = super.getBalance().getAmount().multiply(getInterestRate());

        if(interestUpdateDate != null) {
            if(Calculator.isMonthGetPassed(this.getInterestUpdateDate())) {
                this.interestUpdateDate = LocalDate.now();
                return new Money (super.getBalance().increaseAmount(currentInterestValue).setScale(2, RoundingMode.HALF_EVEN));

            }
        } else {
            if(Calculator.isMonthGetPassed(this.getCreationDate())) {
                this.interestUpdateDate = LocalDate.now();
                return new Money (super.getBalance().increaseAmount(currentInterestValue).setScale(2, RoundingMode.HALF_EVEN));
            }
        }

        return new Money (super.getBalance().getAmount().setScale(2,RoundingMode.HALF_EVEN));
    }
}
