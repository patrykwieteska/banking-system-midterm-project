package com.ironhack.midtermproject.dto;

import com.ironhack.midtermproject.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardDto {
    private final AccountType accountType = AccountType.CREDIT_CARD;
    @NotNull
    @Digits(integer = 10,fraction = 2, message = "Wrong decimal format")
    private BigDecimal balanceAmount;
    @NotNull
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @NotNull
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
}
