package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/count-strings-adjacent-characters-difference-one/
public class CountStringsAdjacentCharsDifferentOne {

    public void find (int n) {
        int result = 0;
        for (int i = 1 ; i <= 26 ; i++) {
            result +=recursive(i, n);
        }
        System.out.println(result);
    }

    private int recursive(int charIndex, int length) {
        if (charIndex > 26 || charIndex <= 0){
            return 0;
        }
        if (length == 1) {
            return 1;
        }

        return recursive(charIndex-1, length-1)
                + recursive(charIndex + 1, length-1);

    }

    public void dp(int n){
        int rowLength = 27;
        int colLength = n+1;
        // length = column
        // charIndex = row
        int matrix[][] = new int[rowLength][colLength];
        // 0th row and 0th col is unused

        for (int  charIndex = 1; charIndex < rowLength ; charIndex++ ) {
            matrix[charIndex][1] = 1;
        }

        for ( int length = 2 ; length < colLength ; length++) {
            for (int charIndex = 1 ; charIndex < rowLength ; charIndex++) {
                int value ;
                value = matrix[charIndex-1][length-1];
                if (charIndex + 1 < rowLength){
                    value += matrix[charIndex+1][length-1];
                }
                matrix[charIndex][length] = value;
            }
        }

        int result = 0;
        for (int i = 1 ; i < rowLength ; i++){
            result += matrix[i][colLength-1];
        }
        System.out.println(result);
    }


    public static void main(String[] args) {
        int n = 4;
        new CountStringsAdjacentCharsDifferentOne().dp(n);
    }

}
