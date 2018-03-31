package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/12/17.
 */
//http://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome/
public class MinimumNumberOfDeletionToMakeAStringPalindrom {

    public void find(String str){
        int[][] dp = new int[str.length()][str.length()];
        for ( int i = 0 ; i < str.length(); i++){
            dp[i][i] = 1;
        }

        for ( int i = 0 ; i < str.length()-1 ; i++){
            int j = i + 1;
            if (str.charAt(i) == str.charAt(j)){
                dp[i][j] = 2;
            }
        }

        for ( int length = 2 ; length <= str.length() ; length++) {
            for ( int i = 0 ; i + length < str.length() ; i++) {
                int j = i + length;
                if (str.charAt(i) == str.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        int maxLength = dp[0][str.length()-1];
        System.out.println(str.length() - maxLength);
    }
    public static void main(String[] args) {
        String str = "geeksforgeeks";
        new MinimumNumberOfDeletionToMakeAStringPalindrom().find(str);
    }
}
