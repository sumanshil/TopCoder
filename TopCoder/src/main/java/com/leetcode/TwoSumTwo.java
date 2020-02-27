package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSumTwo {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < numbers.length ; i++) {
            map.put(numbers[i], i + 1);
        }

        for (int i = 0 ; i < numbers.length ; i++) {
            if (numbers[i] < target && map.containsKey(target - numbers[i])) {
                return new int[] { i + 1, map.get(target - numbers[i])};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int target = 9;
        int[] result = new TwoSumTwo().twoSum(arr, target);
        //for (int i = 0 ; i < result.length ; i++) {
        //    System.out.println(result[i]);
       // }

        System.out.println(702 /26);
        System.out.println(702 %26);

        System.out.println(26 /27);
        System.out.println(26 %27);

    }
}
