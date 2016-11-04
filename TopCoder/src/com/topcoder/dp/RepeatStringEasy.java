package com.topcoder.dp;

/**
 * Created by sshil on 9/25/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=14390
public class RepeatStringEasy {

    public int maximalLength(String s) {
        int result = Integer.MIN_VALUE;
        for ( int i = 0 ; i < s.length() ; i++){
            result = Math.max(result, dp(s.substring(0, i), s.substring(i)));
        }
        System.out.println(result);
        return result;
    }

    private int dp(String substring1, String substring2) {
        int rowLength = substring1.length()+1;
        int colLength = substring2.length()+1;
        int[][] matrix = new int[rowLength][colLength];
        matrix[0][0] = 0;
        for ( int i = 1 ; i < colLength ; i++) {
            matrix[0][i] = i;
        }
        for ( int i = 1 ; i < rowLength ; i++){
            matrix[i][0] = i;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1; j < colLength ; j++) {
                if (substring1.charAt(i-1) == substring2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1];
                } else {
                    matrix[i][j] = Math.min(matrix[i-1][j]+1, Math.min(matrix[i][j-1]+1, matrix[i-1][j-1]+2));
                }
            }
        }
        int minOperations = matrix[rowLength-1][colLength-1];
        return (substring1.length()+substring2.length())-minOperations;
    }

    public static void main(String[] args) {
        String str = "aababbababbabbbbabbabb";
        new RepeatStringEasy().maximalLength(str);
    }
}
