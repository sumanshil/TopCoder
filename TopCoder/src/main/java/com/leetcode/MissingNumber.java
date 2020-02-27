package com.leetcode;

//https://leetcode.com/problems/missing-number/
public class MissingNumber {
    public int missingNumber(int[] nums) {
        for (int i = 0 ; i < nums.length ; i++) {
            int number = Math.abs(nums[i]);
            if (number < nums.length) {
                nums[number] = -nums[number];
            }
        }

        for (int i = 0 ; i < nums.length ; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, 1};
        int res = new MissingNumber().missingNumber(arr);
        System.out.println(res);
    }
}
