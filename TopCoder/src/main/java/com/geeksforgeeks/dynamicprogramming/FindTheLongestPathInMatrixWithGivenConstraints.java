package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/28/16.
 */
//http://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
public class FindTheLongestPathInMatrixWithGivenConstraints {
    int[][] dp = null;
     public void find(int[][] matrix){
         int rowLength = matrix.length;
         int colLength = matrix[0].length;
         dp = new int[rowLength][colLength];
         for (int i = 0; i < rowLength ; i++) {
             for ( int j = 0 ; j < colLength ; j++) {
                 dp[i][j] = -1;
             }
         }

         int result = Integer.MIN_VALUE;
         for (int i = 0 ; i < rowLength ; i++) {
             for ( int j = 0 ; j < colLength ; j++) {
                 int r = findRecursive(matrix, i, j, 0, Integer.MAX_VALUE);
                 if (r > result){
                     result = r;
                 }
             }
         }
         System.out.println(result);
     }

    private int findRecursive(int[][] matrix,
                              int currentX,
                              int currentY,
                              int currentDistance,
                              int prevValue) {
        if (!isValid(matrix, currentX, currentY)){
            return currentDistance;
        }

        if (prevValue != Integer.MAX_VALUE && matrix[currentX][currentY]-prevValue != 1){
            return  currentDistance;
        }

        if (dp[currentX][currentY] != -1){
            return dp[currentX][currentY];
        }

        int result1 = findRecursive(matrix, currentX, currentY-1, currentDistance+1, matrix[currentX][currentY]);
        int result2 = findRecursive(matrix, currentX, currentY+1, currentDistance+1, matrix[currentX][currentY]);
        int result3 = findRecursive(matrix, currentX-1, currentY, currentDistance+1, matrix[currentX][currentY]);
        int result4 = findRecursive(matrix, currentX+1, currentY, currentDistance+1, matrix[currentX][currentY]);

        return Math.max(result1, Math.max(Math.max(result2, result3),result4));
    }

    private boolean isValid(int[][] matrix, int currentX, int currentY) {
        return currentX >= 0 && currentX < matrix.length && currentY >= 0 && currentY < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 9},
                {5, 3, 8},
                {4, 6, 7}};
        new FindTheLongestPathInMatrixWithGivenConstraints().find(matrix);
    }
}

