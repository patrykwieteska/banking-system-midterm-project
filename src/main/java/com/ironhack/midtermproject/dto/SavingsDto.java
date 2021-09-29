package com.ironhack.midtermproject.dto;

import com.ironhack.midtermproject.enums.AccountType;
import com.ironhack.midtermproject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class SavingsDto {
    private final AccountType accountType = AccountType.SAVINGS;
    @NotNull
    @Digits(integer = 10,fraction = 2, message = "Wrong decimal format")
    private BigDecimal balanceAmount;
    @NotNull
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @NotNull
    private String secretKey;
    private Status status;
    @NotNull
    private BigDecimal minimumBalance;
    @NotNull
    private BigDecimal interestRate;


}
