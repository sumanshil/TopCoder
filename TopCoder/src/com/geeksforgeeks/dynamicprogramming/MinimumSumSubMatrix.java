package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/minimum-sum-submatrix-given-2d-array/
public class MinimumSumSubMatrix {

    public void find (int matrix[][]) {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int dp[][] = new int[rowLength][colLength];
        dp[0][0] = matrix[0][0];
        for (int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }

        for (int i = 1 ; i < colLength ; i++) {
            dp[0][i] = dp[0][i-1] + matrix[0][i];
        }

        int minResult = Integer.MAX_VALUE;
        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1; j < colLength ; j++) {

                int result = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
                result += matrix[i][j];
                dp[i][j] = result;
                if (result < minResult){
                    minResult = result;
                }
            }
        }
        System.out.println(minResult);
    }

    public static void main(String[] args) {

        int matrix[][] = {
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        new MinimumSumSubMatrix().find(matrix);
    }

}
