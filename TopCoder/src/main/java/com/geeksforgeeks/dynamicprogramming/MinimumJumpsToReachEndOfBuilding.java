package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/minimum-jumps-to-reach-last-building-in-a-matrix/
public class MinimumJumpsToReachEndOfBuilding {

    int matrix[][] = {
            { 5, 4, 2 },
            { 9, 2, 1 },
            { 2, 5, 9 },
            { 1, 3, 11}
    };

    public void find () {
        int result = recursive(0, 0);
        System.out.println(result);
    }

    public void dp (){
        int dp[][] = new int[matrix.length][matrix[0].length];
        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++){
                if (isValid(i-1, j-1)){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + Math.abs(matrix[i-1][j-1] - matrix[i][j]));
                }

                if (isValid(i-1, j)){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + Math.abs(matrix[i-1][j] - matrix[i][j]));
                }

                if (isValid(i, j-1)){
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + Math.abs(matrix[i][j-1] - matrix[i][j]));
                }
            }
        }
        System.out.println(dp[matrix.length-1][matrix[0].length-1]);
    }

    private int recursive(int row, int col) {
        if (row == matrix.length-1 && col == matrix[0].length-1) {
            return 0;
        }
        int result1 = Integer.MAX_VALUE;
        int result2 = Integer.MAX_VALUE;
        int result3 = Integer.MAX_VALUE;
        if (isValid(row + 1, col)){
            result1 = recursive(row + 1, col) + Math.abs(matrix[row][col] - matrix[row + 1][col]);
        }

        if (isValid(row, col+1)) {
            result2 = recursive(row, col + 1) + Math.abs(matrix[row][col] - matrix[row][col+1]);
        }

        if (isValid(row + 1, col+1)) {
            result3 = recursive(row + 1, col + 1) + Math.abs(matrix[row][col] - matrix[row + 1][col+1]);
        }

        return Math.min(result1, Math.min(result2, result3));
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public static void main(String[] args) {
        new MinimumJumpsToReachEndOfBuilding().find();
    }
}
