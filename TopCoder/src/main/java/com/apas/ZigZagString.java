package com.apas;

/**
 * Created by sshil on 7/1/17.
 */
public class ZigZagString {

    int[] x = { 1, -1};
    int[] y = { 0,  1};
    public void print(String str, int row){

        char[] arr = str.toCharArray();
        int dr = 0;
        int startX = 0;
        int startY = 0;
        int charArrIndex = 0;
        char[][] matrix = new char[row][10];
        for ( int i = 0 ; i < row ; i++) {
            for ( int j = 0 ; j < 10 ; j++) {
                matrix[i][j] = ' ';
            }
        }
        while (true) {
            if (charArrIndex == arr.length){
                break;
            }
            matrix[startX][startY] = arr[charArrIndex++];
            int newStartX = startX + x[dr];
            int newStartY = startY + y[dr];
            if (!isValid(newStartX, newStartY, row)){
                dr = (dr + 1) % 2;
                newStartX = startX + x[dr];
                newStartY = startY + y[dr];
                if (isValid(newStartX, newStartY, row)) {
                    startX = newStartX;
                    startY = newStartY;
                } else {
                    break;
                }
            } else {
                startX = newStartX;
                startY = newStartY;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        for ( int i = 0 ; i <row ; i++) {
            for ( int j = 0 ; j <= startY ; j++){
                if (matrix[i][j] != ' '){
                    stringBuilder.append(matrix[i][j]);
                }
            }
        }
        System.out.println(stringBuilder.toString());

    }

    private boolean isValid(int newStartX, int newStartY, int row) {
        return newStartX >= 0 && newStartX < row && newStartY >= 0 && newStartY < 10;
    }

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        int row = 3;
        new ZigZagString().print(str, 3);
    }
}
