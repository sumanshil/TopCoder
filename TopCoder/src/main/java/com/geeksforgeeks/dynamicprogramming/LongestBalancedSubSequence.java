package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/length-longest-balanced-subsequence/
public class LongestBalancedSubSequence {

    public void find(String str){
        int length = recursive(str, 0, str.length());
        System.out.println(length);
    }

    private int recursive(String str, int low, int high) {
        if (low > high){
            return 0;
        }

        if (str.charAt(low) == '(' && str.charAt(high-1) == ')') {
            return recursive(str, low + 1, high-1)+2;
        } else {
            int result = 0;
            for ( int k = low; k < high ; k++) {
                 int res = recursive(str, low , k) +recursive(str, k+1, high);
                 result = Math.max(res, result);
            }
            return result;
        }
    }

    public void dp(String str) {
        int dp[][] = new int[str.length()][str.length()];

        for (int i = 0 ; i < str.length(); i++) {
            if (str.charAt(i) == '(' && str.charAt(i+1) == ')'){
                dp[i][i+1] = 2;
            }
        }

        for (int L = 2 ; L < str.length() ; L++ ) {
            for ( int i = 0 ; i < str.length() - L  ; i++){
                int j = i + L ;
                if (str.charAt(i) == '(' && str.charAt(j) == ')'){
                    dp[i][j] = dp[i+1][j-1]+2;
                }
                for ( int k = i ; k < j ; k++) {
                        dp[i][j] = Integer.max(dp[i][j], (dp[i][k]+ dp[k+1][j]));
                }
            }
        }
        System.out.println(dp[0][str.length()-1]);
    }



    public static void main(String[] args) {
        new LongestBalancedSubSequence().dp("())");
    }
}
