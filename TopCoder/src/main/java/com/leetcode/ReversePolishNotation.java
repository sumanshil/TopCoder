package com.leetcode;

import java.util.HashSet;
import java.util.Stack;

//https://leetcode.com/problems/evaluate-reverse-polish-notation/
public class ReversePolishNotation {

    /*
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        Stack<String> stack = new Stack<>();
        int finalResult = 0;
        for (int i = tokens.length -1 ; i >= 0; i--) {
            String token = tokens[i];
            if (isOperator(token)) {
                stack.push(token);
            } else {
                if (isOperator(stack.peek())) {
                    stack.push(token);
                } else {
                    stack.push(token);
                    while(!stack.isEmpty() && stack.size() > 2) {
                        String op1 = stack.elementAt(stack.size()-1);
                        String op2 = stack.elementAt(stack.size()-2);
                        String operator = stack.elementAt(stack.size()-3);
                        if (isOperator(operator) && !isOperator(op1) && !isOperator(op2)) {
                            String result = operate(op1, op2, operator);
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.push(result);
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return Integer.parseInt(stack.pop());
    }

     */
    public int evalRPN(String[] tokens) {
        HashSet<String> hash = new HashSet<String>();
        hash.add("+");
        hash.add("-");
        hash.add("*");
        hash.add("/");

        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (hash.contains(token)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String result = operate(op2, op1, token);
                stack.push(result);
            } else {
                stack.push(token);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    private String operate(String token, String op1, String operator) {
        switch (operator) {
            case "+": return "" +(Integer.parseInt(op1) + Integer.parseInt(token));
            case "*": return "" +(Integer.parseInt(op1) * Integer.parseInt(token));
            case "-": return "" +(Integer.parseInt(token) - Integer.parseInt(op1));
            case "/": return "" +(Integer.parseInt(token) / Integer.parseInt(op1));

        }
        throw new RuntimeException("");
    }

    private boolean isOperator(String token) {
        return "+".equals(token) || "*".equals(token) || "/".equals(token) || "-".equals(token);
    }

    public static void main(String[] args) {
        String[] arr = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int result = new ReversePolishNotation().evalRPN(arr);
        System.out.println(result);
    }
}
