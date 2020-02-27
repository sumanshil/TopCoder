package com.leetcode;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuyAndSaleStock {

    public int maxProfit(int[] prices) {
        int maxProfit = Integer.MIN_VALUE;

        int minSoFar = prices[0];

        for (int i = 1 ; i < prices.length ; i++) {
            if (prices[i] > minSoFar) {
                maxProfit = Math.max(maxProfit, prices[i] - minSoFar);
            } else {
                minSoFar = Math.min(minSoFar, prices[i]);
            }
        }
        return maxProfit == Integer.MIN_VALUE ? 0 : maxProfit;
    }

    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int result = new BestTimeToBuyAndSaleStock().maxProfit(arr);
        System.out.println(result);
    }
}
