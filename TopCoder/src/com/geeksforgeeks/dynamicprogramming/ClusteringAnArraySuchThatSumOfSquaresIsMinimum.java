package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/13/17.
 */
//http://www.geeksforgeeks.org/clusteringpartitioning-an-array-such-that-sum-of-square-differences-is-minimum/
public class ClusteringAnArraySuchThatSumOfSquaresIsMinimum {

    int result = Integer.MAX_VALUE;
    
    public void find(int[] arr, int k) {
        findRecursive(-1, arr, 0, k, 0);
        System.out.println(result);
    }

    private void findRecursive(int index, int[] arr, int par, int k, int sum) {
        if (par > k){
            return;
        }

        if (par == k && index == arr.length -1) {
            if (sum < result ){
                result = sum;
            }
            return;
        }

        for ( int j = index+1 ; j < arr.length ; j++ ){
            findRecursive(j, arr, par+1, k, sum +
                    (arr[j]-arr[index+1])*(arr[j]-arr[index+1]));
        }
    }

    private void dp(int[] arr, int k){
        int arrLength = arr.length;

        int[][] dp = new int[arrLength+1][k+1];

        for ( int i = 0 ; i <= arrLength ; i++) {
            for ( int j = 0 ; j <= k ; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;

        for ( int i = 1 ; i <= arrLength ; i++) {
            for (int j = 1 ; j <= k ; j++) {
                for ( int m = i-1 ; m >= 0 ; m--) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[m][j-1]+(arr[i-1]- arr[m])*(arr[i-1]*arr[m]));
                }
            }
        }







    }
    // dp[i][j] = min sum till index i with partition j
    // dp[i][j] = min(dp[i][j],
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 10};
        int k = 2;
        new ClusteringAnArraySuchThatSumOfSquaresIsMinimum().find(arr, k);
    }
}
