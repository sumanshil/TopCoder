package com.topcoder.geeksforgeeks;

public class FindSumOfDigits {

	public static int getSumOfDigits(int number){
		if (number == 0){
			return 0;
		}
		
		int remainder = number %10;
		int remainingNumber = number /10;
		
		int result = getSumOfDigits(remainingNumber);
		return result + remainder;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
         int number = 345678;
         int result = getSumOfDigits(number);
         System.out.println(result);
	}

}
