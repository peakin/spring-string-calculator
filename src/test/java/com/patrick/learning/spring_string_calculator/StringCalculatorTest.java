package com.patrick.learning.spring_string_calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.patrick.learning.spring_string_calculator.StringCalculator;

public class StringCalculatorTest {
	
	@Before
	public void clearInstanceVariables() {
		StringCalculator.setSum(0);		// initializes return value to zero before each test
	}

	@Test
	public void shouldReturnZeroOnEmptyString() {
		assertEquals(0, StringCalculator.add(""));
	}

	@Test
	public void shouldAllowUnknownNumberOfNumbers() {
		assertEquals(1 + 2 + 3 + 4 + 53 + 6 + 7 + 8 + 9, StringCalculator.add("1,2,3,4,53,6,7,8,9"));
	}

	@Test
	public void shouldAcceptNewLineAsDelimiter() {
		assertEquals(1 + 23 + 3 + 4, StringCalculator.add("1\n23\n3\n4"));
	}

	@Test
	public void shouldSupportDifferentDelimiters() {
		assertEquals(1 + 2 + 3 + 4, StringCalculator.add("//!\n1!2!3!4"));
		StringCalculator.setSum(0);
		assertEquals(1 + 3 + 34 + 4, StringCalculator.add("//#\n1#3#34#4"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void doesNotAcceptNegativeNumbers(){
		StringCalculator.add("1,-9,2");
		StringCalculator.setSum(0); 									
		StringCalculator.add("//!\n-3!9!-3!4");
	}
	
	@Test
	public void numbersGreaterThan1000ShouldBeIgnored(){
		assertEquals(1 + 2 + 4, StringCalculator.add("1,2,1001,4"));
	}
	
	@Test
	public void shouldSupportDelimitersOfAnySize(){
		assertEquals(1+2+3, StringCalculator.add("//[d3l!m!ter]\n1d3l!m!ter2d3l!m!ter3"));
	}
}