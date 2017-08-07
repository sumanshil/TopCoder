package com.geeksforgeeks.dynamicprogramming;

public class MaximumSizeSquareSubMatrix {
    private static int[][] matrix = {
            {0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
    };

    public static void find(){
        int[][] dp = new int[matrix.length][matrix[0].length];
        for ( int i = 0 ; i < matrix.length ; i++){
            dp[i][0] = matrix[i][0];
        }

        for ( int i = 0 ; i < matrix[0].length ; i++ ) {
            dp[0][i] = matrix[0][i];
        }

        for ( int i = 1 ; i < matrix.length ; i++) {
            for ( int j = 1 ; j < matrix[0].length ; j++) {
                if (matrix[i][j] == 1) {
                    int min = Math.min(Math.min(dp[i-1][j],dp[i][j-1]), dp[i-1][j-1]);
                    dp[i][j] = min + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for ( int i = 1 ; i < dp.length ; i++) {
            for (int j = 1; j < dp[0].length ; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        find();
    }
}
