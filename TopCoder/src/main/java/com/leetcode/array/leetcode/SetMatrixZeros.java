package com.leetcode.array.leetcode;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/set-matrix-zeroes/
public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for ( int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = -1;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        new SetMatrixZeros().setZeroes(matrix);
        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
