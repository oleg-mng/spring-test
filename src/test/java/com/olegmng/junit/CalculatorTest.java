package com.olegmng.junit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {


//    @Test
//    public void testSum(){
//        Calculator calculator = new Calculator(new CalculatorHistory());
//        int testSum = calculator.sum(3, 4);
//        assertEquals(testSum, 7);
//    }

    @Test
    public void testLogOperation(){
        CalculatorHistory calculatorHistoryMock = Mockito.mock(CalculatorHistory.class);
        Calculator calculator = new Calculator(calculatorHistoryMock);
        int testSum = calculator.sum(3, 4);
        assertEquals(testSum, 7);
        Mockito.verify(calculatorHistoryMock).logSumOperation(3, 4 ,7);
    }




}