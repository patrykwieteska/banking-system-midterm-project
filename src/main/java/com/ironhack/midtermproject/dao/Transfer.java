package com.ironhack.midtermproject.dao;

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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Owner transferSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Owner transferReceiver;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.zzz")
    private LocalDateTime transferDate;

    private boolean isFraudDetected;

    public Transfer(Money amount, Owner transferSender, Owner transferReceiver, LocalDateTime transferDate, boolean isFraudDetected) {
        this.amount = amount;
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
        this.transferDate = transferDate;
        this.isFraudDetected = isFraudDetected;
    }

    public Transfer(Money amount, Owner transferSender, Owner transferReceiver, LocalDateTime transferDate) {
        this.amount = amount;
        this.transferSender = transferSender;
        this.transferReceiver = transferReceiver;
        this.transferDate = transferDate;
        this.isFraudDetected = false;
    }
}
