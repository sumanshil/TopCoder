package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix {

    int[] x = {0, 1,  0, -1};
    int[] y = {1, 0, -1,  0};


    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length-1;
        int endCol = matrix[0].length-1;

        List<Integer> result = new ArrayList<>();

        while (startRow <= endRow || startCol <= endCol) {
            int j = startCol;

            if (j <= endCol) {
                while (j <= endCol) {
                    result.add(matrix[startRow][j]);
                    j++;
                }
            } else {
                break;
            }

            int i = startRow+1;

            if ( i <= endRow) {
                while (i <= endRow) {
                    result.add(matrix[i][endCol]);
                    i++;
                }
            } else {
                break;
            }

            j = endCol - 1;
            if ( j >= startCol) {
                while (j >= startCol) {
                    result.add(matrix[endRow][j]);
                    j--;
                }
            } else {
                break;
            }

            i = endRow - 1;
            if ( i > startRow) {
                while (i > startRow) {
                    result.add(matrix[i][startCol]);
                    i--;
                }
            } else {
                break;
            }

            startCol++;
            endCol--;
            startRow++;
            endRow--;
        }
        return result;
    }

    public static void main(String[] args) {
        /*
        int[][] matrix = {
                {1,2},
                {3,4},
                {5,6},
                {7,8},
                {9,10},
                {11,12}
        };
        */
        /*
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
         */
        /*
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };

         */
        int[][] matrix = {
                {2,5,8},
                {4,0,-1}
        };

        List<Integer> result = new SpiralMatrix().spiralOrder(matrix);
        System.out.println(result);
    }

}
