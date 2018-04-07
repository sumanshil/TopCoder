package com.leetcode.array.leetcode;

import java.util.stream.IntStream;

//https://leetcode.com/problems/spiral-matrix-ii/description/
public class SpiralMatrix2 {

    int xDiff[] = {0, 1,  0, -1};
    int yDiff[] = {1, 0, -1,  0};
    static int[][] matrix;

    public int[][] generateMatrix(int n) {
        matrix = new int[n][n];
        int number = 1;
        int startRow = 0;
        int startCol = 0;
        int endRow = n-1;
        int endCol = n-1;
        while (number <= Math.pow(n,2)) {
            for (int j = startCol; j <= endCol ; j++) {
                matrix[startRow][j] = number++;
            }

            for (int j = startRow+1 ; j <= endRow ; j++) {
                matrix[j][endCol] = number++;
            }

            for (int j = endCol-1 ; j >= startCol ; j--) {
                matrix[endRow][j] = number++;
            }

            for (int j = endRow-1 ; j > startRow ; j--) {
                matrix[j][startCol] = number++;
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return matrix;
    }

    /*
    public int[][] generateMatrix(int n) {
        this.matrix = new int[n][n];
        int number  = 1;
        int row = 0;
        int col = 0;
        int originalRow = 0;
        int originalCol = 0;
        int diffIndex = 0;
        for (int i = 1 ; i <= Math.pow(n, 2) ; i++) {
            matrix[originalRow][originalCol] = number++;
            row = originalRow + xDiff[diffIndex];
            col = originalCol + yDiff[diffIndex];
            if (!isValid(row, col)) {
                diffIndex = (diffIndex + 1) % 4;
                int newRow = originalRow + xDiff[diffIndex];
                int newCol = originalCol + yDiff[diffIndex];
                if (!isValid(newRow, newCol)) {
                    diffIndex = (diffIndex + 1) % 4;
                    newRow = originalRow + xDiff[diffIndex];
                    newCol = originalCol + yDiff[diffIndex];
                    if (!isValid(newRow, newCol)) {
                        break;
                    }
                }
                originalRow = newRow;
                originalCol = newCol;
            } else {
                originalRow = row;
                originalCol = col;
            }
        }
        return matrix;
    }




    private boolean isValid(int row, int col) {
        return row >= 0
               && row < matrix.length
               && col >= 0
               && col < matrix[0].length && matrix[row][col] == 0;
    }

    */

    public static void main(String[] args) {
        int n = 3;
        new SpiralMatrix2().generateMatrix(n);
        IntStream.range(0, matrix.length)
                .forEach(row -> IntStream.range(0, matrix[0].length)
                        .forEach(col -> {
                            System.out.print(matrix[row][col]);
                            if (col == matrix[0].length-1) {
                                System.out.println();
                            }
                        }));
    }
}
