package com.ironhack.midtermproject.controller;

import com.ironhack.midtermproject.dto.AccountTransactionRequest;
import com.ironhack.midtermproject.dto.ThirdPartTransactionRequest;
import com.ironhack.midtermproject.dto.TransactionConfirmation;
import com.ironhack.midtermproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/third-part")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionConfirmation createThirdPartTransaction(@RequestHeader("hashed-key") String hashedKey,
                                                              @RequestBody @Valid ThirdPartTransactionRequest transactionRequest) {

        return transactionService.createThirdPartTransaction(transactionRequest,hashedKey);

    }

    @PostMapping("/regular")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionConfirmation createAccountHolderTransaction(@RequestHeader("account-number") Long senderAccountNumber,
                                                              @RequestBody @Valid AccountTransactionRequest transactionRequest) {

        return transactionService.createAccountTransaction(transactionRequest, senderAccountNumber);

    }


}
