package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/combination-sum/
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> finalResults = new ArrayList<>();
        List<Integer> results= new ArrayList<>();
        recursive1(0, target, candidates, results, finalResults);
        return finalResults;
    }

    private void recursive(int index, int target,int[] candidates,
                           List<Integer> currentElements,
                           List<List<Integer>> finalResults) {
        if (target < 0) {
            return;
        }
        if (index == candidates.length) {
            if (target == 0) {
                List<Integer> newCopy = currentElements.stream().collect(Collectors.toList());
                finalResults.add(newCopy);
                return;
            } else {
                return;
            }
        }
        if (target == 0) {
            List<Integer> newCopy = currentElements.stream().collect(Collectors.toList());
            finalResults.add(newCopy);
            return;
        }


        if (candidates[index] > target) {
            recursive(index+1, target, candidates, currentElements, finalResults);
        } else {
            currentElements.add(candidates[index]);
            recursive(index, target - candidates[index], candidates, currentElements, finalResults);
            currentElements.remove(currentElements.size() - 1);
            recursive(index + 1, target, candidates, currentElements, finalResults);
        }
    }

    private void recursive1(int index, int target, int[] candidates,
                            List<Integer> currentElements,
                            List<List<Integer>> finalResults) {
        if ( index == candidates.length || target < 0) {
            return;
        }

        if (target == 0) {
            finalResults.add(new ArrayList<>(currentElements));
            return;
        }

        for ( int i = index ; i < candidates.length ; i++) {
            if (target - candidates[i] >= 0) {
                currentElements.add(candidates[i]);
                recursive1(i, target-candidates[i], candidates, currentElements, finalResults );
                currentElements.remove(currentElements.size()-1);
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        int target = 8;
        List<List<Integer>> results = new CombinationSum().combinationSum(arr, target);
        for (List<Integer> result : results) {
            for (Integer i : result) {
                System.out.print(i + " -> ");
            }
            System.out.println();
        }
    }
}
