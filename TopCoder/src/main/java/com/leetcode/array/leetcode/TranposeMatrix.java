package com.leetcode.array.leetcode;

//https://leetcode.com/problems/transpose-matrix/description/
public class TranposeMatrix {

    public int[][] transpose(int[][] A) {
        /*
        int result[][] = new int[A[0].length][A.length];

        int currentRow = 0;
        int currentCol = 0;

        for ( int i = 0 ; i < A.length ; i++) {
            for ( int j = 0 ; j < A[0].length ; j++) {
                result[currentRow][currentCol] = A[i][j];
                currentRow++;
            }
            currentRow = 0;
            currentCol++;
        }
        return result;
        */
        int rowLength = A.length;
        int colLength = A[0].length;
        int result[][] = new int[rowLength][colLength];
        for ( int i = 0 ; i < rowLength * colLength ; i++) {
            int r = i / rowLength;
            int c = i % rowLength;
            result[c][r] = A[r][c];
        }

        return result;
    }




    public static void main(String[] args) {
        int matrix[][] = {{1,2,3},
                          {4,5,6},
                          {7,8,9}};
        int[][] result = new TranposeMatrix().transpose(matrix);
        for ( int i = 0 ; i < result.length ; i++) {
            for (int j = 0 ; j < result[0].length ; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }

    }
}
