package com.ironhack.midtermproject.exceptions;

public class IncorrectBalanceAmountException extends RuntimeException{

    public IncorrectBalanceAmountException(String errorMessage) {
        super(errorMessage);
    }
}
