package com.ledelsea.training.java.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculationTest {
	@Before  
    public void setUp() throws Exception {  
        System.out.println("before");  
    }  
	
	@Test  
    public void testFindMax1(){  
        assertEquals(4,Calculation.findMax(new int[]{1,3,4,2}));  
    }  
	
	@Test  
    public void testFindMax2(){  
        assertEquals(-1,Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));  
    }  
	
	@After  
    public void tearDown() throws Exception {  
        System.out.println("after");  
    }  
}