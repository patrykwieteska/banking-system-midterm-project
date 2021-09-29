package com.ironhack.midtermproject.dao.transactions;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
@Entity
@AllArgsConstructor
@Setter
@Getter
public class ThirdPartTransaction extends Transaction {

    public ThirdPartTransaction(Money amount, boolean isFraudDetected, Account receiverAccount) {
        super(amount, isFraudDetected, receiverAccount);
    }

    public ThirdPartTransaction(Money amount, Account receiverAccount) {
        super(amount, receiverAccount);
    }
}
