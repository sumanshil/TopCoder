package com.leetcode.array.leetcode;

//https://www.geeksforgeeks.org/prefix-sum-2d-array/
public class PrefixSumArray {

    public void find (int matrix[][]) {
        int colLength = matrix[0].length;
        int rowLength = matrix.length;
        for (int i = 1 ; i < colLength ; i++) {
            matrix[0][i] = matrix[0][i] + matrix[0][i-1];
        }

        for (int i = 1 ; i < rowLength ; i++) {
            matrix[i][0] = matrix[i][0] + matrix[i-1][0];
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                matrix[i][j] += matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
            }
        }

        for ( int i = 0 ; i < rowLength ; i++) {
            for ( int j = 0 ; j < colLength ; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {10, 20, 30},
                {5, 10, 20},
                {2, 4, 6}
        };
        new PrefixSumArray().find(matrix);
    }
}
