package com.geeksforgeeks.array;

//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
public class LargestSumContiguousArray {


    public void find (int arr[]) {
        int maxSumSoFar = 0;
        int maxSumEndingHere = 0;
        int start = 0;
        int end = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            maxSumEndingHere = maxSumEndingHere + arr[i];
            if (maxSumEndingHere > maxSumSoFar) {
                maxSumSoFar = maxSumEndingHere;
                end = i;
            }
            if (maxSumEndingHere < 0) {
                maxSumEndingHere = 0;
                start = i;
            }
        }
        System.out.println("max sum "+ maxSumSoFar);
        System.out.println(start +" "+ end);
    }

    public static void main(String[] args) {
        int arr[]= {-2, -3, 4, -1, -2, 1, 5, -3};
        new LargestSumContiguousArray().find(arr);
    }
}
