package com.ironhack.midtermproject.dao.accounts;


import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountOwner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Money balance;
    String secretKey;
    AccountOwner primaryOwner;


}
