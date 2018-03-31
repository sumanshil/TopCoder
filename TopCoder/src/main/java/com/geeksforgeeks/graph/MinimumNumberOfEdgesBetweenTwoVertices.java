package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//http://www.geeksforgeeks.org/minimum-number-of-edges-between-two-vertices-of-a-graph/
public class MinimumNumberOfEdgesBetweenTwoVertices {

    private int[][] matrix = {
            {0,1,1,0,1,0,0},
            {1,0,1,0,0,0,0},
            {1,1,0,0,0,1,0},
            {0,0,0,0,1,0,0},
            {0,0,0,1,0,0,1},
            {0,0,1,0,1,0,0},
            {0,0,0,0,1,0,0}
    };

    public void find(int source, int destination) {
        int[] dp = new int[matrix.length];
        dp[source] = 0;
        boolean[] visited = new boolean[matrix.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()){
            int current = queue.poll();
            visited[current] = true;
            int[] arr = matrix[current];
            for ( int i = 0 ; i < arr.length ; i++) {
                if (arr[i] != 0 && !visited[i]){
                    queue.add(i);
                    dp[i] = dp[current]+1;
                }
            }
        }
        System.out.println(dp[destination]);
    }

    public void find(List<Integer> edges[], int source, int destination, int n) {
        List<Integer> distance = new ArrayList<>(n);
        for ( int i = 0 ; i < n ; i++) {
            distance.add(i, 0);
        }
        List<Boolean> visited = new ArrayList<>(n);
        for ( int i = 0 ; i < n ; i++) {
            visited.add(i, false);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited.set(current, true);
            List<Integer> currentEdges = edges[current];
            for (Integer edge : currentEdges) {
                if (!visited.get(edge)) {
                    distance.set(edge, distance.get(current)+1);
                    queue.add(edge);
                }
            }
        }
        System.out.println(distance.get(destination));
    }

    public static void addEdge(List<Integer> edges[], int source, int destination){
        edges[source].add(destination);
        edges[destination].add(source);
    }

    public static void main(String[] args) {
        //new MinimumNumberOfEdgesBetweenTwoVertices().find(1, 5);
        int n = 9;
        List<Integer> edges[] = new List[n];
        for ( int i = 0 ; i < n ; i++) {
            edges[i] = new ArrayList<>();
        }
        addEdge(edges, 0, 1);
        addEdge(edges, 0, 7);
        addEdge(edges, 1, 7);
        addEdge(edges, 1, 2);
        addEdge(edges, 2, 3);
        addEdge(edges, 2, 5);
        addEdge(edges, 2, 8);
        addEdge(edges, 3, 4);
        addEdge(edges, 3, 5);
        addEdge(edges, 4, 5);
        addEdge(edges, 5, 6);
        addEdge(edges, 6, 7);
        addEdge(edges, 7, 8);
        new MinimumNumberOfEdgesBetweenTwoVertices().find(edges, 0, 5, n);
    }
}
