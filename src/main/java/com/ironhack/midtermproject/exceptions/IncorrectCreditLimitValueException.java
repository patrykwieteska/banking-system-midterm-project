package com.ironhack.midtermproject.exceptions;

public class IncorrectCreditLimitValueException extends RuntimeException{

    public IncorrectCreditLimitValueException(String errorMessage) {
        super(errorMessage);
    }
}
