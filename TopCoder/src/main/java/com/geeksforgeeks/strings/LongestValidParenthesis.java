package com.geeksforgeeks.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LongestValidParenthesis {

    private Map<Integer, Integer> countToIndexMap = new HashMap<>();

    public int process(String str){
        int max = Integer.MIN_VALUE;
        int count = 1;
        Stack<String> stack = new Stack<>();
        countToIndexMap.put(1, 0);
        stack.push(str.charAt(0)+"");
        for ( int i = 1 ; i < str.length() ; i++ ) {
            if ((str.charAt(i)+"").equals(")") && stack.peek().equals("(")) {
                count--;
                if (countToIndexMap.containsKey(count)){
                    int index = countToIndexMap.get(count);
                    if (i - index > max){
                        max = (i - index);
                    }
                } else {
                    countToIndexMap.put(count, i);
                }
            } else {
                count++;
                stack.push(str.charAt(i)+"");
                countToIndexMap.put(count, i);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        String str = "(((())((((()";
        int max = new LongestValidParenthesis().process(str);
        System.out.println(max);
    }
}
