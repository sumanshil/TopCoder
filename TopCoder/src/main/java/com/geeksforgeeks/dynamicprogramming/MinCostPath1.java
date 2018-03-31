package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
public class MinCostPath1 {
    private static int[][] matrix = {
            {1, 2, 3},
            {4, 8, 2},
            {1, 5, 3}
    };

    public static void dp(){
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for ( int i = 1 ; i < matrix.length ; i++) {
            dp[i][0] = matrix[i][0]+matrix[i-1][0];
        }

        for ( int j = 1 ; j < matrix[0].length ; j++) {
            dp[0][j] = matrix[0][j]+matrix[0][j-1];
        }

        for ( int i = 1 ; i < matrix.length ; i++) {
            for (int j = 1 ; j < matrix[0].length; j++) {
                dp[i][j]
                        = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]),
                        dp[i-1][j])+matrix[i][j];
            }
        }
        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[matrix.length-1][matrix[0].length-1]);
    }

    public static void main(String[] args) {
        MinCostPath1.dp();
    }
}
