package com.leetcode;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/different-ways-to-add-parentheses/
public class DifferentWaysToAddParenthesis {

    Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = recursive(input);
        return result;
    }

    private List<Integer> recursive(String input) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        if (isAllDigit(input)) {
            List<Integer> result = new LinkedList<>();
            result.add(Integer.parseInt(input));
            map.put(input, result);
            return result;
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0 ; i < input.length() ; i++) {
            if (isOperator(input.charAt(i))) {
                List<Integer> res1 = recursive(input.substring(0, i));
                List<Integer> res2 = recursive(input.substring(i+1));
                char operator = input.charAt(i);
                for (Integer j :res1) {
                    for (Integer k : res2) {
                        result.add(evaluate(j, operator, k));
                    }
                }
            }
        }
        map.put(input, result);
        return result;
    }

    private Integer evaluate(int operand1, char operator, Integer res) {
        if (operator == '+') {
            return operand1 + res;
        } else if (operator == '-') {
            return operand1 - res;
        } else if (operator == '*') {
            return operand1 * res;
        }
        return 0;
    }

    private boolean isAllDigit(String input) {
        for (int i = 0 ; i < input.length() ; i++) {
            if (isOperator(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "2*3-4*5";
        List<Integer> result = new DifferentWaysToAddParenthesis().diffWaysToCompute(str);
        System.out.println(result);
    }
}
