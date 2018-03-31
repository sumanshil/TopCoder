package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/longest-common-subsequence/
public class LongestCommonSubSequence1 {

    public void find(String str1, String str2) {
        int result = recursive(str1, str2, str1.length(), str2.length());
        System.out.println(result);
    }

    private int recursive(String str1, String str2, int str1Index, int str2Index) {
        if (str1Index == 0 || str2Index == 0){
            return 0;
        }

        if (str1.charAt(str1Index-1) == str2.charAt(str2Index-1)) {
            return recursive(str1, str2, str1Index-1, str2Index-1)+1;
        }

        return Math.max(recursive(str1, str2, str1Index-1, str2Index),
                        recursive(str1, str2, str1Index, str2Index-1));
    }

    private int dp(String str1, String str2) {
        int matrix[][] = new int[str1.length()+1][str2.length()+1];
        int rowLength = str1.length()+1;
        int colLength = str2.length()+1;
        for ( int i = 0 ; i < colLength ; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 0 ; i < rowLength ; i++){
            matrix[i][0] = 0;
        }

        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 1 ; j < colLength ; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 1 ; j < colLength ; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        System.out.println(max);
        int startX = rowLength-1;
        int startY = colLength-1;
        StringBuilder stringBuilder = new StringBuilder();
        while ( startX > 0 && startY > 0) {
            if (str1.charAt(startX-1) == str2.charAt(startY-1)) {
                stringBuilder.append(str1.charAt(startX-1));
                startX = startX-1;
                startY = startY-1;
            } else {
                if (matrix[startX][startY-1] > matrix[startX-1][startY]){
                    startY = startY -1;
                } else {
                    startX = startX-1;
                }
            }
        }
        System.out.println(stringBuilder.toString());

        return max;
    }


    public static void main(String[] args) {
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        new LongestCommonSubSequence1().dp(str1, str2);
    }
}
