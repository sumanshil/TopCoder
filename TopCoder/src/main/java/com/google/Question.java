package com.google;

/**
 * Created by sshil on 2/10/2016.
 */
// Only one programming questions. Input a string, including
// numbers, operands, and brackets. Calculate the result.
// Test case: input: "(+ 3 3)": Output: 6 input "(* 3 3)" Output: 9
// input: "(+ 1 12 (- 17 3) 5 (* 2 8 (/ 120 4)) 46)", Output: 1 + 12 + (17 - 3) + 5 + (2 * 8 * (120 / 4)) + 46 = 558
public class Question {

	public void calculate(String str) {
		int result = calculateRecursive(str);
		System.out.println(result);
	}

	private int calculateRecursive(String str) {
		if (str.indexOf('(',1 ) == -1) {
			String str1 = str.substring(1, str.length()-1);
			char operator = str1.charAt(0);
			String[] arr = str1.split("\\s+");
			int result = 0;
			int operand1 = Integer.parseInt(arr[1]);
			int operand2 = Integer.parseInt(arr[2]);
			result = operate(operator, operand1, operand2);
			for ( int i = 3 ; i < arr.length ;  i++) {
				int operand3 = Integer.parseInt(arr[i]);
				result = operate(operator, result, operand3);
			}
            return  result;
		}
		int leftBraceIndex = -1;
		int rightBraceIndex = -1;
		for ( int i = 0 ; i < str.length() ; i++) {
			if (str.charAt(i) == '(') {
				leftBraceIndex = i;
			}
			if (str.charAt(i) == ')'){
				rightBraceIndex = i;
			}

			if (leftBraceIndex != -1 && rightBraceIndex != -1){
				if (leftBraceIndex > 0 && rightBraceIndex < str.length()-1) {
					int result= calculateRecursive(str.substring(leftBraceIndex, rightBraceIndex+1));
					return calculateRecursive(str.substring(0, leftBraceIndex-1)+" "+result+ str.substring(rightBraceIndex+1));
				} else {
					return calculateRecursive(str.substring(leftBraceIndex+1, rightBraceIndex-1));
				}
			}
		}
		return -1;

	}

	private int operate(char operator, int operand1, int operand2) {
		switch (operator){
			case '+' : return operand1+operand2;
			case '-' : return Math.abs(operand1-operand2);
			case '*' : return operand1*operand2;
			case '/' : return  operand1/operand2;
		}
		return 0;
	}

	public static void main(String[] args) {
		String str = "(+ 1 12 (- 17 3) 5 (* 2 8 (/ 120 4)) 46)";
		new Question().calculate(str);
	}
}
