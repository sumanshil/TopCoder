package com.leetcode;

import java.math.BigInteger;

//https://leetcode.com/problems/factorial-trailing-zeroes/
public class FactorialTrailingZeros {

    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 5) {
            res += n/5;
            n = n / 5;
        }
        return res;
    }

    public static void main(String[] args) {
        int number = 30;
        int res = new FactorialTrailingZeros().trailingZeroes(number);
        System.out.println(res);
    }
}
