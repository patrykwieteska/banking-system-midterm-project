package com.ironhack.midtermproject.dto;


import com.ironhack.midtermproject.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionConfirmation {

    private TransactionStatus status;
    @Digits(fraction = 2,integer = 9)
    BigDecimal amount;
    private String desc;

}
