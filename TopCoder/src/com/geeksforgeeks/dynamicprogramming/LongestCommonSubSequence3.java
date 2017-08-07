package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
public class LongestCommonSubSequence3 {

    public static void find(String str1, String str2) {
        int result = findRecursive(str1, str2, str1.length(), str2.length());
        System.out.println(result);
    }

    private static int findRecursive(String str1,
                                     String str2,
                                     int n1,
                                     int n2) {
        if (n1 == 0 || n2 == 0){
            return 0;
        }

        if (str1.charAt(n1-1) == str2.charAt(n2-1)) {
            return findRecursive(str1, str2, n1-1, n2-1)+1;
        }

        int result1 = findRecursive(str1, str2,n1-1, n2);
        int result2 = findRecursive(str1, str2,   n1,n2-1);
        int result3 = findRecursive(str1, str2, n1-1, n2-1);

        return Math.max(Math.max(result1, result2), result3);
    }

    public static void findDP(String str1, String str2){
        int rowLength = str1.length() + 1;
        int colLength = str2.length() + 1;
        int[][] dp = new int[rowLength][colLength];
        for (int i = 0 ; i < rowLength ; i++) {
            for ( int j = 0 ; j < colLength ; j++) {
                if (i == 0 || j == 0){
                    dp[i][j] = 0;
                    continue;
                }

                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                }
            }
        }
        int result = dp[rowLength-1][colLength-1];
        System.out.println(result);
    }


    public static void main(String[] args) {
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        LongestCommonSubSequence3.findDP(str1, str2);
    }
}


