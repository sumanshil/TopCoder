package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums.length > 0) {
            List<Integer> result = new ArrayList<>();
            for (int n : nums) {
                result.add(n);
            }
            permute(results, result, 0, nums);
        }
        return results;
    }

    private void permute(List<List<Integer>> results, List<Integer> result, int index, int[] nums) {
        if (index == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }

        for (int i = index ; i < nums.length ; i++) {
            Collections.swap(result, i, index);
            permute(results, result, index + 1, nums);
            Collections.swap(result, index, i);
        }
    }


    /*
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] taken = new boolean[nums.length];
        int[] tempArray = new int[nums.length];
        Arrays.fill(tempArray, Integer.MIN_VALUE);
        permuteRecursive(results, current, nums, 0, taken, tempArray );
        return results;
    }

    private void permuteRecursive(List<List<Integer>> results, List<Integer> current, int[] nums, int index,
                                  boolean[] taken, int[] tempArray) {
        if (full(tempArray)) {
            List<Integer> resultList = new LinkedList<>();
            for (int i = 0 ; i < tempArray.length ; i++) {
                resultList.add(tempArray[i]);
            }
            results.add(resultList);
            return;
        }
        for (int i = index ; i < nums.length ; i++) {
            if (!taken[i]) {
                for (int j = 0 ; j < tempArray.length ; j++) {
                    if (tempArray[j] == Integer.MIN_VALUE) {
                        tempArray[j] = nums[i];
                        taken[i] = true;
                        permuteRecursive(results, current, nums, i+1, taken, tempArray);
                        tempArray[j] = Integer.MIN_VALUE;
                        taken[i] = false;
                    }
                }

            }
        }

    }

    private boolean full(int[] tempArray) {

        for (int i = 0 ; i < tempArray.length ; i++) {
            if (tempArray[i] == Integer.MIN_VALUE) {
                return false;
            }
        }
        return true;
    }

     */

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> results = new Permutations().permute(arr);
        System.out.println(results);
    }
}
