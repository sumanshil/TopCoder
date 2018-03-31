package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/13/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
public class KnapSackProblem2 {
    public void find(int[] val, int[] weight, int W){
        int result = findRecursive(val, weight, W, val.length);
        System.out.println(result);
    }

    private int findRecursive(int[] val, int[] weight, int w, int index) {
        if (w == 0 || index == 0 ){
            return 0;
        }

        if (weight[index-1] > w){
            return findRecursive(val, weight, w, index-1);
        }
        return Math.max(val[index-1]+findRecursive(val, weight, w-weight[index-1], index-1), findRecursive(val, weight, w, index-1));
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int W = 50;
        new KnapSackProblem2().find(val, weight, W);
    }
}
