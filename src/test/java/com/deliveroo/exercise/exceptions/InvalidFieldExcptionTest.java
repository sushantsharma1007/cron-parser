package com.deliveroo.exercise.exceptions;

import org.junit.Test;

public class InvalidFieldExcptionTest {
	@Test(expected= InvalidFieldException.class) 
	public void myTest() throws InvalidFieldException { 
	  throw new InvalidFieldException("Testing exception class"); 
	} 
}
