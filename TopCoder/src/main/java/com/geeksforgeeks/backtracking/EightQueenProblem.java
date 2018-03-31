package com.geeksforgeeks.backtracking;

//http://www.geeksforgeeks.org/8-queen-problem/
public class EightQueenProblem {

    static int queens = 4;
    static int matrix[][] = null;
    public static void find () {
         matrix = new int[queens][queens];
        for ( int i = 0 ; i < queens ; i++) {
            for (int j = 0 ; j < queens ; j++) {
                matrix[i][j] = 0;
            }
        }
        recursive();
    }

    private static boolean recursive() {
        if (queens == 0){
            // all are exuasuted.
            printMatrix();
            return true;
        }


        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0; j < matrix[0].length ; j++) {
                if (matrix[i][j] == 0 && isValidPosition(i, j)){
                    matrix[i][j] = queens--;
                    if (recursive()){
                        return true;
                    } else {
                        matrix[i][j] = 0;
                        queens++;
                    }
                }
            }
        }
        return false;

    }

    private static boolean isValidPosition(int row, int col) {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;

        for (int i = 0 ; i < colLength ; i++) {
            if (matrix[row][i] != 0){
                return false;
            }
        }

        for (int i = 0 ; i < rowLength ; i++) {
            if (matrix[i][col] != 0){
                return false;
            }
        }

        int r = row;
        int c = col;
        while (r < rowLength && c < colLength) {
            if (matrix[r++][c++] != 0){
                return false;
            }
        }

        r = row;
        c = col;
        while (r >= 0 && c>= 0 && r < rowLength && c < colLength){
            if (matrix[r--][c--] != 0){
                return false;
            }
        }

        return true;
    }

    private static void printMatrix() {
        for ( int i = 0 ; i < matrix.length ; i++){
            for ( int j = 0 ; j < matrix[0].length ; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        find();
    }
}
