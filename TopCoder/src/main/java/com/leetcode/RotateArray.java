package com.leetcode;

//https://leetcode.com/problems/rotate-array/
public class RotateArray {

    public void rotate(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        k = k % nums.length;
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    /*
    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int startIndex = nums.length - k;
        int beginIndex = 0;
        int currentStartIndex = startIndex;
        while (currentStartIndex < nums.length) {
            int index = currentStartIndex;
            while (index > beginIndex) {
                int temp = nums[index-1];
                nums[index-1] = nums[index];
                nums[index] = temp;
                index--;
            }
            currentStartIndex++;
            beginIndex++;
        }
        for (int num : nums) {
            System.out.println(num);
        }
    }
    */

    public static void main(String[] args) {
        //[1,2,3,4,5,6,7] and k = 2
        int[] nums = {1,2,3,4,5,6,7};
        int k = 2;
        new RotateArray().rotate(nums, k);
    }
}
