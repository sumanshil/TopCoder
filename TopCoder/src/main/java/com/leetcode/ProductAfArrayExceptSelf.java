package com.leetcode;

//https://leetcode.com/problems/product-of-array-except-self/
public class ProductAfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        /**
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = nums[0];
        for (int i = 1 ; i < nums.length ; i++) {
            left[i] = nums[i] * left[i-1];
        }

        right[right.length-1] = nums[nums.length-1];
        for (int i= nums.length-2 ; i >= 0 ; i--) {
            right[i] = nums[i] * right[i+1];
        }

        int[] output = new int[nums.length];

        output[0] = right[1];
        output[output.length-1] = left[left.length-2];
        for (int i = 1; i < output.length-1 ; i++) {
            output[i] = left[i-1] * right[i+1];
        }
        return output;

         **/

        int[] output = new int[nums.length];
        output[0] = 1;

        int L = nums[0];

        for (int i = 1 ; i < nums.length ; i++) {
            output[i] = L;
            L *= nums[i];
        }

        int R  = nums[nums.length-1];

        for (int i = nums.length - 2 ; i >= 0; i --) {
            output[i] *= R;
            R *= nums[i];
        }
        return output;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int[] result = new ProductAfArrayExceptSelf().productExceptSelf(arr);

        for (int i : result) {
            System.out.println(i);
        }
    }

}
