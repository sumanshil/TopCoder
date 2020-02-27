package com.leetcode.array.leetcode;

//https://leetcode.com/problems/jump-game/
public class JumpGame1 {

    public boolean canJump(int[] nums) {
        int currentMax = 0;
        int currentEnd = 0;

        for (int i = 0 ; i < nums.length - 1; i++) {
            currentMax = Math.max(currentMax, i + nums[i]);
            if (i == currentEnd) {
                currentEnd = currentMax;
            }
        }
        return currentEnd >= nums.length - 1;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,1,4};
        boolean result = new JumpGame1().canJump(arr);
        System.out.println(result);
    }

}
