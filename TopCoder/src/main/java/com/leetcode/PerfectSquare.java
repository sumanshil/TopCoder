package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/perfect-squares/
public class PerfectSquare {

    public int numSquares(int n) {

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 2 ; i  <= n; i++ ) {
            for (int j = 1; j * j <= i ; j++) {

                dp[i] = Math.min(dp[i], dp[i - (int)Math.pow(j,2)] + 1);
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int n = 13;
        int res = new PerfectSquare().numSquares(n);
        System.out.println(res);
    }
}
