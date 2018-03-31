package com.leetcode.array.leetcode;

import java.util.Arrays;

public class ArrayPairSum {

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0 ; i < nums.length ; ) {
            maxSum += nums[i];
            i = i + 2;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int nums[] = {1,4,3,2};
        int result = new ArrayPairSum().arrayPairSum(nums);
        System.out.println(result);
    }
}
