package com.ironhack.midtermproject.service;

import com.ironhack.midtermproject.dto.AccountTransactionRequest;
import com.ironhack.midtermproject.dto.ThirdPartTransactionRequest;
import com.ironhack.midtermproject.dto.TransactionConfirmation;

public interface TransactionService {
    TransactionConfirmation createThirdPartTransaction(ThirdPartTransactionRequest transactionRequest, String hashedKey);

    TransactionConfirmation createAccountTransaction(AccountTransactionRequest transactionRequest, Long accountNumber);
}
