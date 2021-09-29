package com.ironhack.midtermproject.enums;

public enum AccountType {

    SAVINGS("Savings"),
    CREDIT_CARD("Credit card"),
    CHECKING("Checking"),
    STUDENT_CHECKING("Student checking");

    private final String value;

    AccountType(String s) {
        this.value=s;
    }
}
