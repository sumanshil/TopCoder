package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/sort-colors/
public class SortColors {
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            while (nums[start] == 0 && start < nums.length) {
                start++;
            }

            while (nums[end] == 1 && end >= 0)  {
                end--;
            }

            if (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,1,0};
        new SortColors().sortColors(nums);

        Arrays.stream(nums).forEach(System.out::println);
    }

}
