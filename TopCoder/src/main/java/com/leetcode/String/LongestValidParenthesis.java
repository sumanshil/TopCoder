package com.leetcode.String;

import java.util.Stack;

//https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParenthesis {

    static class ContentAndIndex {
        String content;
        int index;
        public ContentAndIndex(String c, int i) {
            this.content = c;
            this.index = i;
        }
    }

    public int longestValidParentheses(String s) {
        /*
        Stack<ContentAndIndex> stack = new Stack<>();
        int maxLength = Integer.MIN_VALUE;
        int lastMatchIndex = -1;
        for (int i = 0 ; i < s.length() ; i++) {
            String str = s.charAt(i)+"";
            if ("(".equals(str)) {
                stack.push(new ContentAndIndex(str, i));
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    if (stack.isEmpty()) {
                       maxLength = Math.max(maxLength, (i-lastMatchIndex));
                    } else {
                       maxLength = Math.max(maxLength, (i-stack.peek().index));
                    }
                } else {
                    lastMatchIndex = i;
                }

            }
        }
        return  maxLength == Integer.MIN_VALUE ? 0 : maxLength;
        */
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else {
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }
                else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String str = "()";
        //String str = "(()())";
        //String str = "(()";
        //String str = ")()())";
        //String str = "()()";
        //String str = "((()))(())";

        int res = new LongestValidParenthesis().longestValidParentheses(str);
        System.out.println(res);
    }
}
