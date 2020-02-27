package com.leetcode;

//https://leetcode.com/problems/house-robber-ii/
public class HouseRobbers2 {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }


        int sumExcluding1 = findMax(nums, 1, nums.length - 1);
        int sumIncluding1 = findMax(nums, 0, nums.length - 2);

        return Math.max(sumExcluding1, sumIncluding1);

    }

    private int findMax(int[] nums, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return nums[endIndex];
        }
        if (startIndex + 1 <= endIndex && startIndex + 1 == endIndex) {
            return Math.max(nums[startIndex], nums[endIndex]);
        }

        if (startIndex + 2 <= endIndex && startIndex + 2 == endIndex) {
            return Math.max(nums[startIndex] + nums[startIndex + 2], nums[startIndex + 1]);
        }

        int[] dp = new int[(endIndex - startIndex) + 1];
        dp[0] = nums[startIndex];
        dp[1] = nums[startIndex + 1];
        dp[2] = nums[startIndex] + nums[startIndex + 2];

        for (int i = startIndex + 3 ; i <= endIndex ; i++) {
            int dpIndex = i- startIndex;
            dp[dpIndex] = nums[i] + Math.max(dp[dpIndex - 2], dp[dpIndex-3]);
        }
        return Math.max(dp[dp.length-1], dp[dp.length - 2]);
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 2};
        int res = new HouseRobbers2().rob(arr);
        System.out.println(res);
    }
}
