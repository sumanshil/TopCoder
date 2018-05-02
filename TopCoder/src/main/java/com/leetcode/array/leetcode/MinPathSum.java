package com.leetcode.array.leetcode;

//https://leetcode.com/problems/minimum-path-sum/description/
public class MinPathSum {

    public void find (int num[][]) {
        int rowLength = num.length;
        int colLength = num[0].length;
        int dp[] = new int[colLength];
        dp[0] = num[0][0];
        for ( int i = 1 ; i < colLength ; i++) {
            dp[i] = num[0][i] + dp[i-1];
        }

        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 0 ; j < colLength ; j++) {
               if ( j == 0) {
                   dp[j] = dp[j] + num[i][j];
               } else {
                   dp[j] = num[i][j] + Math.min(dp[j], dp[j-1]);
               }
            }
        }
        System.out.println(dp[colLength-1]);

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        new MinPathSum().find(matrix);
    }
}
