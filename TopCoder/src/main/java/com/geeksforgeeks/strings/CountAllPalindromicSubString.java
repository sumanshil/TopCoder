package com.geeksforgeeks.strings;

public class CountAllPalindromicSubString {

    public void find (String input){
        int dp[][] = new int[input.length()][input.length()];
        boolean palindrome[][] = new boolean[input.length()][input.length()];

        int n = input.length();
        for (int i = 0 ; i < n-1 ; i ++){
            int j = i + 1;
            if (input.charAt(i) == input.charAt(j)) {
                dp[i][j] = 1;
                palindrome[i][j] = true;
            }
        }

        for ( int i = 0 ; i < n ; i++) {
            palindrome[i][i] = true;
        }

        for ( int L = 3; L <= n ; L++) {
            for (int i = 0; i < n; i++) {
                int j = i + L - 1;
                if ( j < n) {
                    if (input.charAt(i) == input.charAt(j) && palindrome[i + 1][j - 1]) {
                        palindrome[i][j] = true;
                        dp[i][j] = dp[i + 1][j - 1] + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                    }
                }
            }
        }

        System.out.println(dp[0][input.length()-1]);
    }

    public static void main(String[] args) {
        String str = "abbaeae";
        new CountAllPalindromicSubString().find(str);
    }
}
