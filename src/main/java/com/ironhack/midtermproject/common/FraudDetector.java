package com.ironhack.midtermproject.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FraudDetector {

    //Below methods return true if transaction is correct

    public static boolean isTwoTransactionsInOneSecond(LocalDateTime lastTransactionDate) {

        System.out.println("ILE SEKUND MORDO?" + ChronoUnit.MILLIS.between(lastTransactionDate,LocalDateTime.now()));
        return ChronoUnit.MILLIS.between(lastTransactionDate,LocalDateTime.now()) > 1000;


    }
    //TODO
    public static boolean checkRiskOf24hoursTransactions() {
        return true;
    }
}
