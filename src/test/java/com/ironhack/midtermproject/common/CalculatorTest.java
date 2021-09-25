package com.ironhack.midtermproject.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

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

    @Test
    void Calculator_isMonthGetPassed_passed() {
        assertTrue(Calculator.isMonthGetPassed(LocalDate.now().minusDays(32)));
    }

    @Test
    void Calculator_isMonthGetPassed_notPassed() {
        assertFalse(Calculator.isMonthGetPassed(LocalDate.now().minusDays(27)));
    }
}