package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 4/9/2016.
 */
public class MatrixRotation1 {
    static int[][] matrix = {{1,2,3,4},
                             {5,6,7,8},
                             {9,10,11,12},
                             {13,14,15,16}};

    private static final int[] xDiff = {0, 1, 0, -1};
    private static final int[] yDiff = {1, 0,-1,  0};
    private static int INDEX = 0;
    static boolean[][] visited = null;

    public static void rotate(int numberOfRotation){
        visited = new boolean[matrix.length][matrix[0].length];
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;

        int startX = -1;
        int startY = -1;

        while(true){
            startX = startX + 1;
            startY = startY + 1;

            if (visited[startX][startY]){
                break;
            }




        }


    }

    private static void printMatrix() {
        for ( int j = 0 ; j < matrix.length ; j++){
            for ( int k = 0 ; k < matrix[0].length ; k++){
                System.out.print(matrix[j][k]+" ");
            }
            System.out.println();
        }
    }

    private static void swapPosition(int currentX, int currentY, int nextPosX, int nextPosY) {
        int tempX = matrix[currentX][currentY];
        matrix[currentX][currentY] = matrix[nextPosX][nextPosY];
        matrix[nextPosX][nextPosY] = tempX;

    }

    private static boolean isValidPosition(int nextPosX, int nextPosY) {
        return nextPosX >= 0 && nextPosX < matrix.length
            && nextPosY >= 0 && nextPosY < matrix[0].length
            && !visited[nextPosX][nextPosY];
    }

    public static void main(String[] args) {
        MatrixRotation1.rotate(3);
        System.out.println();
        printMatrix();
    }

}
