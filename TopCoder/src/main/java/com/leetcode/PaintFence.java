package com.leetcode;

//https://leetcode.com/problems/paint-fence/
public class PaintFence {

    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 1;
        }

        int[] dp = new int[n];

        dp[0] = k;
        dp[1] = k * k;

        for (int i = 2 ; i < n ; i++) {
            dp[i] = (k-1) * (dp[i-1] + dp[i-2]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        int res = new PaintFence().numWays(n, k);
        System.out.println(res);
    }
}
