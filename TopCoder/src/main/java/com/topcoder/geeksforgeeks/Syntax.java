package com.topcoder.geeksforgeeks;

import java.util.Stack;

public class Syntax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String str = "x(y(z{test})abc)";
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0 ; i < str.length(); i++){
        	if (shouldBePushed(str.charAt(i))){
        		stack.push(str.charAt(i));
        	} else {
        		char c = str.charAt(i);
        		if(isParenthesis(c)&&!matching(c,stack)){
        			System.out.println("false");
        			return;
        		} else if (isParenthesis(c)){
        		    stack.pop();
        		}
        	}
        }

        System.out.println("true");
	}

	private static boolean isParenthesis(char c) {		
		return (c=='}' ||c==']' || c==')');
	}

	private static boolean matching(char c, Stack<Character> stack) {		
		return (c == '}'&&stack.peek()=='{') ||(c == ']'&&stack.peek()=='[') ||(c == ')'&&stack.peek()=='(');
	}

	private static boolean shouldBePushed(char c) {		
		return (c=='[' || c=='(' || c == '{');
	}

}
