package com.leetcode;

import java.util.Arrays;

public class PalindromPartition {
    /**
    public int minCut(String s) {
        int n = s.length();
        boolean[][] check = new boolean[n][n];
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i=0;i<s.length();i++){
            for(int j=i;j>=0;j--){
                if(s.charAt(i)!=s.charAt(j)){
                    continue;
                }

                if(j+1>i-1 || check[j+1][i-1]==true){
                    check[j][i] = true;
                    int temp = j-1<0?0:dp[j-1];
                    dp[i] = Math.min(dp[i], temp+1);
                }
            }
        }
        return dp[n-1]-1;
    }
     **/
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int level = 0 ; level < s.length() ; level++) {
            for (int i = 0 ; i + level < s.length() ; i++) {
                if (level == 0) {
                    dp[i][i+level] = true;
                } else if (level == 1 && s.charAt(i) == s.charAt(i+level)) {
                    dp[i][i + level] = true;
                } else {
                    int index1 = i;
                    int index2 = i + level;
                    if (s.charAt(index1) == s.charAt(index2) && dp[index1+1][index2-1]) {
                        dp[index1][index2] = true;
                    }
                }
            }
        }

        int min = 0;
        int[] minCuts = new int[s.length()];

        for (int i = 1 ; i < s.length() ; i++) {
            min++;
            if (dp[0][i]) {
                min = 0;
                minCuts[i] = 0;
                continue;
            }

            for (int j = 1 ; j < i ; j++) {
                int cut = minCuts[j - 1] + 1;
                if (dp[j][i] && min > cut) {
                    min = cut;
                }
            }
            minCuts[i] = min;
        }
        return min;
    }


    public static void main(String[] args) {
        String str = "aaabb";
        int res = new PalindromPartition().minCut(str);
        System.out.println(res);
    }
}
