package com.leetcode;

//https://leetcode.com/problems/wiggle-sort/
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        for (int i = 0 ; i <nums.length - 1 ; i++ ) {
            if ( ( i % 2 == 0 && nums[i] < nums[i+1]) || ( i % 2 == 1 && nums[i] > nums[i+1])) {
                int temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        new WiggleSort().wiggleSort(nums);
        for (int i = 0 ; i < nums.length ; i++) {
            System.out.println(nums[i]);
        }
    }
}
