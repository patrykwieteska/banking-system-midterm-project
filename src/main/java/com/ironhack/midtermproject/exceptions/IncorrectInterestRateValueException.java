package com.ironhack.midtermproject.exceptions;

public class IncorrectInterestRateValueException extends RuntimeException{

    public IncorrectInterestRateValueException(String errorMessage) {
        super(errorMessage);
    }
}
