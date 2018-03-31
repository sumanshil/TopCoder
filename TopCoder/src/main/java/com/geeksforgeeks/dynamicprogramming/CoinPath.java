package com.geeksforgeeks.dynamicprogramming;

public class CoinPath {

    public static void find(int arr[], int maxJump){
        int result = recursive(0, arr, maxJump, 0);
        System.out.println(result);
    }

    private static int recursive(int index, int[] arr, int maxJump, int costSoFar) {
        if (index > arr.length) {
            return Integer.MAX_VALUE;
        }
        if (arr[index] == -1){
            return Integer.MAX_VALUE;
        }
        if (index == arr.length-1) {
            return arr[index];
        }

        int minCost = Integer.MAX_VALUE;

        for ( int i = index+1 ; i <= index + maxJump ; i++){
            int cost = recursive(i,arr,maxJump, costSoFar);
            if (cost != Integer.MAX_VALUE) {
                if (cost + arr[index] < minCost) {
                    minCost = cost + arr[index];
                }
            }
        }
        return minCost;
    }


    public static void dp(int[] arr, int maxJumps) {
        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        for ( int i = 1 ; i < arr.length ; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != -1) {
                for (int j = i + 1; j <= i + maxJumps && j < arr.length; j++) {
                    if (arr[j] != -1) {
                        dp[j] = Math.min(dp[j], arr[j] + dp[i]);
                    }
                }
            }
        }

        System.out.println(dp[dp.length-1]);

    }

    public static void main(String[] args) {
        int[] arr= {1, 2, 4, -1, 2};
        int maxJump = 2;
        CoinPath.dp(arr, maxJump);
    }
}
