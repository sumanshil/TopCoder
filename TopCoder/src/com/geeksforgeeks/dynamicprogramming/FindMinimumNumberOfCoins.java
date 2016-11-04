package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/20/16.
 */
//http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
public class FindMinimumNumberOfCoins {

    public void find(int n, int[] arr) {
        int result = findMinRecursive(n, arr, 0);
        System.out.println(result);
    }

    private int findMinRecursive(int n, int[] arr, int index) {
        if (n == 0) {
            return 0;
        }
        if (index >= arr.length) {
            return 1000;
        }

        if ( arr[index] <= n) {
            return Math.min(findMinRecursive(n-arr[index], arr, index)+1,
                            findMinRecursive(n, arr, index+1));
        } else {
            return findMinRecursive(n, arr, index+1);
        }
    }

    private int findMinRecursive(int n, int[] arr){
        if ( n == 0 ) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] <= n ){
                result = Math.min(findMinRecursive(n-arr[i], arr)+1, result );
            }
        }
        return result;
    }


    private void dp(int n , int[] arr) {
        int[][] dp = new int[n+1][arr.length+1];
        int rowLength = n+1;
        int colLength = arr.length+1;
        for ( int i = 0 ; i < colLength ; i++) {
            dp[0][i] = 0;
        }

        for ( int i = 1 ; i < rowLength ; i++){
            dp[i][0] =Integer.MAX_VALUE;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (arr[j-1] <= i ) {
                    int index = i-arr[j-1];
                    int value1 = dp[index][j];
                    int value2 = dp[i][j-1];
                    if (value1 != Integer.MAX_VALUE)
                        dp[i][j] = Math.min(value1+1, value2);
                    else
                        dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        System.out.println(dp[rowLength-1][colLength-1]);
    }

    public static void main(String[] args) {
        int[] arr = {5,1};
        int v = 11;
        new FindMinimumNumberOfCoins().dp(v, arr);
       // System.out.println(result);
    }
}
