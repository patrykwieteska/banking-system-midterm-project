package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checking extends Account {

    @JoinColumn(unique = true)
    private String secretKey;

    @Digits(integer = 3,fraction = 2)
    private BigDecimal minimumBalance;

    @Digits(integer = 3,fraction = 2)
    private BigDecimal monthlyMaintenanceFee;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.setMinimumBalance();
        this.setMonthlyMaintenanceFee();
        this.status = status;
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.setMinimumBalance();
        this.setMonthlyMaintenanceFee();
        this.status = Status.ACTIVE;
    }


    // setter for minimum balance (Constant value according to requirements)
    public void setMinimumBalance() {
        this.minimumBalance = new BigDecimal("250.00");
    }

    // setter for minimum balance (Constant value according to requirements)
    public void setMonthlyMaintenanceFee() {
        this.monthlyMaintenanceFee = new BigDecimal("12.00");
    }
}
