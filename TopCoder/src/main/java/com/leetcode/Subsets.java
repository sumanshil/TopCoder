package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/subsets/
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        /*
        result.add(new ArrayList<>());
        for (int subsetSize = 1 ; subsetSize <= nums.length ; subsetSize++) {
            List<Integer> currentList = new LinkedList<>();
            boolean[] taken = new boolean[nums.length];
            recursive(0, currentList, result, taken, nums, subsetSize);
        }

         */
        List<List<Integer>> result = new LinkedList<>();
        result.add(new LinkedList<>());
        for (int k = 1 ; k < Math.pow(2, nums.length); k++){
            List<Integer> list = new ArrayList<>();
            int mask = 1;
            int index = 0;
            int num = k;
            while (num > 0) {
                if ((num & mask) > 0) {
                    list.add(nums[index]);
                }
                index++;
                num = num >> 1;
            }
            result.add(list);
        }
        return result;

    }

    private void recursive(int index, List<Integer> currentList,
                           List<List<Integer>> result, boolean[] taken,
                           int[] nums, int subsetSize) {
        if ( index > nums.length || currentList.size() > subsetSize ) {
            return;
        }

        if (currentList.size() == subsetSize) {
            result.add(new LinkedList<>(currentList));
            return;
        }

        for (int i = index ; i < nums.length ; i++) {
            if (taken[i]) {
                return;
            }
            taken[i] = true;
            currentList.add(nums[i]);
            recursive(i+1, currentList, result, taken, nums, subsetSize);
            taken[i] = false;
            currentList.remove(currentList.size()-1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new Subsets().subsets(arr);
        for (List<Integer> res : result) {
            System.out.println(res);
        }
    }
}
