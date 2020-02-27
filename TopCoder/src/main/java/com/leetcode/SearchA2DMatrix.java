package com.leetcode;

//https://leetcode.com/problems/search-a-2d-matrix/
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
         int rowLength = matrix.length;
         int colLength = matrix[0].length;

         int row = findRow(matrix, target, 0, rowLength-1);
         if (row == -1) {
             return false;
         }
         int col = findCol(matrix, target, 0, colLength-1, row);
         if (col == -1) {
             return false;
         }
         return true;

    }

    private int findCol(int[][] matrix, int target, int startCol, int endCol, int row) {
        if (startCol > endCol) {
            return -1;
        }
        if (startCol == endCol) {
            if (matrix[row][startCol] == target) {
                return startCol;
            } else {
                return -1;
            }
        }

        int mid = (startCol + endCol)/2;

        if (matrix[row][mid] == target) {
            return mid;
        }

        if (matrix[row][mid] > target) {
            return findCol(matrix, target, startCol, mid-1, row);
        } else {
            return findCol(matrix, target, mid+1, endCol, row);
        }
    }

    private int findRow(int[][] matrix, int target, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (matrix[start][0] == target) {
            return start;
        }

        if (matrix[end][0] == target) {
            return end;
        }

        int mid = (start + end)/2;

        if (matrix[mid][0] <= target && matrix[mid][matrix[0].length-1] >= target) {
            return mid;
        }

        if (target > matrix[mid][0]) {
            return findRow(matrix, target, mid+1, end);
        } else {
            return findRow(matrix, target, start, mid-1);
        }

    }

    public static void main(String[] args) {
        int[][] matrix ={{-8,-8,-7,-7,-6,-5,-3,-2},
                         {0,0,1,3,4,6,8,8},
                         {11,12,14,16,18,18,19,19},
                         {22,23,25,27,28,30,30,31},
                         {34,35,37,39,40,42,43,45},
                         {48,50,51,51,53,54,55,57},
                         {58,60,62,62,62,63,63,65},
                         {68,69,71,72,72,72,74,76}};
        int target = 76;

        /*
        int[][] matrix = {
                {1, 3, 5},
        };*/

        //int target = 3;
        boolean result = new SearchA2DMatrix().searchMatrix(matrix, target);
        System.out.println(result);

    }

}
