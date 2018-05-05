package com.leetcode.array.leetcode;

//https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        boolean dp[][] = new boolean[s.length()][s.length()];
        String res = null;
        for (int i = s.length()-1 ; i >= 0 ; i--) {
            for (int j = i ; j < s.length() ; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ( j - i < 3 || dp[i+1][j-1]);

                if (dp[i][j] && (res == null || j-i+1 > res.length())) {
                    res = s.substring(i, j+1);
                }

            }
        }
        return res;
    }


    /*
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int dp[][] = new int[s.length()][s.length()];
        for ( int i = 0 ; i < s.length() ; i++) {
            dp[i][i] = 1;
        }

        int maxLength = Integer.MIN_VALUE;
        String result = s.charAt(0)+"";
        for (int L = 2 ; L <= s.length() ; L++) {
            for ( int i = 0 ; i + L -1 < s.length() ; i++) {
                int j = i + L -1;
                if (s.charAt(i) == s.charAt(j)) {
                   if (L == 2) {
                      dp[i][j] = 2;
                       if (dp[i][j] > maxLength) {
                           maxLength = dp[i][j];
                           result = s.substring(i, j + 1);
                       }
                   } else {
                       if (dp[i + 1][j - 1] > 0) {
                           dp[i][j] = dp[i + 1][j - 1] + 2;
                           if (dp[i][j] > maxLength) {
                               maxLength = dp[i][j];
                               result = s.substring(i, j + 1);
                           }
                       }
                   }
                }
            }
        }
        return result;
    }
    */

    public static void main(String[] args) {
        String str = "cbbd";
        String res = new LongestPalindromicSubstring().longestPalindrome(str);
        System.out.println(res);
    }
}
