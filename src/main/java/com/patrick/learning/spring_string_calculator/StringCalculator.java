package com.patrick.learning.spring_string_calculator;

public class StringCalculator {
	private static int sum;
	
	
	
	public static void setSum(int setSum){ 		// setter used to reset "sum" value in unit tests
		sum = setSum;
	}
	
	
	
	public static int add(String numbers) {
		if (numbers.isEmpty()) { 	//returns 0 if numbers is empty
			return 0;
		} else {
			String[] splitNumbers = StringCalculator.stringSplitter(numbers); 	//invokes stringSplitter method on numbers turning string into array
			for (String sumString : splitNumbers) { 	//iterates over array
				if(Integer.parseInt(sumString) >= 1001){ 	//skips numbers greater than 1000
					continue;
				}
				if (Integer.parseInt(sumString) < 0){
					throw new IllegalArgumentException("Negative numbers not allowed");
				}
				sum += Integer.parseInt(sumString); 	//adds all elements together
			}
			return sum;
		}
	}

	
	
	private static String[] stringSplitter(String numberString) {
		String numbers = numberString;
		
		if (numbers.startsWith("//") && !numbers.contains("[")) {
			char delimiter = numbers.charAt(2); 	// finds delimiter after the "//"
			
			String delimString = String.valueOf(delimiter);		// converts delimiter char into a string
			String numbersSub = numbers.substring(returnFirstIntegerIndex(numbers)); 	// isolates substring that is to be split using returnFirstIntegerIndex
			String[] split = numbersSub.split(delimString);
			
			return split;
		} else if (numbers.startsWith("//[")) {		// allows any size delimiter
			String currentDelimiter = numbers.substring(numbers.indexOf("[") + 1, numbers.indexOf("]")); 	// isolates delimiter
			String numbersWithNewDelimiter = numbers.replaceAll(currentDelimiter, ","); //changes number string's delimiter to a comma
			String newDelimiter = numbersWithNewDelimiter.substring(numbersWithNewDelimiter.indexOf("[") + 1, numbersWithNewDelimiter.indexOf("]")); //isolates the new delimiter
			
			String numbersSub = numbersWithNewDelimiter.substring(returnFirstIntegerIndex(numbersWithNewDelimiter)); 	// isolates substring that is to be split using returnFirstIntegerIndex
			String[] split = numbersSub.split(newDelimiter);
			
			return split;
		} else {
			String[] split = numbers.split(",|\\n");
			return split;
		}
	}
	
	
	
	public static Integer returnFirstIntegerIndex(String s) {
		int indexValue = 0;
		char[] charArray = s.toCharArray();
		for (int i = 0; i<charArray.length; i++){
			if (Character.isDigit(charArray[i])){
				indexValue = i;
				break;
			}
			
		}return indexValue;
	}
}