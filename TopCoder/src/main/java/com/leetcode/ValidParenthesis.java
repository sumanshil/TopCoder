package com.leetcode;

import java.util.Stack;

//https://leetcode.com/problems/valid-parentheses/
public class ValidParenthesis {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for ( int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "(]";
        boolean result = new ValidParenthesis().isValid(str);
        System.out.println(result);
    }
}
