package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/8/16.
 */
//http://www.geeksforgeeks.org/minimum-positive-points-to-reach-destination/
public class MinimumInitialPointsToReachDestination {

//    private int[][] matrix = {
//            {-2, -3,   3},
//            {-5, -10,  1},
//            {10,  30, -5},
//    };

    private static int[][] matrix = {
            {-2, -3},
            {-5, -10}
    };

//    private static int[][] matrix = {
//            {1,   2,   3},
//            {4,   5,   6},
//            {7,   8,   9},
//    };
    private void find() {
        int result = recursive(2,2,0);
        System.out.println(result);
    }

    private int recursive(int x, int y, int sum) {
        if (x < 0 || x > 2 || y < 0 || y > 2){
            return Integer.MAX_VALUE;
        }

        if (x == 0 && y == 0){
            return  matrix[x][y] + sum < 0 ? Math.abs(matrix[x][y] + sum)+1 :  matrix[x][y] + sum ;
        }
        int result1 = recursive(x, y-1, sum + matrix[x][y]);
        int result2 = recursive(x-1, y, sum + matrix[x][y]);
        return Math.min(result1, result2);
    }

    public void findDP(){
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int[][] dp = new int[rowLength][colLength];

        dp[0][0] = matrix[0][0];
        for ( int i = 1 ; i < colLength ; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i-1];
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = dp[i-1][0]+ matrix[i][0];
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {

                int x = matrix[i][j] + dp[i-1][j];
                int y = matrix[i][j] + dp[i][j-1];
                if ( x < 0 && y < 0){
                    dp[i][j] = Math.max(x, y);
                }else {
                    dp[i][j] = Math.min(x, y);
                }
            }
        }

        int x = matrix[rowLength-1][colLength-1] + dp[rowLength-2][colLength-1];
        int y = matrix[rowLength-1][colLength-1] + dp[rowLength-1][colLength-2];
        int result;
        if (x < 0 && y < 0){
            int max = Math.max(x, y);
            result = Math.abs(max)+1;
        } else if ( x > 0 && y < 0){
            result = Math.abs(y)+1;
        } else if ( x < 0 && y > 0) {
            result = Math.abs(x) + 1;
        } else {
            result = 1;
        }
        System.out.println(result);
    }


    static int minInitialPoints(int points[][],int R,int C)
    {
        // dp[i][j] represents the minimum initial points player
        // should have so that when starts with cell(i, j) successfully
        // reaches the destination cell(m-1, n-1)
        int dp[][] = new int[R][C];
        int m = R, n = C;

        // Base case
        dp[m-1][n-1] = points[m-1][n-1] > 0? 1:
                Math.abs(points[m-1][n-1]) + 1;

        // Fill last row and last column as base to fill
        // entire table
        for (int i = m-2; i >= 0; i--)
            dp[i][n-1] = Math.max(dp[i+1][n-1] - points[i][n-1], 1);
        for (int j = n-2; j >= 0; j--)
            dp[m-1][j] = Math.max(dp[m-1][j+1] - points[m-1][j], 1);

        // fill the table in bottom-up fashion
        for (int i=m-2; i>=0; i--)
        {
            for (int j=n-2; j>=0; j--)
            {
                int min_points_on_exit = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(min_points_on_exit - points[i][j], 1);
            }
        }

        return dp[0][0];
    }
    public static void main(String[] args) {
        new MinimumInitialPointsToReachDestination().findDP();
        //int result = new MinimumInitialPointsToReachDestination().minInitialPoints(matrix, 2, 2);
       // System.out.println(result);
    }
}
