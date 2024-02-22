package com.olegmng.junit;

public class Calculator {
    private final CalculatorHistory calculatorHistory;

    public Calculator(CalculatorHistory calculatorHistory) {
        this.calculatorHistory = calculatorHistory;
    }

    public int sum(int a, int b) {
        int res = a + b;
        calculatorHistory.logSumOperation(a, b, res);
        return a + b;
    }
}
