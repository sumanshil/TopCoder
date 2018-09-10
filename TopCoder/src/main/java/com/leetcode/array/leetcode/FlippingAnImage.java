package com.leetcode.array.leetcode;

//https://leetcode.com/problems/flipping-an-image/description/
public class FlippingAnImage {

    public void flip(int[][] matrix) {

        for ( int i = 0 ; i < matrix.length ; i++) {
            int start = 0;
            int end = matrix[i].length - 1;
            while (start < end) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }

        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[i].length ; j++) {
                matrix[i][j] = matrix[i][j] == 1? 0 : 1;
            }
        }

    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0},
                {1, 0, 1},
                {0, 0, 0}
        };
        new FlippingAnImage().flip(matrix);
        for ( int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[i].length ; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

}
