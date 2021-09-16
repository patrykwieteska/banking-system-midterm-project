package com.ironhack.midtermproject.dao.accounts;

import com.ironhack.midtermproject.enums.Status;
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
public class StudentChecking extends Account{

    private String secretKey;
    private Date creationDate;
    private Status status;
}
