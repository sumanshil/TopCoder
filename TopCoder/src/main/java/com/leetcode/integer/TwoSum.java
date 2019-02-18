package com.leetcode.integer;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0 ; i < nums.length ; i++) {
            int number1 = nums[i];
            int number2 = target - nums[i];
            if (map.containsKey(number2)) {
                return new int[]{ map.get(number2), i};
            }
            map.put(number1, i);
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int number = 9;
        int result[] = new TwoSum().twoSum(nums, number);
        for ( int i : result) {
            System.out.println(i);
        }
    }
}
