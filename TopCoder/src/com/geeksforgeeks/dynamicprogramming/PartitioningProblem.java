package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;

/**
 * Created by sshil on 8/7/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
public class PartitioningProblem {

    public void partition(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0){
            return;
        }
        int sum1 = sum / 2 ;
        boolean result = recursive(sum1, 0, arr);
        System.out.println(result);
    }

    private boolean recursive(int sum, int index, int[] arr) {
        if (sum == 0){
            return true;
        }
        if (index == arr.length){
            return false;
        }
        if (sum < arr[index]){
            return recursive(sum, index+1, arr);
        }
        return recursive(sum, index+1, arr) || recursive(sum-arr[index], index+1, arr);
    }

    private boolean dynamicProgramming(int[] arr){
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        boolean[][] matrix = new boolean[sum/2+1][n+1];
        for ( int i = 0 ; i <= n ; i++){
            matrix[0][i] = true;
        }
        for ( int i = 1 ; i <= sum/2 ; i++){
            matrix[i][0] = false;
        }

        for ( int i = 1 ; i <= sum/2 ; i++) {
            for ( int j = 1 ; j<= n ; j++) {
                matrix[i][j] = matrix[i][j-1];
                if ( i < arr[j-1]){
                    matrix[i][j] = matrix[i][j-1] || matrix[i-arr[j-1]][j-1];
                }
            }
        }
        return matrix[sum/2][n];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 8};
        boolean result = new PartitioningProblem().dynamicProgramming(arr);
        System.out.println(result);
    }
}
