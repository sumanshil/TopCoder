package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
public class LongestPalidromicSubString1 {

    public void find (String str) {
        int dp[][] = new int[str.length()][str.length()];
        boolean isPalidrom[][] = new boolean[str.length()][str.length()];

        for ( int i = 0 ; i < str.length() ; i++) {
            dp[i][i] = 1;
            isPalidrom[i][i] = true;
        }

        for ( int i = 0 ; i + 2 <= str.length() ; i++) {
            int j = i + 1;
            if (str.charAt(i) == str.charAt(j)) {
                dp[i][j] = 2;
                isPalidrom[i][j] = true;
            } else {
                dp[i][j] = 1;
            }
        }

        int L = 3;
        while ( L <= str.length()) {
            for (int i = 0; i + L <= str.length(); i++) {
                int j = ((i + L) - 1);
                if (str.charAt(i) == str.charAt(j) && isPalidrom[i + 1][j - 1]) {
                    isPalidrom[i][j] = true;
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j-1]);
                }
            }
            L++;
        }
        System.out.println(dp[0][str.length()-1]);
    }

    public static void main(String[] args) {
        String str = "forgeeksskeegfor";
        new LongestPalidromicSubString1().find(str);
    }
}
