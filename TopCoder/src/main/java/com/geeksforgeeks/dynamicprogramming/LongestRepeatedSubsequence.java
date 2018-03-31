package com.geeksforgeeks.dynamicprogramming;

import java.util.Stack;

//http://www.geeksforgeeks.org/longest-repeated-subsequence/
public class LongestRepeatedSubsequence {

    public void find (String str) {
        int result = recursive(str, str, str.length(), str.length());
        System.out.println(result);
    }

    private int recursive(String str1, String str2, int str1Index, int str2Index) {
        if (str1Index == 0 || str2Index == 0){
            return 0;
        }

        if (str1.charAt(str1Index-1) == str2.charAt(str2Index-1)){
            if (str1Index == str2Index) {
                return Math.max(recursive(str1, str2, str1Index-1, str2Index),
                                recursive(str1, str2, str1Index, str2Index-1));
            } else {
                return recursive(str1, str2, str1Index-1, str2Index) + 1;
            }
        } else {
            return Math.max(recursive(str1, str2, str1Index-1, str2Index),
                            recursive(str1, str2, str1Index, str2Index-1));
        }
    }

    public void dp (String str) {
        int rowLength = str.length()+1;
        int colLength = str.length()+1;
        int matrix[][] = new int[rowLength][colLength];

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (str.charAt(i-1)== str.charAt(j-1) && i != j){
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }
        System.out.println(matrix[rowLength-1][colLength-1]);
        print(str, matrix);
    }

    public void print(String str, int matrix[][]){
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        Stack<String> stack = new Stack<>();
        for (int i = rowLength-1, j = colLength-1 ; i > 0 && j > 0 ; ) {
            if (str.charAt(i-1) == str.charAt(j-1) && i != j) {
                stack.push(str.charAt(i-1)+"");
                i = i - 1;
                j = j - 1;
            } else if (matrix[i-1][j] > matrix[i][j-1]) {
                i = i-1;
            } else {
                j = j-1;
            }
        }
        stack.stream().forEach(System.out::print);

    }

    public static void main(String[] args) {
        String str1 = "aabebcdd";
        new LongestRepeatedSubsequence().dp(str1);
    }

}
