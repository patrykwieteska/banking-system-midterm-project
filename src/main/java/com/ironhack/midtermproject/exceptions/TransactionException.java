package com.ironhack.midtermproject.exceptions;

public class TransactionException extends RuntimeException{

    public TransactionException(String errorMessage) {
        super(errorMessage);
    }
}
