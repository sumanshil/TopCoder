package com.geeksforgeeks.array;

/**
 * Created by sshil on 7/28/2016.
 */
// http://www.geeksforgeeks.org/largest-product-subarray-size-k/
public class LargestSubArrayOfSizeK {

    public void find(int[] arr, int k){
        int startIndex = 0;
        int currentIndex = 0;
        int currentProduct = 1;
        int maxProduct = Integer.MIN_VALUE;
        for ( int i = 0 ; i < k ; i++) {
            currentProduct = currentProduct * arr[i];
            currentIndex = i;
        }
        if (currentProduct > maxProduct){
            maxProduct = currentProduct;
        }
        currentIndex++;
        while(currentIndex < arr.length){
            currentProduct = currentProduct/arr[startIndex++];
            currentProduct = currentProduct * arr[currentIndex++];
            if (currentProduct > maxProduct){
                maxProduct = currentProduct;
            }
        }
        System.out.println(maxProduct);
    }

    public static void main(String[] args) {
        int[] arr= {1, 5, 9, 8, 2, 4, 1, 8, 1, 2};
        new LargestSubArrayOfSizeK().find(arr, 4);
    }
}
