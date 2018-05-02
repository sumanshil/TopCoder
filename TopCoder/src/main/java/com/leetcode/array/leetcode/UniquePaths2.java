package com.leetcode.array.leetcode;

//https://leetcode.com/problems/unique-paths-ii/description/
public class UniquePaths2 {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int dp[][] = new int[row][col];

        for ( int i = 1 ; i < row ; i++) {
            dp[0][i] = 1;
        }

        for ( int j = 1; j < col ; j++) {
            dp[j][0] = 1;
        }


        for ( int i = 1; i < row ; i++) {
            for ( int j = 1 ; j < col ; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[row-1][col-1];
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int col = obstacleGrid[0].length;

        int dp[] = new int[col];

        dp[0]  = obstacleGrid[0][0] | 1;

        for (int[] arr : obstacleGrid) {
            for (int i = 0 ; i < col ; i++) {
                if (arr[i] == 1) {
                    dp[i] = 0;
                } else if (i > 0) {
                    dp[i] += dp[i-1];
                }
            }
        }
        return dp[dp.length-1];
    }



    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
                };

        int result = new UniquePaths2().uniquePathsWithObstacles1(matrix);
        System.out.println(result);
    }

}
