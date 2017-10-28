package com.geeksforgeeks.dynamicprogramming;

public class MinCostPath2 {

    public void minPath(int path[][]){
        int rowLength = path.length;
        int colLength = path[0].length;
        int matrix[][] = new int[rowLength][colLength];
        matrix[0][0] = path[0][0];
        for ( int i = 1 ; i < colLength; i++){
            matrix[0][i] = path[0][i] + matrix[0][i-1];
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            matrix[i][0] = path[i][0] + matrix[i-1][0];
        }

        for ( int i = 1; i < rowLength ; i++) {
               for ( int j = 1 ; j < colLength ; j++) {
                int min = Math.min(Math.min(matrix[i-1][j], matrix[i][j-1]), matrix[i-1][j-1]);
                matrix[i][j] = min + path[i][j];
            }
        }
        System.out.println(matrix[rowLength-1][colLength-1]);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 8 ,2},
            {1, 5, 3}
        };

        new MinCostPath2().minPath(matrix);
    }
}
