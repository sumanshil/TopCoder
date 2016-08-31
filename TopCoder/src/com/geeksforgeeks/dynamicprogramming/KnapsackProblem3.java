package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/28/16.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
public class KnapsackProblem3 {

    public void knapsack(int W, int[] w, int[] val) {
        int max = recursive(w, val, W, w.length-1, 0);
        System.out.println(max);
    }

    private int recursive(int[] w, int[] val, int currentWeight, int index, int currentVal) {
        if (index < 0){
            return currentVal;
        }
        if (w[index] > currentWeight){
            return recursive(w, val, currentWeight, index-1, currentVal);
        }
        return Math.max(recursive(w, val, currentWeight-w[index], index-1, currentVal+val[index]),
                recursive(w, val, currentWeight, index-1, currentVal));
    }

    private void dp(int W, int[] w, int[] val){
        int row = W+1;
        int col = w.length+1;
        int[][] matrix = new int[row][col];
        for ( int i = 0 ; i < col; i ++){
            matrix[0][i] = 0;
        }

        for ( int i = 0 ; i < row ; i++) {
            matrix[i][0] = 0;
        }

        for (int i =1 ; i < row ; i++) {
            for ( int j = 1 ; j < col; j++) {
                if (w[j-1] > i){
                    matrix[i][j] = matrix[i][j-1];
                } else {
                    matrix[i][j] = Math.max(matrix[i][j-1],val[j-1] + matrix[i-w[j-1]][j-1]);
                }
            }
        }
        System.out.println(matrix[row-1][col-1]);
    }


    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int W = 50;
        new KnapsackProblem3().dp(W, weight,val);
    }
}
