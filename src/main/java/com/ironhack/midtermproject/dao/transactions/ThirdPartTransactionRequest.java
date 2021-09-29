package com.ironhack.midtermproject.dao.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartTransactionRequest {

    @NotNull
    private Long accountId;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    private String accountSecretKey;

}
