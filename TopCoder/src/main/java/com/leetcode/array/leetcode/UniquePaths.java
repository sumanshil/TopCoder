package com.leetcode.array.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UniquePaths {

    public int uniquePaths(int row, int col) {
        int matrix[][] = new int[row][col];
        for (int i = 0 ; i < col ; i++) {
            matrix[0][i] = 1;
        }

        for (int j = 0 ; j < row ; j++) {
            matrix[j][0] = 1;
        }

        for (int i = 1 ; i < row ; i++) {
            for ( int j = 1 ; j < col ; j++) {
                matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
            }
        }

        System.out.println(matrix[row-1][col-1]);
        return matrix[row-1][col-1];
    }


    public int uniquePaths1(int row, int col) {
//        if (row > col) {
//            return uniquePaths1(col, row);
//        }
        // optimized by two cols
        int[] pre = new int[row];
        int[] curr = new int[row];

        Arrays.fill(pre, 1);
        Arrays.fill(curr, 1);

        for ( int currentCol = 1 ; currentCol < col ; currentCol++) {
            for ( int currentRow = 1 ; currentRow < row ; currentRow++) {
                curr[currentRow] = curr[currentRow-1] + pre[currentRow];
            }
            int[] temp = curr;
            curr = pre;
            pre = temp;
        }
        return pre[row-1];
    }

    public int uniquePaths2(int row, int col) {
        //        if (row > col) {
        //            return uniquePaths1(col, row);
        //        }
        // optimized by two cols
        int[] curr = new int[row];

        Arrays.fill(curr, 1);

        for ( int currentCol = 1 ; currentCol < col ; currentCol++) {
            for ( int currentRow = 1 ; currentRow < row ; currentRow++) {
                curr[currentRow] += curr[currentRow-1];
            }
        }
        return curr[row-1];
    }


    public static void main(String[] args) {
        int row = 4;
        int col = 3;
        int result = new UniquePaths().uniquePaths2(row, col);
        System.out.println(result);
    }

}
