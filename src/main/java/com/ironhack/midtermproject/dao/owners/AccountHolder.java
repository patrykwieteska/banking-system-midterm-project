package com.ironhack.midtermproject.dao.owners;

import com.ironhack.midtermproject.dao.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends AccountOwner {

    private Date dateOfBirth;
    private Address primaryAddress;
    private Address mailingAddress;
}
