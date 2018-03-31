package com.geeksforgeeks.graph;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/number-nodes-two-vertices-acyclic-graph-disjoint-union-method/
public class DistanceBetweenTwoNodesInAGraph {

    private int[][] matrix = {
            {0,0,0,1,0,0},
            {0,0,0,1,0,1},
            {0,0,0,0,0,1},
            {1,1,0,0,1,0},
            {0,0,0,1,0,0},
            {0,1,1,0,0,0}
    };

    public void calculate(int source, int destination){
        int[] parent = new int[matrix.length];
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[matrix.length];
        queue.add(source);
        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            int[] arr = matrix[currentNode];
            for (int i = 0 ; i < arr.length ; i++ ) {
                if (arr[i] != 0 && !visited[i]){
                    parent[i] = currentNode;
                    queue.add(i);
                }
            }
        }

        int currentParent = parent[destination];
        int count = 0;
        while (currentParent != source){
            currentParent = parent[currentParent];
            count++;
        }
        System.out.println(count);
    }
    public static void main(String[] args) {

    }
}
