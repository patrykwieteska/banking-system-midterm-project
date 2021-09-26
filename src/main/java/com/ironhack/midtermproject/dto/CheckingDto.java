package com.ironhack.midtermproject.dto;

import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;

@Getter
@Setter
@AllArgsConstructor
public class CheckingDto {

    @Digits(integer = 10,fraction = 2)
    String balanceAmount;
    Integer primaryOwnerId;
    Integer secondaryOwnerId;
    String secretKey;
    Status status;

}
