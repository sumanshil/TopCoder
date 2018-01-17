package com.geeksforgeeks.dynamicprogramming;

//https://www.geeksforgeeks.org/longest-repeated-subsequence/
public class LongestRepeatedSubsequence1 {

    public void find (String str) {
        int n = str.length();
        int dp[][] = new int[n+1][n+1];

        for ( int i = 1 ; i <= n ; i++ ) {
            for ( int j = 1 ; j <= n ; j++ ) {
                if (str.charAt(i-1) == str.charAt(j-1) && i != j) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        for (int i = 0 ; i <= n ; i++) {
            for (int j = 0 ; j <= n ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n ; i > 0 ; i--) {
            for (int j = n; j > 0; j--) {
                if (dp[i][j] == dp[i-1][j-1] + 1) {
                    stringBuilder.append(str.charAt(i-1));
                    i--;
                    j--;
                } else if (dp[i][j] == dp[i][j-1]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        String str = "aabb";
        new LongestRepeatedSubsequence1().find(str);
    }

}
