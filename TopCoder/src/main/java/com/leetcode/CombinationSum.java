package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/combination-sum-iii/
public class CombinationSum {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        recursive(list, result, 1, k, n) ;
        return result;
    }

    private void recursive(List<Integer> list, List<List<Integer>> result, int index, int maxCount, int sum) {
        if (sum == 0) {
            if (maxCount == list.size()) {
                result.add(new LinkedList<>(list));
            }
            return;
        }
        if (maxCount == list.size()) {
            return;
        }
        if (sum < 0) {
            return;
        }
        for (int i = index ; i < 10 ; i++) {
            list.add(i);
            recursive(list, result, i + 1, maxCount, sum - i);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        List<List<Integer>> list = new CombinationSum().combinationSum3(k, n);
        System.out.println(list);
    }
}
