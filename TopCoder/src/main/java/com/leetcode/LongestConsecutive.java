package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0 ; i < nums.length ; i++) {
            set.add(nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length ; i++)  {
            if (set.contains(nums[i])) {
                int next = nums[i];
                while (set.contains(next)) {
                    next = next + 1;
                }
                max = Math.max(max, (next) - nums[i]);
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        int res = new LongestConsecutive().longestConsecutive(nums);
        System.out.println(res);
    }
}
