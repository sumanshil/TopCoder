package com.leetcode.array.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-closest/description/
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minTotalDiff = Integer.MAX_VALUE;
        int resultSum = 0;
        for (int i = 0 ; i < nums.length-2; i++) {
            int lastIndex = nums.length-1;
            int firstIndex = i+1;
            while (firstIndex < lastIndex) {
                int currentSum  = nums[i]+ nums[firstIndex] + nums[lastIndex];
                int currentDiff;
                if ((currentSum > 0 && target <0)|| (currentSum < 0 && target >0)) {
                    currentDiff = Math.abs(currentSum - target);
                } else {
                    currentDiff = (target - currentSum);
                }
                if (Math.abs(currentDiff) < minTotalDiff) {
                    minTotalDiff = Math.abs(currentDiff);
                    resultSum = currentSum;
                }

                if (currentSum < target) {
                    firstIndex++;
                } else if (currentSum > target) {
                    lastIndex--;
                } else {
                    firstIndex++;
                    lastIndex--;
                }
            }
        }
        return resultSum;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 8, 16, 32, 64, 128};
        int r = new ThreeSumClosest().threeSumClosest(arr, 82);
        System.out.println(r);
    }
}
