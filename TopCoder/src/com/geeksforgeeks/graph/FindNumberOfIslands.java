package com.geeksforgeeks.graph;

/**
 * Created by sshil on 4/1/17.
 */
//http://www.geeksforgeeks.org/find-number-of-islands/
public class FindNumberOfIslands {

    boolean[][] visited;
    public void find(int[][] matrix) {
        visited = new boolean[matrix.length][matrix[0].length];
        int result = 0;
        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    result++;
                    fill(matrix, i, j);
                }
            }
        }
        System.out.println(result);
    }

    private void fill(int[][] matrix, int i, int j) {
        if (!isValid(matrix, i, j)){
            return;
        }

        if (matrix[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        fill(matrix, i+1, j);
        fill(matrix, i, j+1);
        fill(matrix, i-1, j);
        fill(matrix, i, j-1);
        fill(matrix, i-1,j-1);
        fill(matrix, i+1, j+1);
        fill(matrix, i-1, j+1);
        fill(matrix, i+1, j-1);
    }

    private boolean isValid(int[][] matrix, int i, int j) {
        return i >=0
                && i < matrix.length
                && j>= 0
                && j < matrix[0].length;
    }


    public static void main(String[] args) {
        int[][] matrix =  {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};
        new FindNumberOfIslands().find(matrix);

    }
}
