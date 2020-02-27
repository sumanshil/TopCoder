package com.leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations-ii/
public class Permutation2 {


    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();

        for (Integer num : nums) {
            if (results.size() == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                results.add(list);
            } else {
                List<List<Integer>> newResults = new ArrayList<>();
                for (List<Integer> thisList : results) {
                    for (int i = 0 ; i <= thisList.size() ; i++) {
                        List<Integer> newResult = new ArrayList<>(thisList);
                        newResult.add(i, num);
                        newResults.add(newResult);
                    }
                }
                results = newResults;
            }
        }
        return results;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> results = new Permutation2().permuteUnique(nums);
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }
}
