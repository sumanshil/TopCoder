package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
public class LongestPalindromicSubSequence1 {

    public void find (String str) {
        int result = recursive(str,0, str.length()-1);
        System.out.println(result);
    }

    private int recursive(String str, int low, int high) {
        if (low == high) {
            return 1;
        }

        if (str.charAt(low) == str.charAt(high) && low + 1 == high) {
            return 2;
        }

        if (str.charAt(low) == str.charAt(high)){
            return recursive(str, low + 1, high-1)+2;
        }

        return Math.max(recursive(str, low+ 1, high), recursive(str, low, high-1));
    }

    private void dp (String str) {
        int rowLength = str.length();
        int colLength = str.length();
        int dp[][] = new int[rowLength][colLength];

        for (int i = 0 ; i < rowLength ; i ++) {
            dp[i][i] = 1;
        }

        for (int i = 0 ; i < rowLength -1 ; i++) {
            if (str.charAt(i) == str.charAt(i+1)) {
                dp[i][i+1] = 2;
            }
        }

        for ( int L = 3 ; L <= rowLength ; L++) {
            for ( int i = 0 ; i < rowLength - L +1 ; i++) {
                int j = i + L -1;
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[0][colLength-1]);
    }

    public static void main(String[] args) {
        String str = "GEEKS FOR GEEKS";
        new LongestPalindromicSubSequence1().dp(str);
    }

}
