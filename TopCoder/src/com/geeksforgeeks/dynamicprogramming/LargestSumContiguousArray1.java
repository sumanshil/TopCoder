package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
public class LargestSumContiguousArray1 {

    public void find (int[] arr) {
        int maxCurrent = 0;
        int maxSoFar = Integer.MIN_VALUE;

        for ( int i = 0 ; i < arr.length; i++) {
            maxCurrent = maxCurrent + arr[i];
            if (maxCurrent < 0){
                maxSoFar = Math.max(maxSoFar, maxCurrent);
                maxCurrent = 0;
            } else {
              //  maxCurrent = maxCurrent + arr[i];
                maxSoFar = Math.max(maxSoFar, maxCurrent);
            }
        }
        System.out.println(maxSoFar);

    }

    public static void main(String[] args) {
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        new LargestSumContiguousArray1().find(arr);
    }

}
