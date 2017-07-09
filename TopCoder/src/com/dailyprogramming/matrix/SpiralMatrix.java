package com.dailyprogramming.matrix;

/**
 * Created by sshil on 6/25/17.
 */
public class SpiralMatrix {

    int[] x = {0,  1,  0, -1};
    int[] y = {1,  0, -1,  0};

    public void find(int n) {
        int[][] matrix = new int[n][n];
        int startX = 0;
        int startY = 0;
        int dr = 0;
        int number = 1;

        while (true){
            matrix[startX][startY] = number;
            int newStartX = startX + x[dr];
            int newStartY = startY + y[dr];
            if (!isValid(n, newStartX, newStartY)){
                dr = (dr + 1)%4;
                newStartX = startX + x[dr];
                newStartY = startY + y[dr];
            }
            if (matrix[newStartX][newStartY] != 0){
                dr = (dr + 1)%4;
                newStartX = startX + x[dr];
                newStartY = startY + y[dr];
                if (matrix[newStartX][newStartY] != 0) {
                    break;
                }
            }
            startX = newStartX;
            startY = newStartY;
            number += 1;
        }
        for ( int i = 0 ; i < n ; i++){
            for ( int j = 0 ; j < n ; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private boolean isValid(int n, int newStartX, int newStartY) {
        return newStartX >= 0 && newStartX < n && newStartY >= 0 && newStartY < n;
    }

    public static void main(String[] args) {
        int n = 5;
        new SpiralMatrix().find(n);
    }
}
