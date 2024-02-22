package com.olegmng.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Auto.class, AutoService.class} )
class AutoServiceTest {

    @Autowired
    private AutoService autoService;

    @MockBean
    private Auto auto;

    @Test
    public void testChangeWheelsInOk(){
        //Testing with Spy (proxi from original Bean)
//        Auto autoSpy = new Auto();
//        Auto spy = Mockito.spy(autoSpy);
//        Mockito.verify(spy).changeWheels();

        Mockito.doNothing().when(auto).changeWheels();
        Assertions.assertTrue(autoService.changeWheelsIn());
    }
    @Test
    public void testChangeWheelsInError(){
        Mockito.doThrow(RuntimeException.class).when(auto).changeWheels();
        Assertions.assertEquals(false, autoService.changeWheelsIn());
    }

    //SpringTest
//    @BeforeTestClass
//    @BeforeTransaction

}