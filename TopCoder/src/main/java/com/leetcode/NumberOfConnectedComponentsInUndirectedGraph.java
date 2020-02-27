package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class NumberOfConnectedComponentsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        /**
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int result = 0;
        boolean[] visited = new boolean[n];

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            List<Integer> list = graph.getOrDefault(a, new LinkedList<>());
            list.add(b);
            graph.put(a, list);

            list = graph.getOrDefault(b, new LinkedList<>());
            list.add(a);
            graph.put(b, list);

        }
        for (int i = 0 ; i < n ; i++) {
            if (visited[i]) {
                continue;
            }
            result++;
            dfs(i, -1, visited, graph);
        }

        return result;
         **/
        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.count;
    }

    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n];

            for (int i = 0 ; i < n ; i++) {
                parent[i] = i;
            }
            count = n;
        }


        public void union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);

            if (p1 != p2) {
                parent[p2] = p1;
                count--;
            }
        }

        private int find(int n) {
            if (n == parent[n]) {
                return n;
            }

            return parent[n] = find(parent[n]);
        }
    }
    private void dfs(int index, int parent, boolean[] visited,
                     Map<Integer, List<Integer>> graph) {
        visited[index] = true;
        if (graph.get(index) == null || graph.get(index).size() == 0) {
            return;
        }


        for (Integer child : graph.get(index)) {
            if (child != parent && !visited[child]) {
                dfs(child, index, visited, graph);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] matrix = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int result = new NumberOfConnectedComponentsInUndirectedGraph().countComponents(n, matrix);
        System.out.println(result);
    }
}
