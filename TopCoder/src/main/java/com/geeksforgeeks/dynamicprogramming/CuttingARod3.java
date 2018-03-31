package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;

public class CuttingARod3 {

    public void find (int[] arr) {
        int n = arr.length+1;

        int dp[] = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;


        for (int length = 1 ; length < dp.length ; length++) {
            for (int j = 1 ; j <= length ; j++) {
                dp[length] = Math.max(dp[length],dp[length-j]+arr[j-1]);
            }
        }
        System.out.println(dp[dp.length-1]);
    }
    


    public static void main(String[] args) {
        int arr[] = {1, 5, 8, 9, 10, 17, 17, 20};
        new CuttingARod3().find(arr);
    }

}
