package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/graph-valid-tree/
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0 ; i < n ; i++) {
            graph.put(i, new LinkedList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> recursionStack = new LinkedList<>();
        boolean[] visited = new boolean[n];
        boolean res = recursive(0, graph, recursionStack, visited, 0);
        if (!res) {
            return false;
        }
        for (int i = 0 ; i < visited.length ; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean recursive(int index, Map<Integer, List<Integer>> graph,
                              List<Integer> recursionStack, boolean[] visited, int parent) {
        if (recursionStack.contains(index)) {
            return false;
        }
        if (visited[index]) {
            return false;
        }

        visited[index] = true;
        if (!graph.containsKey(index)) {
            return true;
        }
        recursionStack.add(index);

        for (Integer i : graph.get(index)) {
            if (i == parent) {
                continue;
            }
            if (!recursive(i, graph, recursionStack, visited, index)) {
                return false;
            }
        }
        recursionStack.remove(recursionStack.size()-1);
        return true;
    }

    //    private boolean recursive(int index, int[] inorder, Map<Integer, List<Integer>> graph, boolean[] visited) {
//        if (inorder[index] == 1) {
//            return false;
//        }
//        visited[index] = true;
//        inorder[index] = inorder[index] + 1;
//        if (graph.containsKey(index)) {
//            for (Integer i : graph.get(index)) {
//                if (!recursive(i, inorder, graph, visited)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,1}, {1 ,2}, {2,3}, {1,3}, {1, 4}};
        //int[][] edges = {{0,1}, {0 ,2}, {0,3}, {1, 4}};
        boolean result = new GraphValidTree().validTree(n, edges);
        System.out.println(result);
    }
}
