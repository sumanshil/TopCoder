package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-smaller/
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
//        recursive(0, nums, target, 0, 0);
//        return count;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0 ; i < nums.length - 2 ; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            for (int j = low; j < high ; j++) {
                while ( high > low && nums[i] + nums[j] + nums[high] >= target) {
                    high--;
                }
                count += (high - j);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //int[] nums = {1,-1,2,0,3,-2};

        //int[] nums = {0, 0, 0};
        //int[] nums = {-2,1,-1,2};
        int[] nums = {3, 1, 0, -2};
        int res = new ThreeSumSmaller().threeSumSmaller(nums, 4);
        System.out.println(res);
    }
}
