package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Savings extends Account{

    private String secretKey;
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="amount", column = @Column(name="minimum_balance"))
    })
    private Money minimumBalance;
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate;
}
