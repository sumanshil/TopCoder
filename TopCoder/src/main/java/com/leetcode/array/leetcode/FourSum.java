package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/4sum/description/
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for ( int i = 0 ; i <= nums.length - 4; i++) {
            for (int j = i + 1 ; j <= nums.length-3 ; j++) {
                int low = j + 1;
                int high = nums.length-1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        result.add(Arrays.asList(new Integer[]{nums[i],
                                                               nums[j],
                                                               nums[low],
                                                               nums[high]}));

                        int tempLow = nums[low];
                        low++;
                        while (low < high && nums[low] == tempLow) {
                            low++;
                        }
                        int tempHigh = nums[high];
                        high--;
                        while (high > low && nums[high] == tempHigh) {
                            high--;
                        }
                    } else if (sum > target) {
                        int tempHigh = nums[high];
                        high--;
                        while (high > low && nums[high] == tempHigh) {
                            high--;
                        }
                    } else {
                        int tempLow = nums[low];
                        low++;
                        while (low < high && nums[low] == tempLow) {
                            low++;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        List<List<Integer>> result = new FourSum().fourSum(arr, target);
        System.out.println(result);
    }
}
