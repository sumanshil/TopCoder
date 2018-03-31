package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
public class LargestSumContiguousArray {

    public void find(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0 ; i < arr.length ; i++) {
            dp[i] = arr[i];
        }

        for ( int i = 1 ; i < arr.length ; i++){
            dp[i] = Math.max(dp[i-1]+arr[i], dp[i]);
        }

        int max = Integer.MIN_VALUE;
        for ( int i = 1 ; i < dp.length ; i++) {
            if (dp[i] > max){
                max = dp[i];
            }
        }
        System.out.println(max);
    }
    public static void main(String[] args) {
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        new LargestSumContiguousArray().find(arr);
    }
}
