package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/knapsack-problem/
public class KnapSackProblem5 {

    public void find (int val[], int weight[], int W) {
        int result = recursive(val, weight, W, val.length, W);
        System.out.println(result);
    }

    private int recursive(int[] val, int[] weight, int w, int index, int currentWeight) {
        if (index == 0) {
            return 0;
        }
        if (currentWeight == 0){
            return 0;
        }
        if (weight[index-1] > w) {
            return recursive(val, weight, w, index-1, currentWeight);
        } else {
            return Math.max(
                    recursive(val, weight, w, index-1, currentWeight-weight[index-1])+ val[index-1]
            , recursive(val, weight, w, index-1, currentWeight));
        }
    }

    public void dp (int val[], int wt[], int w){
        int rowLength = w+1;
        int colLength = val.length+1;
        int dp[][] = new int[rowLength][colLength];

        for (int currentWeight = 1 ; currentWeight < rowLength ; currentWeight++){
            for (int arrIndex = 1 ; arrIndex < colLength ; arrIndex++) {

                if (wt[arrIndex-1] > currentWeight){
                    dp[currentWeight][arrIndex] = dp[currentWeight][arrIndex-1];
                } else {
                    dp[currentWeight][arrIndex] = Math.max(dp[currentWeight-wt[arrIndex-1]][arrIndex-1]+val[arrIndex-1],
                                                           dp[currentWeight][arrIndex-1]);
                }
            }
        }
        System.out.println(dp[rowLength-1][colLength-1]);
    }



    public static void main(String[] args) {
        int value[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int W = 50;
        new KnapSackProblem5().dp(value, weight, W);
    }
}
