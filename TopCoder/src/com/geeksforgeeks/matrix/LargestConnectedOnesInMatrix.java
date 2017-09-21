package com.geeksforgeeks.matrix;

public class LargestConnectedOnesInMatrix {

    private static int[][] matrix = {
            {0,0,0,1,0,0},
            {0,0,1,1,1,0},
            {0,0,1,0,0,0},
            {0,0,0,0,0,0},
            {0,0,1,1,1,1}
    };
    private static boolean[][] visited;

    public static void find() {
        visited = new boolean[matrix.length][matrix[0].length];
        int maxSize = Integer.MIN_VALUE;
        for ( int i = 0 ; i  < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    int result = recursiveUtil(i, j);
                    if (result > maxSize) {
                        maxSize = result;
                    }
                }
            }
        }
        System.out.println(maxSize);
    }

    private static int[] x = {-1,-1,-1,0, 1,1, 1, 0 };
    private static int[] y = {-1, 0, 1,1, 1,0,-1,-1 };
    private static int recursiveUtil(int row, int col) {
        if (!isValid(row, col) || matrix[row][col] == 0){
            return 0;
        }
        if (visited[row][col]){
            return 0;
        }
        visited[row][col] = true;
        int count = 0;
        for ( int index= 0 ; index < 8 ; index++) {
            int newRow = row + x[index];
            int newCol = col + y[index];
            count += recursiveUtil(newRow, newCol);
        }
        return count+1;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public static void main(String[] args) {
         LargestConnectedOnesInMatrix.find();
    }
}
