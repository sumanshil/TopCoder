package com.leetcode;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/single-number-ii/
public class SingleNumber2 {

    public int singleNumber(int[] nums) {
        /*
        Set<Integer> oneCounts = new HashSet<>();
        Set<Integer> twoCounts = new HashSet<>();
        for (int i = 0 ; i < nums.length ; i++) {
            if (!oneCounts.contains(nums[i]) && !twoCounts.contains(nums[i])) {
                oneCounts.add(nums[i]);
            } else if (oneCounts.contains(nums[i])) {
                oneCounts.remove(nums[i]);
                twoCounts.add(nums[i]);
            }
        }
        return oneCounts.iterator().next();
         */
        int once=0,twice=0;

        for(int num:nums)
        {
            once=~twice & (once^num);
            twice=~once & (twice^num);
        }
        return once;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,3,2};
        new SingleNumber2().singleNumber(arr);
        System.out.println(~2);
    }
}
