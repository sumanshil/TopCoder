package com.leetcode;

//https://leetcode.com/problems/integer-break/
public class IntegerBreak {

    public int integerBreak(int n) {
        if (n == 1|| n == 2) {
            return 1;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;

        for (int i = 1 ; i <=n ; i++) {
            result[i] = i;
        }

        for (int i = 2 ; i < n ; i++) {

            for (int j = 0 ; j <= i/2; j++) {
                result[i] = Math.max(result[i], result[j] * result[i-j]);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int j = 1 ; j <= n/2 ; j++) {
            max = Math.max(max, result[j] * result[n-j]);
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 20;
        int result = new IntegerBreak().integerBreak(n);
        System.out.println(result);
    }
}
