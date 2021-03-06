package com.leetcode.array.leetcode;

//https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) nums[i] = nums.length + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs <= nums.length) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) return i + 1;
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int arr[] = {-1};
        int result = new FirstMissingPositive().firstMissingPositive(arr);
        if (result == -1) {
            System.out.println(arr.length);
        } else {
            System.out.println(result);
        }
    }
}
