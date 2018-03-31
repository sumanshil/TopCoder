package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/28/16.
 */
//http://www.geeksforgeeks.org/number-of-paths-with-exactly-k-coins/
public class NumberOfPathsWithExactlyKCoins1 {

    public void find(int[][] matrix, int k) {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int result = findRecursive(matrix, rowLength-1, colLength-1, k);
        System.out.println(result);
    }

    private int findRecursive(int[][] matrix, int currentX, int currentY, int k) {
        if (currentX == 0 && currentY == 0 && (k-matrix[currentX][currentY] == 0)) {
            return 1;
        }

        int result1 = 0;
        int result2 = 0;
        if (isValid(matrix, currentX, currentY-1)) {
            result1 = findRecursive(matrix, currentX, currentY-1, k-matrix[currentX][currentY-1]);
        }

        if (isValid(matrix, currentX-1, currentY)) {
            result2 = findRecursive(matrix, currentX-1, currentY, k-matrix[currentX-1][currentY]);
        }
        return result1+result2;
    }

    private int[][][] dp = null;
    private void dp(int[][] matrix, int k){
        dp = new int[matrix.length][matrix[0].length][1000];
        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++){
                for ( int l = 0 ; l < 1000 ; l++){
                    dp[i][j][l] = -1;
                }
            }
        }
        int result = recursive1(matrix, matrix.length-1, matrix[0].length-1, k);
        System.out.println(result);
    }

    private int recursive1(int[][] matrix,  int currentX, int currentY, int k) {
        if (k < 0 ){
            return 0;
        }
        if (currentX < 0 || currentY < 0){
            return 0;
        }
        if (currentX == 0 && currentY == 0){
            if (matrix[0][0] == k){
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[currentX][currentY][k] != -1){
            return dp[currentX][currentY][k];
        }
        dp[currentX][currentY][k] = recursive1(matrix, currentX-1, currentY, k-matrix[currentX][currentY])+
                recursive1(matrix,currentX, currentY-1, k-matrix[currentX][currentY]);
        return dp[currentX][currentY][k];
    }

    private boolean isValid(int[][] matrix, int currentX, int currentY) {
        return currentX >= 0 && currentX < matrix.length && currentY >= 0 && currentY < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };
        int k = 12;
        new NumberOfPathsWithExactlyKCoins1().dp(matrix, k);
    }
}
