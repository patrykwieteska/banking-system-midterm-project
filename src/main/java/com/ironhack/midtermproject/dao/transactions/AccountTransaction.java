package com.ironhack.midtermproject.dao.transactions;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
import com.ironhack.midtermproject.dao.owners.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Owner transferSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Owner transferReceiver;


    public AccountTransaction(Money amount, Owner transferSender, Owner transferReceiver, boolean isFraudDetected,
                              Account receiverAccount) {
        super(amount,isFraudDetected,receiverAccount);
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
    }

    public AccountTransaction(Money amount, Owner transferSender, Owner transferReceiver, Account receiverAccount) {
        super(amount,receiverAccount);
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
    }
}
