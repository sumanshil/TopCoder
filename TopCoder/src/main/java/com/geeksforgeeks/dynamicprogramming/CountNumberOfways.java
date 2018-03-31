package com.geeksforgeeks.dynamicprogramming;

//https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
public class CountNumberOfways {

    public void find(int n) {
        int result = recursive(n);
        System.out.println(result);
    }

    private int recursive(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return recursive(n-1) + recursive(n-2) + recursive(n-3) ;
    }


    private void dp(int n ) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3 ; i <=n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        System.out.println(dp[dp.length-1]);
    }

    public static void main(String[] args) {
        new CountNumberOfways().dp(4);
    }
}
