package com.leetcode;

//https://leetcode.com/problems/range-sum-query-2d-immutable/
public class NumMatrix {

    int sumMatrix[][] = null;
    public NumMatrix(int[][] matrix) {
        int rowLength = matrix.length + 1;
        int colLength = matrix[0].length + 1;
        sumMatrix = new int[rowLength][colLength];

        for (int i = 1 ; i <= matrix.length ; i++) {
            for (int j = 1 ; j <= matrix[0].length ; j++) {
                sumMatrix[i][j] = matrix[i-1][j-1] + sumMatrix[i][j-1] + sumMatrix[i-1][j] - sumMatrix[i-1][j-1];
            }
        }

        for (int i = 0 ; i < rowLength ; i++) {
            for (int j = 0 ; j < colLength ; j++) {
                System.out.print(sumMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sumRightUpCorner = sumMatrix[row2 + 1][col2 + 1];
        System.out.println(sumRightUpCorner);
        int sumRightDownCorner = sumMatrix[row1 ][col2 + 1];
        System.out.println(sumRightDownCorner);

        int sumLeftUpCorner = sumMatrix[row2 +1][col1];
        System.out.println(sumLeftUpCorner);

        int sumLeftDownCorner = sumMatrix[row1 ][col1];
        System.out.println(sumLeftDownCorner);

        int result = (sumRightUpCorner - (sumRightDownCorner + sumLeftUpCorner)) + sumLeftDownCorner;
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
            };
        NumMatrix numMatrix = new NumMatrix(matrix);
        //int res = numMatrix.sumRegion(2, 1, 4, 3);
        //int res = numMatrix.sumRegion(1, 1, 2, 2);
        int res = numMatrix.sumRegion(1, 2, 2, 4);

        System.out.println(res);
    }

    //38 - (14 + 24) +
}
