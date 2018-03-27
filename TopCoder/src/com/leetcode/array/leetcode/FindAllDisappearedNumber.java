package com.leetcode.array.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
public class FindAllDisappearedNumber {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new LinkedList<>();
        for ( int i = 0 ; i < nums.length ; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = 0 - nums[index];
            }
        }

        for ( int i = 0 ; i < nums.length ; i++) {
            if (nums[i] > 0) {
                result.add(i+1);
            }
        }
        return result;
    }
    /*
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<Integer> result = new LinkedList<>();
        for (int i = 0 ; i < nums.length-1 ; i++) {
            int diff = nums[i + 1] - nums[i];
            if (diff > 1) {
                for (int j = nums[i] +1 ; j < nums[i+1] ; j++) {
                    result.add(j);
                }
            }
        }
        if (nums[nums.length-1] < nums.length) {
            for ( int j = nums[nums.length-1] + 1; j < nums.length ; j++) {
                result.add(j);
            }
        }
        return result;
    }
    */

    public static void main(String[] args) {
        int nums[] = {4,3,2,7,8,2,3,1};
        List<Integer> result = new FindAllDisappearedNumber()
                .findDisappearedNumbers(nums);
        System.out.println(result);
    }

}
