package com.ironhack.midtermproject.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void Calculator_isYearGetPassed_leapYear() {

        assertTrue(Calculator.isYearGetPassed(LocalDate.of(2016, 12, 12)));
    }

    @Test
    void Calculator_isYearGetPassed_notLeapYear() {

        assertFalse(Calculator.isYearGetPassed(LocalDate.of(2021, 12, 12)));
    }


    //TODO tests for method isMonthGetPassed
    @Test
    void Calculator_isMonthGetPassed() {
    }
}