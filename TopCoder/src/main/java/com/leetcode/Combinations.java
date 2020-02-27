package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.topcoder.problems.Combination;

//https://leetcode.com/problems/combinations/
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        boolean taken[] = new boolean[n];
        int[] arr= new int[n];
        for (int i = 0 ; i < n ; i++) {
            arr[i] = i + 1;
        }
        List<List<Integer>> resultList = new LinkedList<>();
        List<Integer> currentlist = new LinkedList<>();

        recursive(arr, taken, k, resultList, 0, currentlist);
        return resultList;
    }

    private void recursive(int[] arr, boolean[] taken,
                           int k, List<List<Integer>> list, int index,
                           List<Integer> currentList) {
       if (index > arr.length || currentList.size() > k) {
           return;
       }

       if (currentList.size() == k) {
           list.add(new LinkedList<>(currentList));
           return;
       }
       for (int j = index ; j < arr.length ; j++) {
           if (taken[j]) {
               continue;
           }
           taken[j] = true;
           currentList.add(arr[j]);
           recursive(arr, taken, k, list, j+1, currentList);
           taken[j] = false;
           currentList.remove(currentList.size()-1);
       }

    }


    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        List<List<Integer>> list = new Combinations().combine(n, k);
        System.out.println(list);
    }
}
