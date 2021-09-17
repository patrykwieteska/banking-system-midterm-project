package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends Account {

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="amount", column = @Column(name="credit_limit"))
    })
    private Money creditLimit;
    private BigDecimal interestRate;
}
