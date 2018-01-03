package com.geeksforgeeks.dynamicprogramming;

//https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
public class SubnetSumProblem {

    public void find (int arr[], int sum) {
        boolean dp[][] = new boolean[sum+1][arr.length+1];
        int rowLength = dp.length;
        int colLength = dp[0].length;
        for (int col = 0 ; col < colLength ; col++ ) {
            dp[0][col] = true;
        }

        for (int row = 1 ;  row < rowLength ; row++) {
            dp[row][0] = false;
        }

        for ( int row = 1 ; row < rowLength ; row ++) {
            for ( int col = 1 ; col < colLength ; col++) {
                if (arr[col-1] > row) {
                    dp[row][col] = dp[row][col-1];
                } else {
                    dp[row][col] = dp[row][col-1] || dp[row - arr[col-1]][col-1];
                }
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 1;
        new SubnetSumProblem().find(arr, sum);
    }

}
