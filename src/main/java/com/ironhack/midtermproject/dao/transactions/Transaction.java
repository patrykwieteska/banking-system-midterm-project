package com.ironhack.midtermproject.dao.transactions;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
import com.ironhack.midtermproject.dao.owners.Owner;
import com.ironhack.midtermproject.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="transfer_type", discriminatorType = DiscriminatorType.STRING)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Owner transferSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Owner transferReceiver;

    @Embedded
    private Money amount;
    private boolean isFraudDetected;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.zzz")
    private LocalDateTime transferDate;

    @JoinColumn(name="receiver_account_id")
    @ManyToOne
    private Account receiverAccount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public Transaction(Money amount, boolean isFraudDetected, Account receiverAccount, Owner transferSender,
                       Owner transferReceiver,TransactionStatus status) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected = isFraudDetected;
        this.receiverAccount = receiverAccount;
        this.transferSender=transferSender;
        this.transferReceiver=transferReceiver;
        this.status=status;

    }

    public Transaction(Money amount, Account receiverAccount, Owner transferSender, Owner transferReceiver,
                       TransactionStatus status) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected=false;
        this.receiverAccount = receiverAccount;
        this.transferSender=transferSender;
        this.transferReceiver=transferReceiver;
        this.status=status;
    }
}
