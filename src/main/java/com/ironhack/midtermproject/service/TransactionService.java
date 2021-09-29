package com.ironhack.midtermproject.service;

import com.ironhack.midtermproject.dao.transactions.AccountTransactionRequest;
import com.ironhack.midtermproject.dao.transactions.ThirdPartTransactionRequest;
import com.ironhack.midtermproject.dao.transactions.TransactionConfirmation;

public interface TransactionService {
    TransactionConfirmation createThirdPartTransaction(ThirdPartTransactionRequest transactionRequest, String hashedKey);

    TransactionConfirmation createAccountTransaction(AccountTransactionRequest transactionRequest, Long accountNumber);
}
