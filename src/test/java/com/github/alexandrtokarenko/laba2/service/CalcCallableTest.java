package com.github.alexandrtokarenko.laba2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcCallableTest {


    private CalcCallable calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalcCallable();
    }

    @Test
    public void calculateTest() {

        double a = 1;
        double b = 9;
        int n = 1000;

        double expected = 171.9;
        calculator.calculate(a,b,n);
        double result = calculator.getTotalSum();
        assertEquals(expected,result,1e-1);
    }
}