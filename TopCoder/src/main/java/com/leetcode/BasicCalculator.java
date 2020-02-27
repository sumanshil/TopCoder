package com.leetcode;

import java.util.Stack;

//https://leetcode.com/problems/basic-calculator-ii/
public class BasicCalculator {

    public int calculate(String s) {
        Stack<String> ops = new Stack<>();
        Stack<Integer> values = new Stack<>();

        for (int i = 0 ; i < s.length() ;) {
            if (isOperator(s.charAt(i))) {
                while (!values.isEmpty() && !ops.isEmpty() && hasPrecedence(ops.peek(), s.charAt(i)+"")) {
                    int value1 = values.pop();
                    int value2 = values.pop();
                    String op = ops.pop();
                    int res = evaluate(op, value2, value1);
                    values.push(res);
                }
                ops.push(s.charAt(i) +"");
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                while (i < s.length() && !isOperator(s.charAt(i))) {
                    stringBuffer.append(s.charAt(i));
                    i++;
                }
                int value = Integer.parseInt(stringBuffer.toString().trim());
                values.push(value);
            }
        }
        while (!ops.isEmpty() && !values.isEmpty()) {
            String op = ops.pop();
            int value1 = values.pop();
            int value2 = values.pop();
            int res = evaluate(op, value2, value1);
            values.push(res);
        }
        return values.pop();
    }

    private boolean hasPrecedence(String peek, String s) {
        if ((peek.equals("*") || peek.equals("/")) && (s.equals("+") || s.equals("-"))) {
            return true;
        }
        return ((peek.equals(s))|| (peek.equals("-")  && s.equals("+")) || (peek.equals("+")  && s.equals("-")) || (peek.equals("*")  && s.equals("/")) || (peek.equals("/")  && s.equals("*")));
    }

    private int evaluate(String op, int value1, int value2) {
        if (op.equals("+")) {
            return value1 + value2;
        } else if (op.equals("-")) {
            return value1 - value2;
        } else if (op.equals("*")) {
            return value1 * value2;
        } else if (op.equals("/")) {
            return value1 / value2;
        }
        throw new RuntimeException("Invalid operator");
    }

    private boolean isOperator(char c) {
        return c == '*' || c == '+' || c == '-' || c == '/';
    }

    public static void main(String[] args) {
        String str = "1-1+1";
        int res = new BasicCalculator().calculate(str);
        System.out.println(res);
    }

}
