package com.deliveroo.exercise.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.deliveroo.exercise.exceptions.InvalidFieldException;

public class ExpressionParserTest {
	
	@Test
	public void testCorrectExpression() throws InvalidFieldException {
		ExpressionParser parser = new ExpressionParser("*/15 1-5 1-15 * 1,5 /usr/bin/find");
		String output="Minute        0 15 30 45\n"
				+ "Hour          1 2 3 4 5\n"
				+ "Day Of Month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
				+ "Month         1 2 3 4 5 6 7 8 9 10 11 12\n"
				+ "Day Of Week   1 5\n"
				+ "Command       /usr/bin/find\n"
				+ "";
		assertEquals(parser.toString(), output);
	}
	
	@Test(expected = InvalidFieldException.class)
	public void testInCorrectExpression() throws InvalidFieldException {
		new  ExpressionParser("*/15 1-5 1-15 *-15 1,5 /usr/bin/find");
	}
	@Test(expected = InvalidFieldException.class)
	public void testInCorrectInputsExpression() throws InvalidFieldException {
		new  ExpressionParser("*/15 1-5 1-15 *-15 1,5 /usr/bin/find find");
	}

}
