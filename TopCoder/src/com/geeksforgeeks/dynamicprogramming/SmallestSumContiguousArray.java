package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/smallest-sum-contiguous-subarray/
public class SmallestSumContiguousArray {

    public void find (int[] arr){
        int[] minSumArray = new int[arr.length];
        for ( int i = 0 ; i < arr.length ; i++){
            minSumArray[i] = arr[i];
        }

        for (int i = 1 ; i < arr.length ; i++){
            minSumArray[i] = Math.min(minSumArray[i-1]+arr[i], arr[i]);
        }

        int minSum = Integer.MAX_VALUE;
        for ( int i = 0 ; i < minSumArray.length ; i++){
            if (minSumArray[i] < minSum){
                minSum = minSumArray[i];
            }
        }
        System.out.println(minSum);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -10, -20,50, 60};
        new SmallestSumContiguousArray().find(arr);
    }
}
