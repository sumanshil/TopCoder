package com.leetcode.integer;

public class ReverseInteger {

    public int reverse(int x) {
        long result = 0;
        boolean isNegative = false;
        if (x < 0 ) {
            isNegative = true;
            x = Math.abs(x);
        }
        long remaining = x;
        while (remaining > 0) {
            long lastDigit = remaining % 10;
            remaining = remaining / 10;
            result = result* 10 + lastDigit;
        }
        if (isNegative) {
            result = -result;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int)result;
        }
    }

    public static void main(String[] args) {
        //int number = -123;
        int n = 1534236469;
        int res = new ReverseInteger().reverse(n);
        System.out.println(res);
        System.out.println(Integer.MAX_VALUE);
    }
}
