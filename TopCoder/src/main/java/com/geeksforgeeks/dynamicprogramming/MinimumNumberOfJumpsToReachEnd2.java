package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;

//https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
public class MinimumNumberOfJumpsToReachEnd2 {

    public void find (int arr[]) {
        int dp[] = new int[arr.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for ( int i = 0 ; i < arr.length-1 ; i++) {
            int maxIndex = i + arr[i];
            for (int j = i+1 ; j <= maxIndex && j <dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        System.out.println(dp[dp.length-1]);
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        new MinimumNumberOfJumpsToReachEnd2().find(arr);
    }
}
