package com.geeksforgeeks.dynamicprogramming;


//https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
public class CountNumberOfWaysToReachAGivenScoreInAGame {

    public void find (int arr[], int sum) {
        int dp[][] = new int[sum+1][arr.length+1];
        int rowLength = dp.length;
        int colLength = dp[0].length;
        for (int i = 0 ; i < colLength ;i++) {
            dp[0][i] = 1;
        }


        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (arr[j-1] > i) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1] + dp[i-arr[j-1]][j];
                }
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);

    }
    public static void main(String[] args) {
        int arr[] = {3, 5, 10};
        int sum = 20;
        new CountNumberOfWaysToReachAGivenScoreInAGame().find(arr, sum);
    }

}
