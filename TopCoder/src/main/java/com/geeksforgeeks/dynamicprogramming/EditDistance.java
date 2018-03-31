package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/5/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
public class EditDistance {
    public int find(String str1, String str2){
        //int result = findRecursive(str1, str2, str1.length()-1, str2.length()-1);
        int result = findDynamic(str1, str2);
       // System.out.println(result);
        return result;
    }

    private int findRecursive(String str1,
                              String str2,
                              int str1Index,
                              int str2Index) {
        if (str1Index == 0){
            return str2Index;
        }

        if (str2Index == 0){
            return str1Index;
        }

        if (str1.charAt(str1Index) == str2.charAt(str2Index)) {
            return findRecursive(str1, str2, str1Index-1, str2Index-1);
        }

        int replace = findRecursive(str1, str2, str1Index-1, str2Index-1);
        int insert = findRecursive(str1, str2, str1Index, str2Index-1);
        int delete = findRecursive(str1, str2, str1Index-1, str2Index);
        return 1 + Math.min(replace,Math.min(insert, delete));
    }

    public int findDynamic(String str1, String str2){
        int[][] matrix = new int[str1.length()+1][str2.length()+1];
        int matrixRowLength = matrix.length;
        int matrixColLength = matrix[0].length;

        matrix[0][0] = 0;
        for ( int i = 1 ; i < matrixColLength; i++){
            matrix[0][i] = i;
        }

        for ( int j = 1 ; j < matrixRowLength ; j++) {
            matrix[j][0] = j;
        }

        for (int i = 1; i < matrixRowLength ; i++) {
            for (int j = 1 ; j < matrixColLength ; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1];
                } else {
                    matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1]))+1;
                }
            }
        }
        return matrix[matrixRowLength-1][matrixColLength-1];
    }
    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        int result = new EditDistance().find(str1, str2);
        System.out.println(result);
    }
}
