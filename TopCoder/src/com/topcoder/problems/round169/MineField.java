package com.topcoder.problems.round169;

import com.sun.javafx.image.IntPixelGetter;

/**
 * Created by SumanChandra on 11/8/2015.
 */
//https://community.topcoder.com/stat?c=round_overview&er=5&rd=4650
//https://community.topcoder.com/stat?c=problem_statement&pm=1877&rd=4650
public class MineField {
    private char[][] matrix = new char[9][9];
    public String[] getMineField(String mineLocations){
        populateMineFields(mineLocations);
        count();
        String[] retVal = new String[9];
        for (int i = 0; i < 9 ;i++){
            StringBuffer sb = new StringBuffer();
            for ( int j = 0 ; j < 9 ; j++){
                sb.append(matrix[i][j]);
            }
            retVal[i] = sb.toString();
        }
        return retVal;
    }

    private void count() {
        for (int i = 0 ; i < 9 ; i++) {
            for ( int j = 0; j < 9 ; j++) {
                if (matrix[i][j] != 'M') {
                    checkForMines(i, j);
                }
            }
        }

    }

    private void checkForMines(int x, int y) {
        int counter = 0;
        for ( int i = -1 ; i<= 1; i++) {
            for (int j = -1 ; j<= 1; j++) {
                if ( i == 0 && j == 0){
                    continue;
                }
                int posX = x + i;
                int posY = y + j;
                if ( isMineField(posX, posY)){
                    counter++;
                }
            }
        }
        matrix[x][y] = (char)counter;
    }

    private boolean isMineField(int posX, int posY) {
        return (isValidField(posX, posY) && matrix[posX][posY] == 'M');
    }

    private boolean isValidField(int posX, int posY) {
        return posX >= 0 && posX < 9 && posY >= 0 && posY < 9;
    }

    private void populateMineFields(String mineLocations) {
        String str = mineLocations.substring(1, mineLocations.length()-1);
        String[] arr = str.split("\\)\\(");
        for (String strPos : arr){
            String[] posArr = strPos.split(",");
            if (posArr.length == 2) {
                int x = Integer.parseInt(posArr[0]);
                int y = Integer.parseInt(posArr[1]);
                matrix[x][y] = 'M';
            } else {
                System.out.println("Not a proper array");
            }
        }
    }
}
