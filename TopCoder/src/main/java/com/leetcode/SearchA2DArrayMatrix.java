package com.leetcode;

//https://leetcode.com/problems/search-a-2d-matrix-ii/
public class SearchA2DArrayMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int startRow = 0;
        int endRow = matrix.length - 1;

        int startCol = 0;
        int endCol = matrix[0].length - 1;

        boolean result = recursive(matrix, startRow, endRow, startCol, endCol , target);
        return result;
    }

    private boolean recursive(int[][] matrix, int startRow, int endRow, int startCol, int endCol, int target) {
        if (startRow > endRow) {
            return false;
        }

        if (startCol > endCol) {
            return false;
        }

        if (startRow == endRow && (startCol + 1 == endCol)) {
            if (matrix[startRow][startCol] == target || matrix[startRow][endCol] == target) {
                return true;
            } else {
                return false;
            }
        }

        if (startCol == endCol && (startRow + 1 == endRow)) {
            if (matrix[startRow][startCol] == target || matrix[startRow][endCol] == target) {
                return true;
            } else {
                return false;
            }
        }
        int midRow = (startRow) + (endRow - startRow)/2;
        int midCol = (startCol) + (endCol - startCol)/2;

        if (matrix[midRow][midCol] == target) {
            return true;
        }

        if (matrix[midRow][midCol] < target) {
            return recursive(matrix, midRow, endRow, startCol, midCol, target) // left lower
                   || recursive(matrix, startRow, midRow, midCol, endCol, target)
                   || recursive(matrix, midRow, endRow, midCol, endCol, target);
        } else {
            // target is smaller than mid
            return recursive(matrix, startRow, midRow, startCol, midCol, target)
                   || recursive(matrix, midRow, endRow, startCol, midCol, target)
                   || recursive(matrix, startRow, midRow, midCol, endCol, target);
        }

    }


    public static void main(String[] args) {
//        int[][] matrix = {
//                { 1,  4,  7, 11, 15},
//                { 2,  5,  8, 12, 19},
//                { 3,  6,  9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}
//            };
        int[][] matrix = {{5, 7}, {6, 8}};
        boolean result = new SearchA2DArrayMatrix().searchMatrix(matrix, 8);
        System.out.println(result);
    }
}
