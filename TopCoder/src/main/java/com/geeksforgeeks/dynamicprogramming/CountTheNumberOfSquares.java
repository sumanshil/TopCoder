package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/20/16.
 */
//http://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
public class CountTheNumberOfSquares {

    public void find(int n){
        int res = findRecursive(n);
        System.out.println(res);
    }

    private int findRecursive(int n) {
        if ( n < 3) {
            return n;
        }
        int minSquares = n;
        for ( int x = 1 ; x*x <= n ; x++) {
            minSquares = Math.min(minSquares,1 + findRecursive(n - x*x));
        }
        return minSquares;
    }

    private int findDp(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for ( int i = 3 ; i <= n ; i++) {
            int res = i;
            for ( int x = 1 ; x*x <= i ; x++){
                res = Math.min(res, dp[i-x*x]+1);
            }
            dp[i] = res;
        }
        System.out.println(dp[dp.length-1]);
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        new CountTheNumberOfSquares().findDp(100);
    }
}
