package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 6/3/2016.
 */
//http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
public class MaximumProfitBySellingStockMaxtwoTimes {
    public void find(int[] arr){
        int[] lMax = new int[arr.length];
        int[] rMax = new int[arr.length];
        int min = arr[0];
        int max = Integer.MIN_VALUE;
        lMax[0] = 0;

        for ( int i = 1 ; i < arr.length ; i++) {
            if (arr[i] < min){
                min = arr[i];
            } else {
                lMax[i] = arr[i] - min;
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
        }
        min = arr[arr.length-1];
        max = arr[arr.length-1];
        int minIndex = arr.length-1;
        int maxIndex = arr.length-1;
        for ( int i = arr.length-2 ; i >= 0 ; i--) {
            if (minIndex < maxIndex) {
                rMax[i] = max - min;
            }
            if (arr[i] < min){
                min = arr[i];
                minIndex = i;
            } else if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for ( int i = 0 ; i < arr.length ; i++) {
            if (lMax[i]+rMax[i] > maxSum){
                maxSum = lMax[i]+rMax[i];
            }
        }
        System.out.println(maxSum);
    }

    public static void main(String[] args) {
        int[] arr = {90, 80, 70, 60, 50};
        new MaximumProfitBySellingStockMaxtwoTimes().find(arr);
    }
}
