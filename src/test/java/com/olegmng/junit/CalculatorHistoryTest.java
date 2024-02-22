package com.olegmng.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CalculatorHistoryTest {

    @Test
    public void testLogSum() {
        Storage mock = Mockito.mock(Storage.class);
        CalculatorHistory calculatorHistory = new CalculatorHistory(mock);
        Mockito.when(mock.save("1 + 2 = 3")).thenReturn(true);

        calculatorHistory.logSumOperation(1, 2, 3);
    }


    @Test
    public void testLogSumStorage() {
        Storage mock = Mockito.mock(Storage.class);
        CalculatorHistory calculatorHistory = new CalculatorHistory(mock);
        Mockito.when(mock.save("1 + 2 = 3")).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> calculatorHistory.logSumOperation(1, 2, 3));
    }

    @Test
    public void testLogSumStorageRuntimeOperation() {
        Storage mock = Mockito.mock(Storage.class);
        CalculatorHistory calculatorHistory = new CalculatorHistory(mock);
        Mockito.when(mock.save("1 + 2 = 3")).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class,() -> calculatorHistory.logSumOperation(1, 2, 3));
    }

}