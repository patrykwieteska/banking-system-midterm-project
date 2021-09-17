package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.Owner;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
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

    @NotNull
    private String secretKey;

    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    @NotNull
    private Owner primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    @Nullable
    private Owner secondaryOwner;

    @NotNull
    private BigDecimal penaltyFee;


}
