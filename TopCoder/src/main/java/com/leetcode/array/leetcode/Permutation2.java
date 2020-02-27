package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/permutations-ii/
public class Permutation2 {

    public void traverse(List<List<Integer>> resultList, List<Integer> current, int[] nums, boolean[] used) {
        if( current.size() == nums.length )
            resultList.add(new ArrayList<Integer>(current));
        else{
            for(int i = 0; i < nums.length; i++) {
                if( used[i] || ( i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) ) // remove duplicate
                    continue;
                current.add(nums[i]);
                used[i] = true;
                traverse(resultList, current, nums, used);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // sort is necessary
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        traverse(resultList, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return resultList;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> results = new Permutation2().permuteUnique(nums);
        for (List<Integer> list : results) {
            System.out.println(list);
        }
    }
}
