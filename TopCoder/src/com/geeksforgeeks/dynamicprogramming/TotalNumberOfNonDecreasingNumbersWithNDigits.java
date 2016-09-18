package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 9/12/16.
 */
//http://www.geeksforgeeks.org/total-number-of-non-decreasing-numbers-with-n-digits/
public class TotalNumberOfNonDecreasingNumbersWithNDigits {

    public void find( int n ) {
        int result = 0;
        int digit = 0;
        for ( int i = 0 ; i <= 9 ; i++){
            list = new ArrayList<>();
            list.add(i);
            result += recursive(digit+1, i, n);

        }
        System.out.println(result);
    }

    private List<Integer> list = new LinkedList<>();
    private int recursive(int digit, int number, int n) {
        if (digit == n) {
            list.stream().forEach(System.out::print);
            System.out.println();
            return 1;
        }
        int result = 0;
        for ( int i = number ; i <= 9 ; i++){
            list.add(i);
            result += recursive(digit+1, i, n);
            list.remove(list.size()-1);
        }
        return result;
    }

    private int[][] dp = null;

    private void dp(int n){
        dp = new int[n][10];
        for ( int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < 10 ; j++){
                dp[i][j] = -1;
            }
        }

        int result = recursive1(0, 0, n);
        System.out.println(result);

    }

    private int recursive1(int digit, int number, int n) {
        if (digit == n ){
            return 1;
        }
        if (dp[digit][number] != -1){
            return dp[digit][number];
        }
        int result = 0;
        for ( int i = number ; i <= 9 ; i++) {
            result += recursive1(digit+1, i, n);
        }
        dp[digit][number] = result;
        return result;
    }


    public void dp1(int n) {
        int[][]  dp = new int[n+1][10];

        for (int i = 0 ; i <= 9 ; i++){
            dp[1][i] = 1;
        }

        for ( int i = 0 ; i <= 9 ; i++) {
            for (int j = 2 ; j <= n ; j++) {
                for ( int k = 0; k <= i ; k++) {
                    dp[j][i] += dp[j-1][k];
                }
            }
        }

        int result = 0;

        for ( int i = 0 ; i <= 9 ; i++){
            result += dp[n][i];
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int n = 1;
        new TotalNumberOfNonDecreasingNumbersWithNDigits().dp1(3);
    }
}
