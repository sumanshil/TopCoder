package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/combination-sum/
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> indexes =  dp( candidates, target);
        List<List<Integer>> results = new ArrayList<>();
        for (List<Integer> list : indexes) {
            List<Integer> elements = new ArrayList<>();
            for (Integer i : list) {
                elements.add(candidates[i]);
            }
            results.add(elements);
        }
        return results;
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
                if ( i > index && candidates[i] == candidates[i-1]) {
                    continue;
                }
                currentElements.add(candidates[i]);
                recursive1(i+1, target-candidates[i], candidates, currentElements, finalResults );
                currentElements.remove(currentElements.size()-1);
            }
        }

    }

    private List<List<Integer>> dp(int[] candidates, int target) {
        for (int i = 0 ; i < candidates.length ; i++) {
            System.out.print(candidates[i]);
        }
        System.out.println();
        List<List<Integer>>[] matrix = new List[target + 1];


        for ( int i = 0 ; i <= target ; i++) {
            matrix[i] = new ArrayList<>();
        }

        for ( int i = 0 ; i < candidates.length ; i++) {
            for (int j = 1; j <= target ; j++) {
                if (candidates[i] > j) {
                    continue;
                }
                if (candidates[i] == j) {
                    boolean found = false;
                    for (List<Integer> list : matrix[j]) {
                        if (list.size() == 1 && candidates[list.get(0)] == candidates[i]) {
                            found = true;
                            break;
                        }

                    }
                    if (!found) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        matrix[j].add(list);
                    }
                } else {
                    List<List<Integer>> temp_List = matrix[j - candidates[i]];

                    for (List<Integer> list : temp_List) {
                        if (list.contains(i)) {
                            continue;
                        }
                        List<Integer> newList = new ArrayList<>(list);
                        newList.add(i);
                        if (!matrix[j].contains(newList)) {
                            matrix[j].add(newList);
                        }
                    }
                }
            }
        }
        return matrix[target];
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> results = new CombinationSum().combinationSum(arr, target);
        for (List<Integer> result : results) {
            for (Integer i : result) {
                System.out.print(i + " -> ");
            }
            System.out.println();
        }
    }
}
