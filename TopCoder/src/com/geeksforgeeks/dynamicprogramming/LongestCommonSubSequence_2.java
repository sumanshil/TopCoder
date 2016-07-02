package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/1/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
public class LongestCommonSubSequence_2 {
    public void find(String str1, String str2){
        //int result = findRecursive(str1, str2, str1.length()-1, str2.length()-1);
        //System.out.println(result);
        findDynamic(str1, str2);
    }

    private int findRecursive(String str1,
                              String str2,
                              int index1,
                              int index2) {
        if ((index1 == 0)||(index2 == 0 )){
            return 0;
        }

        if (str1.charAt(index1) == str2.charAt(index2)) {
            return findRecursive(str1, str2, index1-1, index2-1)+1;
        } else {
            return Math.max(findRecursive(str1, str2, index1-1, index2),
                            findRecursive(str1, str2, index1, index2-1));
        }
    }

    private void findDynamic(String str1, String str2){
        int[][] matrix = new int[str1.length()+1][str2.length()+1];
        matrix[0][0] = 0;
        int rowLength = matrix.length;
        int colLength = matrix[0].length;

        for ( int i = 0 ; i < colLength ; i++) {
            matrix[0][i] = 0;
        }

        for ( int i = 0 ; i < rowLength ; i++) {
            matrix[i][0] = 0;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1]+1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j-1], matrix[i-1][j]);
                }
            }
        }
        System.out.println(matrix[rowLength-1][colLength-1]);




    }
    public static void main(String[] args) {
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        new LongestCommonSubSequence_2().find(str1, str2);
    }
}
