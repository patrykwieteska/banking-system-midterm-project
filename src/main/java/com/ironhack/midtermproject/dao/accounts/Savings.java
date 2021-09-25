package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.common.Calculator;
import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.exceptions.IncorrectInterestRateValueException;
import com.ironhack.midtermproject.exceptions.IncorrectMinimumBalanceValueException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Savings extends Account{

    private String secretKey;

    @DecimalMin(value = "0.0",message = "Minimum balance cannot be less than 0")
    private BigDecimal minimumBalance;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate interestUpdateDate;

    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate;

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
                   BigDecimal minimumBalance, LocalDate creationDate, Status status, BigDecimal interestRate,
                   LocalDate interestUpdateDate) {
        super(balance, primaryOwner, secondaryOwner, creationDate);
        this.secretKey = secretKey;
        this.setMinimumBalance(minimumBalance);
        this.status = status;
        this.setInterestRate(interestRate);
        this.interestUpdateDate=interestUpdateDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
                   BigDecimal minimumBalance, LocalDate creationDate, Status status, LocalDate interestUpdateDate) {
        super(balance, primaryOwner, secondaryOwner,creationDate);
        this.secretKey = secretKey;
        this.setMinimumBalance(minimumBalance);
        this.status = status;
        this.interestRate = new BigDecimal("0.0025");
        this.interestUpdateDate=interestUpdateDate;
    }


    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   String secretKey, LocalDate creationDate, Status status, BigDecimal interestRate, LocalDate interestUpdateDate) {
        super(balance, primaryOwner, secondaryOwner,creationDate);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal("1000.00");
        this.status = status;
        this.setInterestRate(interestRate);
        this.interestUpdateDate=interestUpdateDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
                   LocalDate creationDate, Status status, LocalDate interestUpdateDate) {
        super(balance, primaryOwner, secondaryOwner,creationDate);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal("1000.00");
        this.status = status;
        this.interestRate = new BigDecimal("0.0025");
        this.interestUpdateDate=interestUpdateDate;
    }


    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(new BigDecimal("0.00")) < 0)
            throw new IncorrectInterestRateValueException("Interest rate cannot be less than 0");

        if(interestRate.compareTo(new BigDecimal("0.50")) > 0)
            throw new IncorrectInterestRateValueException("Interest rate cannot be greater than 0.50");

        this.interestRate=interestRate;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {

        if(minimumBalance.compareTo(new BigDecimal("100.00")) <0)
            throw new IncorrectMinimumBalanceValueException("Minimum balance cannot be less than 100");

        this.minimumBalance = minimumBalance;
    }


    @Override
    // Updating balance by interestRate according to requirements - after balance access it will check if the year
    // after last update or create is passed
    public Money getBalance() {
        BigDecimal currentInterestValue = super.getBalance().getAmount().multiply(getInterestRate());

        if(interestUpdateDate != null) {
            if(Calculator.isYearGetPassed(this.getInterestUpdateDate())) {
                this.interestUpdateDate = LocalDate.now();
                return new Money (super.getBalance().increaseAmount(currentInterestValue).setScale(2, RoundingMode.HALF_EVEN));

            }
        } else {
            if(Calculator.isYearGetPassed(this.getCreationDate())) {

                this.interestUpdateDate = LocalDate.now();
                return new Money (super.getBalance().increaseAmount(currentInterestValue).setScale(2, RoundingMode.HALF_EVEN));
            }
        }

        return new Money (super.getBalance().getAmount().setScale(2,RoundingMode.HALF_EVEN));
    }
}
