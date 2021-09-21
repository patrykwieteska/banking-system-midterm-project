package com.ironhack.midtermproject.exceptions;

public class   IncorrectMinimumBalanceValueException extends RuntimeException{

    public IncorrectMinimumBalanceValueException(String errorMessage) {
        super(errorMessage);
    }
}
