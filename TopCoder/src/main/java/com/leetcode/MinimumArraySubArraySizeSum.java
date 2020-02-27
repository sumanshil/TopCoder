package com.leetcode;

//https://leetcode.com/problems/minimum-size-subarray-sum/
public class MinimumArraySubArraySizeSum {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int startIndex = 0;
        int endIndex = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        while (endIndex < nums.length) {
            currentSum += nums[endIndex];
            if (currentSum >= s) {
                minLength = Math.min(minLength, (endIndex - startIndex) + 1);

                while (currentSum >= s && startIndex <= endIndex) {
                    currentSum -= nums[startIndex++];
                    if (currentSum >= s) {
                        minLength = Math.min(minLength, (endIndex - startIndex) + 1);
                    }
                }
            }
            endIndex++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        int result = new MinimumArraySubArraySizeSum().minSubArrayLen(s, nums);
        System.out.println(result);
    }

}
