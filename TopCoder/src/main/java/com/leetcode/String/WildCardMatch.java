package com.leetcode.String;

//https://leetcode.com/problems/wildcard-matching/
public class WildCardMatch {

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 1 && p.charAt(0) == '*') {
            return true;
        }
        //return recursive(0, 0, s, p);
        return iterative(s, p);
    }

    private boolean dp(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;

        for (int i = 1 ; i < s.length() + 1; i++) {
            dp[i][0] = false;
        }

        for (int j = 1 ; j < p.length() + 1; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-1];
            }
        }

        for (int i = 1 ; i < s.length() + 1 ; i++) {
            for (int j = 1 ; j < p.length() + 1 ; j++) {
                if (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    private boolean recursive(int sIndex, int pIndex, String s, String p) {
        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        }

        if (sIndex == s.length() && pIndex == p.length() - 1 && p.charAt(pIndex) == '*') {
            return true;
        }

        if (pIndex == p.length()) {
            return false;
        }

        if (sIndex == s.length() && p.charAt(pIndex) == '*') {
            return recursive(sIndex, pIndex + 1, s, p);
        } else {
            if ((p.charAt(pIndex) == '?' && sIndex < s.length()) || ( sIndex < s.length() && s.charAt(sIndex) == p.charAt(pIndex))) {
                return recursive(sIndex + 1, pIndex + 1, s, p);
            } else if (p.charAt(pIndex) == '*') {
                return recursive(sIndex + 1, pIndex, s, p) || recursive(sIndex, pIndex + 1, s, p);
            } else {
                return false;
            }
        }
    }

    private boolean iterative(String s, String p) {
        int sIndex = 0;
        int pIndex = 0;
        int starIndex = -1;
        int ss = 0;

        while (sIndex < s.length()) {

            if (pIndex < p.length()
                && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
                sIndex++;
                pIndex++;
                continue;
            } else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                starIndex = pIndex;
                pIndex = pIndex + 1;
                ss = sIndex;
                continue;
            } else if (starIndex >= 0) {
                pIndex = starIndex+1;
                ss++;
                sIndex = ss;
                continue;
            }
            return false;
        }

        while (pIndex < p.length() && p.charAt(pIndex) == '*') {
            pIndex++;
        }
        return pIndex == p.length();
    }

    public static void main(String[] args) {
        //String s = "acdcb";
        //String p = "a*c?b";
        //String s = "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba";
        //String p = "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*";
        //String s = "acdcb";
        //String p = "a*c?b";
        String s = "acdcb";
        String p = "a*c?b";
        boolean result = new WildCardMatch().isMatch(s, p);
        System.out.println(result);
    }
}
