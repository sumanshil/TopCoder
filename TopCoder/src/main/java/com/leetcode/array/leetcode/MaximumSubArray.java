package com.leetcode.array.leetcode;

//https://leetcode.com/problems/maximum-subarray/
public class MaximumSubArray {

    public int maxSubArray(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length ; i++) {
            currentSum += nums[i];
            if (currentSum < 0) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum = 0;
            } else {
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, -2, 3};
        int res = new MaximumSubArray().maxSubArray(nums);
        System.out.println(res);
    }
}
