package com.ironhack.midtermproject.common;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class Calculator {

    // check if the year is passed
    public static boolean isYearGetPassed(LocalDate date) {
        if(Year.of(date.getYear()).isLeap())
            return ChronoUnit.DAYS.between(date,LocalDate.now())>=366;

        return ChronoUnit.DAYS.between(date,LocalDate.now())>=365;
    }

    // check if the month is passed
    public static boolean isMonthGetPassed(LocalDate date) {
        LocalDate now = LocalDate.now();
        if(now.getYear() - date.getYear() >1) {
            return true;
        } else if(now.getYear()>date.getYear() && date.getMonthValue() - now.getMonthValue() < 11) {
            return true;
        } else if(now.getYear() > date.getYear() && now.getDayOfYear() >= date.getDayOfMonth()) {
                return true;
        } else if (now.getYear()==date.getYear() && now.getMonthValue() - date.getMonthValue() >1) {
            return true;
        } else return now.getYear() == date.getYear() && now.getMonthValue() > date.getMonthValue() && now.getDayOfMonth() >= date.getDayOfMonth();
    }
}
