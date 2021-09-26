package com.ironhack.midtermproject.dao.transfers;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.Account;
import com.ironhack.midtermproject.dao.owners.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transfer {

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

    public Transfer(Money amount, boolean isFraudDetected, Account receiverAccount) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected = isFraudDetected;
        this.receiverAccount = receiverAccount;
    }

    public Transfer(Money amount, Account receiverAccount) {
        this.amount = amount;
        this.transferDate = LocalDateTime.now();
        this.isFraudDetected=false;
        this.receiverAccount = receiverAccount;
    }
}
