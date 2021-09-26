package com.ironhack.midtermproject.dao.transfers;

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
public class AccountTransfer extends Transfer {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Owner transferSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Owner transferReceiver;


    public AccountTransfer(Money amount, Owner transferSender, Owner transferReceiver, boolean isFraudDetected,
                           Account receiverAccount) {
        super(amount,isFraudDetected,receiverAccount);
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
    }

    public AccountTransfer(Money amount, Owner transferSender, Owner transferReceiver, Account receiverAccount) {
        super(amount,receiverAccount);
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
    }
}
