package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="account_type", discriminatorType = DiscriminatorType.STRING)
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
    private BigDecimal penaltyFee;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate creationDate;

    public Account(Money balance, @NotNull AccountHolder primaryOwner, @Nullable AccountHolder secondaryOwner,
                   LocalDate creationDate) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.setPenaltyFee();
        this.creationDate=creationDate;
    }

    private void setPenaltyFee() {
        this.penaltyFee = new BigDecimal("40");
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal amount) {

    }
}
