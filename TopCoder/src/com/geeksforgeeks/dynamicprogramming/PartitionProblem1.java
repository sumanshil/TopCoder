package com.geeksforgeeks.dynamicprogramming;

import java.util.stream.IntStream;

//http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
public class PartitionProblem1 {

    public void find(int[] arr) {
        int sum = IntStream.of(arr).sum();
        int halfSum = sum / 2;
        //boolean result = recursive(arr.length, halfSum, arr);
        boolean result = dp(arr, halfSum);
        System.out.println(result);
    }

    private boolean recursive(int index, int targetSum, int arr[]) {
        if (index == 0 && targetSum > 0) {
            return false;
        }
        if (targetSum == 0) {
            return true;
        }

        if (arr[index-1] > targetSum) {
            return recursive(index-1, targetSum, arr);
        }

        return recursive(index-1, targetSum-arr[index-1], arr)
                || recursive(index-1, targetSum, arr);

    }

    private boolean dp(int[] arr, int taretSum) {
        boolean dp[][] = new boolean[arr.length+1][taretSum+1];
        dp[0][0] = true;

        for ( int i = 1 ; i <= arr.length ; i++ ) {
            dp[i][0] = false;
        }

        for ( int i = 1 ; i <= taretSum ; i++) {
            dp[0][i] = false;
        }

        for ( int i = 1 ; i <= arr.length ; i++) {
            for ( int j = 1; j <= taretSum ; j++) {
                if (arr[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }
            }
        }

        return dp[arr.length][taretSum];

    }

    public static void main(String[] args) {
        int arr[] = {1, 5, 11, 5};
        new PartitionProblem1().find(arr);

    }
}
