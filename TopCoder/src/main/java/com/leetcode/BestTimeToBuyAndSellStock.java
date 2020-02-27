package com.leetcode;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minSoFar = prices[0];
        int maxSoFar = minSoFar;
        int result = 0;
        for (int i = 1 ; i < prices.length ; i++) {
            if (prices[i] < maxSoFar) {
                result += (maxSoFar - minSoFar);
                minSoFar = prices[i];
                maxSoFar = minSoFar;
            } else if (prices[i] > maxSoFar) {
                maxSoFar = prices[i];
            }
        }
        if (minSoFar != maxSoFar) {
            result += maxSoFar - minSoFar;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        int result = new BestTimeToBuyAndSellStock().maxProfit(arr);
        System.out.println(result);
    }
}
