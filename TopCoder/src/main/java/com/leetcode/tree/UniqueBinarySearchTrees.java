package com.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/unique-binary-search-trees/
public class UniqueBinarySearchTrees {
    private Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    public int numTrees(int n) {

        if (n == 0) {
            return 0;
        }
        int number = recursive(1, n);
        return number;
    }

    private int recursive(int start, int end) {
        if (map.containsKey(start) && map.get(start).containsKey(end)) {
            return map.get(start).get(end);
        }
        if (start == end) {
            return 1;
        }
        if (start > end) {
            return 1;
        }
        int result = 0;
        for (int i= start ; i <= end ; i++) {
            int lCount = recursive(start, i-1);
            int rCount = recursive(i+1, end);
            result += lCount*rCount;
        }
        if (map.containsKey(start)) {
            map.get(start).put(end, result);
        } else {
            Map<Integer, Integer> newMap = new HashMap<>();
            newMap.put(end, result);
            map.put(start, newMap);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int res = new UniqueBinarySearchTrees().numTrees(n);
        System.out.println(res);
    }
}
