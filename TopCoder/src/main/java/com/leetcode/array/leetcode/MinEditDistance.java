package com.leetcode.array.leetcode;

public class MinEditDistance {

    public int minDistance(String word1, String word2) {
       return dp1(word1, word2);
    }

    private int dp1(String word1, String word2) {
        int[] dp = new int[word1.length()+1];

        dp[0] = 0;
        for (int i = 1 ; i < word1.length() + 1 ; i++) {
            dp[i] = dp[i-1] + 1;
        }

        for (int i = 1; i < word2.length() + 1 ; i++) {
            dp[0] = i;
            int pre = i-1;

            for (int j = 1 ; j < word1.length()+1 ; j++) {
                int newPre = dp[j];
                if (word2.charAt(i-1) == word1.charAt(j-1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(pre, Math.min(dp[j], dp[j-1]))+1;
                }
                pre = newPre;
            }
        }
        return dp[dp.length-1];

    }
    private int dp(String word1, String word2) {
        int rowLength = word1.length()+1;
        int colLength = word2.length()+1;

        int[][] dp = new int[rowLength][colLength];

        for (int i = 1 ; i < colLength ; i++) {
            dp[0][i] = i;
        }

        for (int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = i;
        }

        for (int i = 1 ; i < rowLength ; i++) {
            for (int j = 1 ; j < colLength ; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1])+1;
                }
            }
        }
        return dp[rowLength-1][colLength-1];
    }


    /*
    private int recursiveUtil(int word1Index, String word1, int word2Index, String word2) {
        if (word1Index == word1.length() && word2Index < word2.length()) {
            return (word2.length() - word2Index);
        }

        if (word2Index == word2.length() && word1Index < word1.length()) {
            return  word1.length() - word1Index;
        }

        if (word2Index == word2.length() && word1Index ==  word1.length()) {
            return 0;
        }
        if (word1.charAt(word1Index) == word2.charAt(word2Index)) {
            return recursiveUtil(word1Index+1, word1, word2Index + 1, word2);
        } else {
            return Math.min(Math.min(recursiveUtil(word1Index+1, word1, word2Index + 1, word2) + 1
            , recursiveUtil(word1Index+1, word1, word2Index, word2) + 1),
              recursiveUtil(word1Index, word1, word2Index+1, word2) + 1);
        }
    }
    */

    public static void main(String[] args) {
        String word1 = "park";
        String word2 = "spake";

        int result = new MinEditDistance().minDistance(word1, word2);
        System.out.println(result);
    }
}
