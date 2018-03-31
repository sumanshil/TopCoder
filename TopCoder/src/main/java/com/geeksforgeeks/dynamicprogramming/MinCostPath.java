package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/5/2016.
 */
public class MinCostPath {
    public void findMinDistance(int[][] matrix){
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];
        sumMatrix[0][0] = matrix[0][0];
        int rowLength = matrix.length;
        int colLength = matrix[0].length;

        for (int i = 1 ; i < colLength ; i++) {
            sumMatrix[0][i] = sumMatrix[0][i-1]+matrix[0][i];
        }
        for ( int j = 1 ; j < rowLength ; j++){
            sumMatrix[j][0] = sumMatrix[j-1][0]+ matrix[j][0];
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                sumMatrix[i][j] = matrix[i][j] + Math.min(sumMatrix[i-1][j],
                                                          Math.min(sumMatrix[i][j-1], sumMatrix[i-1][j-1]));
            }
        }

        int result = sumMatrix[rowLength-1][colLength-1];
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            { 1, 2, 3},
            {4, 8, 2},
            {1, 5, 3}
        };
        new MinCostPath().findMinDistance(matrix);
    }
}
