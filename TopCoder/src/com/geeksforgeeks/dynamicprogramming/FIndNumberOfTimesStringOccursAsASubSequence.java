package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/29/16.
 */
//http://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
public class FIndNumberOfTimesStringOccursAsASubSequence {

    public void find(String input, String pattern){
        //int result = recursive(input, pattern, input.length()-1, pattern.length()-1, 0);
        //int result = recursive2(input, pattern, input.length(), pattern.length());
        int result = dp(input, pattern);
        System.out.println(result);
    }

    private int recursive(String input, String pattern, int inputIndex, int patternIndex, int count) {
        if (inputIndex  < 0 || patternIndex < 0){
            return count;
        }

        if (input.charAt(inputIndex) == pattern.charAt(patternIndex)) {
            if (patternIndex == 0){
                return recursive(input, pattern, inputIndex-1, patternIndex, count+1);
            } else {
                return recursive(input, pattern, inputIndex-1, patternIndex, count)+
                        recursive(input, pattern, inputIndex-1, patternIndex-1, count);
            }
        } else {
            return recursive(input, pattern, inputIndex-1, patternIndex, count);
        }
    }

    private int recursive1(String input , String pattern, int inputIndex, int patternIndex) {
        if ((inputIndex < 0  && patternIndex < 0) || (patternIndex < 0) ) {
            return 1;
        }
        if (inputIndex < 0) {
            return 0;
        }
        if (input.charAt(inputIndex) == pattern.charAt(patternIndex)) {
            return recursive1(input, pattern, inputIndex-1, patternIndex)+ recursive1(input, pattern, inputIndex-1, patternIndex-1);
        } else {
            return recursive1(input, pattern, inputIndex-1, patternIndex);
        }

    }


    private int recursive2(String input , String pattern, int inputIndex, int patternIndex) {
        if ((inputIndex == 0  && patternIndex == 0) || (patternIndex == 0) ) {
            return 1;
        }
        if (inputIndex == 0) {
            return 0;
        }
        if (input.charAt(inputIndex-1) == pattern.charAt(patternIndex-1)) {
            return recursive2(input, pattern, inputIndex-1, patternIndex)+ recursive2(input, pattern, inputIndex-1, patternIndex-1);
        } else {
            return recursive2(input, pattern, inputIndex-1, patternIndex);
        }
    }

    private int dp(String input, String pattern){
        int row = input.length()+1;
        int col = pattern.length()+1;
        int[][] matrix = new int[row][col];
        for (int i = 1 ; i < col ; i++){
            matrix[0][i] = 0;
        }

        for (int i = 0 ; i < row ; i++) {
            matrix[i][0] = 1;
        }


        for ( int i = 1 ; i < row ; i++) {
            for ( int j = 1 ; j < col ; j++) {
                if (input.charAt(i-1) == pattern.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1];
                } else {
                    matrix[i][j] = matrix[i-1][j];
                }
            }
        }
        int result = matrix[row-1][col-1];
      //  System.out.println(result);
        return result;
    }


    public static void main(String[] args) {
       // String input = "GeeksforGeeks";
       // String pattern = "Gks";
        String input = "GeeksForGeeks";
        String pattern = "Gks";
        new FIndNumberOfTimesStringOccursAsASubSequence().find(input, pattern);
    }
}
