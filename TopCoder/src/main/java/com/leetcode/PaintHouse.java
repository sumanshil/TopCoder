package com.leetcode;

import com.sun.prism.paint.Paint;

//https://leetcode.com/problems/paint-house/
public class PaintHouse {
    int minCost = Integer.MAX_VALUE;
    public int minCost(int[][] costs) {
        //recursive(0, costs, -1, 0);
        //return minCost;
        return dp(costs);
    }

    private int dp(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];

        for (int i = 0 ; i < 3 ; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1 ; i < costs.length ; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        }

        return Math.min(dp[dp.length-1][0], Math.min(dp[dp.length - 1][1], dp[dp.length-1][2]));
    }

    private void recursive(int index, int[][] costs, int excludeIndex, int currentCost) {

        if (index == costs.length) {
            minCost = Math.min(currentCost, minCost);
            return;
        }

        for (int i = 0 ; i < 3 ; i++) {
            if (excludeIndex != -1 && excludeIndex == i) {
                continue;
            }
            recursive( index +1 , costs, i, currentCost + costs[index][i]);
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{17,2,17},{16,16,5},{14,3,19}};
        int res = new PaintHouse().minCost(arr);
        System.out.println(res);
    }
}
