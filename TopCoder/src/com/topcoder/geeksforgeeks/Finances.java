package com.topcoder.geeksforgeeks;

public class Finances {
	//http://community.topcoder.com/stat?c=problem_statement&pm=52&rd=3000
	public static int timeLeft(int initialValue, int interest, int monthly){
		int result = calculate(initialValue, interest, monthly, 0);
		return result;
	}
	
	public static int calculate(double initialValue, double interest, int monthly, int timeLeft){
		initialValue =(initialValue + (initialValue*(.01*interest)))-monthly;
		if (initialValue <0.0 || timeLeft >1199){
			return timeLeft;
		}
		
		int result = calculate(initialValue, interest, monthly, ++timeLeft);
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = timeLeft(100, 0, 100);
		//int result = timeLeft(200, 1, 300);
		//int result = timeLeft(3453, 8, 200);
		System.out.println(result);

	}

}
