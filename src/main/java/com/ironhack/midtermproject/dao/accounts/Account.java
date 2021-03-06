package com.ironhack.midtermproject.dao.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.AccountType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="amount", column = @Column(name="balance"))
    })
    private Money balance;

    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    @NotNull
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    @Nullable
    private AccountHolder secondaryOwner;

    @NotNull
    @DecimalMax(value = "999.99", message = "Penalty fee should be less than 1,000.00")
    @DecimalMin(value = "0.0", message = "Penalty fee should be greater than 0.00")
    @Digits(integer=3, fraction = 2, message = "Incorrect penalty fee")
    private BigDecimal penaltyFee;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.zzz")
    private LocalDateTime lastModificationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @JoinColumn(unique = true)
    private String secretKey;

    public Account(Money balance, @NotNull AccountHolder primaryOwner, @Nullable AccountHolder secondaryOwner,
                   AccountType accountType,String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.setPenaltyFee();
        this.creationDate=LocalDate.now();
        this.accountType=accountType;
        this.secretKey=secretKey;
        this.lastModificationDate=LocalDateTime.now();
    }

    private void setPenaltyFee() {
        this.penaltyFee = new BigDecimal("40");
    }

    public Money getBalance() {
        return balance;
    }
}
