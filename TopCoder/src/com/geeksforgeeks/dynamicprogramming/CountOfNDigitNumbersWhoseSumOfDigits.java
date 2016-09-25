package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/8/2016.
 */
//http://www.geeksforgeeks.org/count-of-n-digit-numbers-whose-sum-of-digits-equals-to-given-sum/
public class CountOfNDigitNumbersWhoseSumOfDigits {

    private int[][] dp = null;
    public void find(int n, int sum){
        int ans = 0;
        int digit = 0;
        int currentSum = 0;
        dp = new int[n][1000];
        for ( int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < 1000 ; j++){
                dp[i][j] = -1;
            }
        }
        for ( int i = 1 ; i < 9 ; i++){
            ans += recursive(digit+1, currentSum+i, sum, n );
        }

        System.out.println(ans);
    }

    private int recursive(int digits, int currentSum, int sum, int n) {
        if (digits == n ){
            if (currentSum == sum ){
                return 1;
            }
            return 0;
        }
        if (dp[digits][currentSum] != -1){
            return dp[digits][currentSum];
        }
        int ans = 0;
        for ( int i = 0 ; i <= 9 ; i++){
            ans += recursive(digits+1, currentSum + i, sum, n);
        }
        dp[digits][currentSum] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int n = 3;
        int sum = 6;
        new CountOfNDigitNumbersWhoseSumOfDigits().find(n, sum);
    }
}
