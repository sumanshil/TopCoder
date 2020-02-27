package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/minimum-height-trees/
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = graph.getOrDefault(edge[0], new LinkedList<>());
            list.add(edge[1]);
            graph.put(edge[0], list);

            list = graph.getOrDefault(edge[1], new LinkedList<>());
            list.add(edge[0]);
            graph.put(edge[1], list);

        }
        Map<Integer, Map<Integer, Integer>> heightMap = new HashMap<>(); // node, <parent, height>
        int[] treeHeights = new int[n];
        int minHeight = Integer.MAX_VALUE;

        for (int i = 0 ; i < n ; i++) {
            int currentHeight = -1;
            for (Integer node : graph.get(i)) {
                currentHeight = Math.max(currentHeight, helper(node, i, heightMap, graph));
            }
            treeHeights[i] = currentHeight + 1;
            minHeight = Math.min(treeHeights[i], minHeight);

        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0 ; i < n ; i++) {
            if (treeHeights[i] == minHeight) {
                list.add(treeHeights[i]);
            }
        }
        return list;
    }

    private int helper(int node, int parent, Map<Integer, Map<Integer, Integer>> heightMap,
                       Map<Integer, List<Integer>> graph) {
        if (heightMap.containsKey(node) && heightMap.get(node).containsKey(parent)) {
            return heightMap.get(node).get(parent);
        }

        int maxHeights = 0;

        for (Integer child : graph.get(node)) {
            if (child != parent) {
                maxHeights = Math.max(maxHeights, helper(child, node, heightMap, graph));
            }
        }
        Map<Integer, Integer> map = heightMap.getOrDefault(node, new HashMap<>());
        map.put(parent, maxHeights);
        heightMap.put(node, map);
        return maxHeights + 1;
    }
    //    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
//        if (edges.length == 0) {
//            return new LinkedList<>(Arrays.asList(0));
//        }
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//
//        for (int[] arr : edges) {
//            int point_0 = arr[0];
//            int point_1 = arr[1];
//            List<Integer> list1 = graph.getOrDefault(point_0, new LinkedList<>());
//            List<Integer> list2 = graph.getOrDefault(point_1, new LinkedList<>());
//            list1.add(point_1);
//            list2.add(point_0);
//            graph.put(point_0, list1);
//            graph.put(point_1, list2);
//        }
//
//        List<Integer> queue = new LinkedList<>();
//        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
//            if (entry.getValue().size() == 1) {
//                queue.add(entry.getKey());
//            }
//        }
//
//        List<Integer> result = new LinkedList<>();
//
//        while (queue.size() > 0) {
//            result = queue;
//            queue = new LinkedList<>();
//
//            for (Integer nodeValue : result) {
//                if (graph.get(nodeValue).size() == 0) {
//                    continue;
//                }
//                int child = graph.get(nodeValue).get(0);
//                if (graph.containsKey(child)) {
//                    graph.get(child).remove(nodeValue);
//                    if (graph.get(child).size() == 1) {
//                        queue.add(child);
//                    }
//                }
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        /**
        int n = 4;
        int[][] edges = {
                {1, 0},
                {1, 2},
                {1, 3}
        };
         **/

        int n = 6;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {0, 3},
                {3, 4},
                {4, 5}

        };

        /**
        int n = 2;
        int[][] edges = {
                {0, 1},
        };

        int n = 6;
        int[][] edges = {
                {3, 0},
                {3, 1},
                {3, 2},
                {3, 4},
                {5, 4}
        };
         **/
        List<Integer> res = new MinimumHeightTrees().findMinHeightTrees(n, edges);
        System.out.println(res);

    }
}
