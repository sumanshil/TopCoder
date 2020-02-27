package com.leetcode;

//https://leetcode.com/problems/house-robber/
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums.length == 3) {
            return Math.max(nums[2] + nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[2] = nums[2] + nums[0];
        dp[0] = nums[0];
        dp[1] = nums[1];

        for (int i = 3 ; i < nums.length ; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], nums[i] + dp[i-3]);
        }
        return Math.max(dp[dp.length-1], dp[dp.length-2]);
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int res = new HouseRobber().rob(nums);
        System.out.println(res);
    }
}
