package com.leetcode.array.leetcode;

import java.util.Arrays;

public class ThreeSumClosest1 {

    public int threeSumClosest(int[] nums, int target) {
        int minGap = Integer.MAX_VALUE;
        int sumResult = 0;

        Arrays.sort(nums);

        for ( int i = 0 ; i < nums.length - 2; i++) {

            int low = i + 1;
            int high = nums.length - 1;
            boolean found = false;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];

                int diff = Math.abs(target - sum);
                if (diff == 0) {
                    found = true;
                    sumResult = sum;
                    break;
                } else if (diff < minGap) {
                    minGap = diff;
                    sumResult = sum;
                }

                if (sum < target) {
                    low ++;
                } else {
                    high = high - 1;
                }
            }
            if (found) {
                break;
            }
        }
        return sumResult;
    }

    public static void main(String[] args) {
        int[] arr = {4, -1, -4, 4};
        int res = new ThreeSumClosest1().threeSumClosest(arr, -1);
        System.out.println(res);
    }
}
