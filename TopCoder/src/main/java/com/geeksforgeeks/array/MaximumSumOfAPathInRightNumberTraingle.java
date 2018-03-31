package com.geeksforgeeks.array;

//https://www.geeksforgeeks.org/maximum-sum-path-right-number-triangle/
public class MaximumSumOfAPathInRightNumberTraingle {

    private int[][] matrix = {
            {2, 0, 0},
            {4, 1, 0},
            {1, 2, 7},
    };


    public void find () {
        int dp[][] = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (i == 0 && j ==0 ) {
                    continue;
                }
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        int maxColLength = 2;
        for (int i = 1 ; i < matrix.length ; i++) {
            for ( int col = 0 ; col < maxColLength; col++ ){
                int upRow = i-1;
                int upCol = col;
                if (upRow >= 0 && upCol >= 0){
                    dp[i][col] = Math.max(dp[i][col], dp[upRow][upCol]+matrix[i][col]);
                }
                upCol = col-1;
                if (upRow >= 0 && upCol >= 0) {
                    dp[i][col] = Math.max(dp[i][col], dp[upRow][upCol]+matrix[i][col]);
                }
            }
            maxColLength++;
        }
        int lastRow = dp.length-1;
        int colLength = dp[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < colLength ; i++) {
            max = Math.max(max, dp[lastRow][i]);
        }
        System.out.println(max);
    }


    public static void main(String[] args) {
        new MaximumSumOfAPathInRightNumberTraingle().find();
    }

}
