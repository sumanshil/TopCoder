package com.geeksforgeeks.graph;

import java.util.Stack;

public class TopologicalSorting {

    public void sort(int adj[][]){
        Stack<Integer> stack = new Stack<>();
        for (int i = 0 ; i < adj.length ; i++){
            recursive(adj, stack, i);
        }
        while (!stack.isEmpty()) {
            int result = stack.pop();
            System.out.println(result);
        }
    }

    private void recursive(int[][] adj, Stack<Integer> stack, int index) {
        if (stack.contains(index)){
            return;
        }

        int adjacency[] = adj[index];
        for ( int i = 0 ; i < adj.length ; i++) {
            if (adjacency[i] == 1) {
                recursive(adj, stack, i);
            }
        }
        stack.push(index);
    }


    public static void main(String[] args) {
        int adjacency[][] = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0}
        };
        new TopologicalSorting().sort(adjacency);
    }

}
