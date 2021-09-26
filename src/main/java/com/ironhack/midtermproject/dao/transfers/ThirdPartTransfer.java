package com.ironhack.midtermproject.dao.transfers;

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
public class ThirdPartTransfer extends Transfer{

    public ThirdPartTransfer(Money amount, boolean isFraudDetected, Account receiverAccount) {
        super(amount, isFraudDetected, receiverAccount);
    }

    public ThirdPartTransfer(Money amount, Account receiverAccount) {
        super(amount, receiverAccount);
    }
}
