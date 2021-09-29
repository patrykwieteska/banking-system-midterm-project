package com.ironhack.midtermproject.dto;

import com.ironhack.midtermproject.dao.owners.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionRequest {

    Long accountOwnerId;
    Long accountId;
    BigDecimal transactionAmount;

}
