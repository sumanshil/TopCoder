package com.leetcode;

//https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubArray {

    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int minProduct = 1;
        int maxProduct = 1;
        int finalMaxProduct = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length ; i++) {
            int currMinProduct = Math.min(nums[i], Math.min(minProduct * nums[i], maxProduct * nums[i]));
            int currMaxProduct = Math.max(nums[i], Math.max(minProduct * nums[i], maxProduct * nums[i]));

            finalMaxProduct = Math.max(finalMaxProduct, currMaxProduct);
            minProduct = currMinProduct;
            maxProduct = currMaxProduct;
        }
        return finalMaxProduct;
    }

    public static void main(String[] args) {
        int[] arr = {-2, -2, -1};
        int result = new MaximumProductSubArray().maxProduct(arr);
        System.out.println(result);
    }
}
