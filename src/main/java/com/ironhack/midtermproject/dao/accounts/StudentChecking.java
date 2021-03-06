package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.AccountType;
import com.ironhack.midtermproject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentChecking extends Account{

    @JoinColumn(unique = true)
    @Enumerated(EnumType.STRING)
    private Status status;


    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, AccountType.STUDENT_CHECKING,secretKey);
        this.status=Status.ACTIVE;
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
     Status status) {
        super(balance, primaryOwner, secondaryOwner,AccountType.STUDENT_CHECKING,secretKey);
        this.status=status;
    }
}
