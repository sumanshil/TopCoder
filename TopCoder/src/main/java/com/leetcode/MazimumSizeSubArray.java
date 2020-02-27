package com.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
public class MazimumSizeSubArray {

    public int maxSubArrayLen(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];

        for (int i = 1 ; i < prefix.length ; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int i = 0 ; i < prefix.length ; i++ ) {
            if (map.containsKey(prefix[i] - k)) {
                max = Math.max(max, i - map.get(prefix[i] - k));
            }

            if (!map.containsKey(prefix[i])) {
                map.put(prefix[i], i);
            }
        }

        return max;

    }


    public static void main(String[] args) {
        int[] arr = {-2, -1, 2, 1};
        int res = new MazimumSizeSubArray().maxSubArrayLen(arr, 3);
        System.out.println(res);
    }
}
