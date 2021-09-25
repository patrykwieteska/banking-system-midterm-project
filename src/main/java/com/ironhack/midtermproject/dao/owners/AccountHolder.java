package com.ironhack.midtermproject.dao.owners;

import com.ironhack.midtermproject.dao.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends Owner {

    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "primary_address_id")
    private Address primaryAddress;
    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress;

    public int getAge() {
        return Period.between(this.getDateOfBirth(),LocalDate.now()).getYears();
    }
}
