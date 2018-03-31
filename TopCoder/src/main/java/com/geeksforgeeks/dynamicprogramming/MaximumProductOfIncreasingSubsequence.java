package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;

//http://www.geeksforgeeks.org/maximum-product-increasing-subsequence/
public class MaximumProductOfIncreasingSubsequence {

    public void find (int arr[]) {
        int dp[] = new int[arr.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = arr[0];

        int max = Integer.MIN_VALUE;

        for (int i = 1 ; i < arr.length ; i++) {
            for (int j = 0 ; j < i ; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], arr[i] * dp[j]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        int arr[] = {3, 100, 4, 5, 150, 6};
        new MaximumProductOfIncreasingSubsequence().find(arr);
    }

}
