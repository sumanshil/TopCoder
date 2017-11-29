package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/length-longest-balanced-subsequence/
public class LengthOfLongestBalancedSubsequence1 {

    public void find (String str) {
        int dp[][] = new int[str.length()][str.length()];
        for (int i = 0 ; i < str.length()-1; i++){
            if (str.charAt(i) == '(' && str.charAt(i+1) ==')') {
                dp[i][i+1] = 2;
            }
        }

        for (int gap = 2; gap < str.length() ; gap ++) {
            for ( int i = 0 , j = gap ; j < str.length() ; i++, j++) {
                if (str.charAt(i) == '(' && str.charAt(j) == ')') {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                for ( int k = i ; k < j ; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
            }
        }
        System.out.println(dp[0][str.length()-1]);
    }


    public static void main(String[] args) {
        String str = "()())";
        new LengthOfLongestBalancedSubsequence1().find(str);
    }
}
