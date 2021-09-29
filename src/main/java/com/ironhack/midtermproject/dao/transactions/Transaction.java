package com.ironhack.midtermproject.dao.transactions;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
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
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money amount;
    private boolean isFraudDetected;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.zzz")
    private LocalDateTime transferDate;

    @JoinColumn(name="receiver_account_id")
    @ManyToOne
    private Account receiverAccount;

    public Transaction(Money amount, boolean isFraudDetected, Account receiverAccount) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected = isFraudDetected;
        this.receiverAccount = receiverAccount;
    }

    public Transaction(Money amount, Account receiverAccount) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected=false;
        this.receiverAccount = receiverAccount;
    }
}
