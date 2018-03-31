package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
public class CuttingRod2 {

    public void find(int n, int[] price) {
        int[] dp = new int[price.length+1];
        dp[0] = 0;
        for ( int i = 1 ; i < dp.length ; i++) {
            dp[i] = price[i-1];
        }
        for ( int i = 1 ; i < dp.length; i++) {
            for ( int j = 0 ; j <= i-1; j++) {
                dp[i] = Math.max(dp[i], price[j] + dp[(i-1)-j]);
            }
        }
        System.out.println(dp[dp.length-1]);
    }

    public static void main(String[] args) {
        int n = 8;
        int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
        new CuttingRod2().find(n, arr);
    }
}
