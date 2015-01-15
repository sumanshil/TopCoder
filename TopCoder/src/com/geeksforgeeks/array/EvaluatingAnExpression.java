package com.geeksforgeeks.array;

import java.util.Stack;

public class EvaluatingAnExpression {
   // will return true if op2 has higher or equal precedence than op1
	private static boolean hasPrecedence(char op1, char op2){
		if (op2 == '(' || op2 == ')'){
			return false;
		} else if (((op1 == '*') || (op1 == '/'))&&((op2 == '+') || (op2 == '-'))){
			return false;
		} else {
			return true;
		}
	}
	
	public static int getResult(int a , int b, char operator){
		switch (operator) {
		case '+':
			    return a+b;
		case '-':
			    return a-b;
		case '*':
			    return a*b;
		case '/':
			    return a/b;
		}
		return 0;
	}
	
	public static boolean isOperator(char c){
		if (c =='*' || c=='/' || c=='+' || c=='-'){
			return true;
		}
		return false;
	}
	// a + b * c
	public static int calculate(String str){
		Stack<Character> operatorStack = new Stack<Character>();
		Stack<Integer> operandStack = new Stack<Integer>();
		for(int i = 0 ; i < str.length(); i++){
			char c = str.charAt(i);
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
