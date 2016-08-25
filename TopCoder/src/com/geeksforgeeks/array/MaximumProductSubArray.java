package com.geeksforgeeks.array;

/**
 * Created by sshil on 8/18/2016.
 */
//http://www.geeksforgeeks.org/maximum-product-subarray/
public class MaximumProductSubArray {

    public void find(int[] arr) {
        int maxEndingHere = 1; // for tracking positive product
        int minEndingHere = 1; // for tracking negative product
        int maxSoFar = 1;

        for ( int i = 0 ; i < arr.length ; i++) {
            if ( arr[i] > 0 ) {
                maxEndingHere = maxEndingHere * arr[i];
                minEndingHere = Math.min(minEndingHere * arr[i], 1);
            }

            if (arr[i] == 0){
                maxEndingHere = 1;
                minEndingHere = 1;
            }
            if (arr[i] < 0){
                int temp = maxEndingHere;
                maxEndingHere = Math.max(minEndingHere*arr[i], 1);
                minEndingHere = temp*arr[i];
            }
            if (maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
            }
        }
        System.out.println(maxSoFar);
    }


    public static void main(String[] args) {
        int[] arr = {-2, -3, 0, -2, -40};
        new MaximumProductSubArray().find(arr);
    }
}
