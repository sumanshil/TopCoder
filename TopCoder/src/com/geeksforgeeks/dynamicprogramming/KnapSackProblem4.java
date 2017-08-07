package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
public class KnapSackProblem4 {

    public int findMaxValue(int[] value, int[] weight, int maxWeight) {
        int result = findRecursive(value, weight, maxWeight, value.length-1 );
        return result;
    }

    private int findRecursive(int[] value, int[] weight, int maxWeight, int n) {
        if (n == 0 || maxWeight == 0) {
            return 0;
        }

        if (weight[n-1] > maxWeight){
            return findRecursive(value, weight, maxWeight, n-1);
        }

        int resultWithThisElement = findRecursive(value, weight, maxWeight - weight[n], n-1)+value[n];
        int resultWithoutThisElement = findRecursive(value, weight, maxWeight, n-1);
        return Math.max(resultWithoutThisElement, resultWithThisElement);
    }


    public void findDP(int[] value, int[] weight, int maxWeight, int n){
        int dp[][] = new int[weight.length+1][maxWeight+1];
        int rowLength = value.length+1;
        int colLength = maxWeight+1;
        for ( int i = 0 ; i < rowLength ; i++) {
            dp[i][0] = 0;
        }

        for ( int i = 0 ; i < colLength ; i++) {
            dp[0][i] = 0;
        }

        for ( int i = 1 ; i < rowLength ; i++){
            for ( int j = 1 ; j < colLength ; j++) {
                if (weight[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int result1 = dp[i-1][j-weight[i-1]]+value[i-1];
                    int result2 = dp[i-1][j-1];
                    dp[i][j] = Math.max(result1, result2);
                }
            }
        }
        System.out.println(dp[rowLength-1][colLength-1]);
    }


    public static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        new KnapSackProblem4().findDP(val,wt, W,val.length);
       // System.out.println(result);
    }
}
