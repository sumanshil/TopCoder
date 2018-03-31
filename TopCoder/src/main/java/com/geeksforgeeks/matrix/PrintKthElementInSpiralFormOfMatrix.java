package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 8/14/2016.
 */
//http://www.geeksforgeeks.org/print-kth-element-spiral-form-matrix/
public class PrintKthElementInSpiralFormOfMatrix {

    private int[] x = {0,  1,  0, -1};
    private int[] y = {1,  0, -1,  0};
    public void find(int[][] matrix, int k){
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int startX = 0;
        int startY = 0;
        int diffIndex = 0;
        visited[startX][startY] = true;
        System.out.println(matrix[startX][startY]);
        int count = 1;
        while(true){
            int newX = startX + x[diffIndex];
            int newY = startY + y[diffIndex];
            if (isValid(newX, newY, matrix) && !visited[newX][newY]){
                count++;
                visited[newX][newY] = true;
                startX = newX;
                startY = newY;
                System.out.println(matrix[newX][newY]);
            } else {
                diffIndex = (diffIndex+1)%4;
                newX = startX + x[diffIndex];
                newY = startY + y[diffIndex];
                if (isValid(newX, newY, matrix) && !visited[newX][newY]){
                    startX = newX;
                    startY = newY;
                    System.out.println(matrix[newX][newY]);
                    visited[newX][newY] = true;
                    count++;
                } else {
                    break;
                }
            }
        }
    }

    private boolean isValid(int newX, int newY, int[][] matrix) {
        return newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix ={{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}};
            new PrintKthElementInSpiralFormOfMatrix().find(matrix, 6);
    }
}
