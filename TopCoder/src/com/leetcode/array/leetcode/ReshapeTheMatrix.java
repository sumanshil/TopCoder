package com.leetcode.array.leetcode;

//https://leetcode.com/problems/reshape-the-matrix/description/
public class ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length* nums[0].length != r * c) {
            return nums;
        }

        int matrix[][] = new int[r][c];
        int currentRowIndex = 0;
        int currentColIndex = -1;

        for ( int i = 0 ; i < nums.length ; i++) {
            for ( int j = 0 ; j < nums[i].length ; j++) {
                if (currentColIndex == c) {
                    currentRowIndex++;
                    currentColIndex = 0;
                } else {
                    currentColIndex++;
                }
                matrix[currentRowIndex][currentColIndex] = nums[i][j];
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int nums[][] = {
                {1, 2},
                {3, 4}
        };
        int matrix[][] = new ReshapeTheMatrix().matrixReshape(nums, 2, 4);
        for (int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }
}
