package com.leetcode;

//https://leetcode.com/problems/unique-paths/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int rowLength = m;
        int colLength = n;

        int[][] dp = new int[rowLength][colLength];

        dp[0][0] = 0;

        for (int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1 ; i < colLength ; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 1 ; j < colLength ; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[rowLength-1][colLength-1];
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int res = new UniquePaths().uniquePaths(m, n);
        System.out.println(res);
    }
}
