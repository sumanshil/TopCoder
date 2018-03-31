package com.geeksforgeeks.array;

//https://www.geeksforgeeks.org/number-subarrays-given-product/
public class NumberOfSubArraysWithGivenProduct {

    public void find (int arr[], int k) {
        int start = 0;
        int end = 0;
        int product = 1;
        int result = 0;
        while (end < arr.length) {
            product *= arr[end];
            if (product == k) {
                result++;
                end++;
            } else if (product > k) {
                while (start < end) {
                    product /= arr[start];
                    if (product == k) {
                        result++;
                    } else {
                        break;
                    }
                    start++;
                }
            }
            else {
                end++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr= {2, 1, 1, 1, 4, 5};
        new NumberOfSubArraysWithGivenProduct().find(arr, 4);
    }
}
