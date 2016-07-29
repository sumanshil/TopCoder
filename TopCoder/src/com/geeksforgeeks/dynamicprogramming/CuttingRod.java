package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/13/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
public class CuttingRod {

    public void cut(int[] arr, int length) {
        //int result = findMaxWeight(arr, length, arr.length);
        int result = findMaxWeight(arr);
        System.out.println(result);
    }

    private int findMaxWeight(int[] arr){
        int[] val = new int[arr.length+1];
        val[0] = 0;
        int max = Integer.MIN_VALUE;
        for ( int i = 1 ; i < val.length ; i++ ){
            for (int j = 0 ; j < i ; j++){
                max = Math.max(max, arr[j]+val[i-(j+1)]);
            }
            val[i] = max;
        }
        return val[val.length-1];
    }


    private int findMaxWeight(int[] arr,
                              int maxLength,
                              int n) {
        if (maxLength <= 0){
            return  0;
        }
        if (n == 0){
            return Integer.MIN_VALUE;
        }
        if (n > maxLength){
            return findMaxWeight(arr, maxLength, n-1);
        }
        int with = findMaxWeight(arr, maxLength-n,n )+arr[n-1];
        int without =  findMaxWeight(arr, maxLength, n-1);
        return Math.max(with,without);
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
        new CuttingRod().cut(arr, 8);
    }
}
