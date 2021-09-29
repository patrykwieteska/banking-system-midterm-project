package com.ironhack.midtermproject.enums;

public enum
TransactionStatus {

    NEW("New"),
    BLOCKED("Blocked"),
    COMPLETED("Completed"),
    ABORTED("Aborted"),
    FROZEN("Frozen");

    public final String tStatus;

    TransactionStatus(String s) {
        this.tStatus=s;
    }

    public String getValue() {
        return tStatus;
    }
}
