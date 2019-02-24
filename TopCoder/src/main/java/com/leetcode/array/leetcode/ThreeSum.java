package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);

        for ( int i = 0 ; i < nums.length-2 ; i++) {
            if ( i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int target = 0 - nums[i];

            int low = i + 1;
            int high = nums.length - 1;

            while ( low < high) {
                if ( nums[low] + nums[high] == target) {
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[low]);
                    result.add(nums[high]);
                    list.add(result);
                    while (low + 1 < nums.length && nums[low] == nums[low + 1]) {low = low + 1;}
                    while (high - 1 >= 0 && nums[high] == nums[high-1]) {high = high - 1;}
                    low = low + 1;
                    high = high - 1;
                } else if (nums[low] + nums[high] > target) {
                    high = high - 1;
                } else {
                    low = low + 1;
                }
            }

        }
        return list;
    }


    public static void main(String[] args) {
        int nums[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new ThreeSum().threeSum(nums);
        for (List<Integer> list : res) {
            for (Integer iii : list) {
                System.out.println(iii);
            }
            System.out.println("=======");
        }
    }

}
