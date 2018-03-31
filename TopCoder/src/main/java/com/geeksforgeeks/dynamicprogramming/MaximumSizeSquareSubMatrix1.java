package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
public class MaximumSizeSquareSubMatrix1 {

    int matrix[][] = {
            {0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
    };

    public void find(){
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int dp[][] = new int[rowLength][colLength];

        for (int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                dp[i][j] = matrix[i][j];
            }
        }

        for ( int i = 1 ; i < dp.length ; i++) {
            for ( int j = 1; j < dp[0].length ; j++) {
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        for ( int i = 0 ; i < dp.length ; i++) {
            for ( int j = 0 ; j < dp[0].length ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        int max = Integer.MIN_VALUE;
        for ( int i = 0 ; i < dp.length ; i++) {
            for ( int j = 0 ; j < dp[0].length ; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args) {
        new MaximumSizeSquareSubMatrix1().find();
    }

}
