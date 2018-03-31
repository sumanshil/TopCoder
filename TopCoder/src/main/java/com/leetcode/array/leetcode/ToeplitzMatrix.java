package com.leetcode.array.leetcode;

//https://leetcode.com/problems/toeplitz-matrix/description/
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0 ; i < matrix.length - 1 ; i++) {
            for ( int j = 0 ; j < matrix[i].length - 1 ; j++) {
               if (matrix[i][j] != matrix[i+1][j+1]) {
                   return false;
               }
            }
        }
        return true;
    }

    /*
    public boolean isToeplitzMatrix(int[][] matrix) {
        int startRowIndex = 0;
        int startColIndex = 0;

        for (int col = 0 ; col < matrix[0].length ; col++) {
            boolean result = check(startRowIndex, col, matrix);
            if (!result) {
                return false;
            }
        }

        for (int row = 1 ; row < matrix.length ; row++) {
            boolean result = check(row, startColIndex, matrix);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int row, int col, int[][] matrix) {
        int element = matrix[row][col];
        while (row + 1 < matrix.length && col + 1 < matrix[0].length) {
            if (element != matrix[row+1][col+1]) {
                return false;
            }
            row = row + 1;
            col = col + 1;
        }
        return true;
    }
    */

    public static void main(String[] args) {
        //int matrix[][] = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int matrix[][] = {{1,2},{2,2}};
        boolean result = new ToeplitzMatrix().isToeplitzMatrix(matrix);
        System.out.println(result);
    }
}
