package com.leetcode.array.leetcode;

public class MaxAreaOfIsland {

    boolean visited[][] = null;
    int matrix[][] = null;

    public int maxAreaOfIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        matrix = grid;
        int currentMax = Integer.MIN_VALUE;
        for (int i = 0 ; i < visited.length ; i++) {
            for ( int j = 0 ; j < visited[0].length ; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int max = fillTheMatrix(i, j);
                    currentMax = Integer.max(max, currentMax);
                }
            }
        }
        return currentMax;
    }

    private int fillTheMatrix(int row, int col) {
        if (isInValid(row, col)) {
            return 0;
        }
        visited[row][col] = true;
        if (matrix[row][col] == 1
            && isInValid(row-1 , col)
            && isInValid(row+1, col)
            && isInValid(row, col-1)
            && isInValid(row, col+1)) {
            return 1;
        }


        if (matrix[row][col] == 1) {
            return fillTheMatrix(row-1, col)
                   + fillTheMatrix(row+1, col)
                   + fillTheMatrix(row, col-1)
                   + fillTheMatrix(row, col+1)+1;
        }

        return 0;

    }

    private boolean isInValid(int row, int col) {
        return row < 0
               || row >= matrix.length
               || col < 0
               || col >= matrix[0].length
               || visited[row][col]
               || matrix[row][col] == 0 ;
    }

    public static void main(String[] args) {
        int matrix[][] =
                {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
                 {0,1,1,0,1,0,0,0,0,0,0,0,0},
                 {0,1,0,0,1,1,0,0,1,0,1,0,0},
                 {0,1,0,0,1,1,0,0,1,1,1,0,0},
                 {0,0,0,0,0,0,0,0,0,0,1,0,0},
                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
                 {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int result = new MaxAreaOfIsland().maxAreaOfIsland(matrix);
        System.out.println(result);
    }
}
