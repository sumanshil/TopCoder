package com.leetcode;

//https://leetcode.com/problems/unique-paths-ii/
public class UniquePath2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowLength = obstacleGrid.length;
        int colLength = obstacleGrid[0].length;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0 ; i < rowLength ; i++ ) {
            for (int j = 0 ; j < colLength ; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if ( (i == 0 || j == 0) && obstacleGrid[i][j] == 0) {
                    dp[i][j] = 1;
                } else if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[rowLength-1][colLength-1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int result = new UniquePath2().uniquePathsWithObstacles(matrix);
        System.out.println(result);
    }
}
