package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class Combinations1 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        recursive(n, k, result, new LinkedList<>(), 1);
        return result;
    }

    private void recursive(int n, int k, List<List<Integer>> result, LinkedList<Integer> current, int index) {
        if (current.size() == k) {
            result.add(new LinkedList<>(current));
            return;
        }

        for (int i = index ; i <= n ; i++ ) {
            current.add(i);
            recursive(n, k, result, current, i+1);
            current.remove(current.size()-1);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        List<List<Integer>> results = new Combinations1().combine(n, k);
        for (List<Integer> list : results) {
            System.out.println(list);
        }
    }
}
