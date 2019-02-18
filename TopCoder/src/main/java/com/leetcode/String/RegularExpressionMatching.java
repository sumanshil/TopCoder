package com.leetcode.String;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {

        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if(p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || s.length() > 0 && ((s.charAt(0) == p.charAt(0) || '.' == p.charAt(0))
                                                  && isMatch(s.substring(1), p));
        }

        if (s.length() > 0  && ((s.charAt(0) == p.charAt(0)) || p.charAt(0) == '.')) {
            return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }


    public boolean dp(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        int rowLength = s.length() + 1;
        int colLength = p.length() + 1;

        for (int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = false;
        }

        for (int j= 1 ; j < colLength ; j++) {
            if (j > 1 && p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 1 ; j < colLength ; j++) {
                if (p.charAt(j-1) == '*') {
                    if ( j > 1 ) {
                        dp[i][j] = dp[i][j - 2];
                    }
                    if ( j > 1 && (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.')) {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } else if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[rowLength-1][colLength-1];
    }
    public static void main(String[] args) {
        String s = "aaa";
        String p = "ab*a*c*a";
        boolean res = new RegularExpressionMatching().dp(s, p);
        System.out.println(res);
    }

}
