package com.geeksforgeeks.graph;


import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/bipartite-graph/
public class BiPatriteGraph {

    public void find (int adjacency[][]){
        int color[] = new int[adjacency.length];
        for (int i = 0 ; i < color.length ; i++) {
            color[i] = -1;
        }

        color[0] = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean bipatritePossible = true;

        while (!queue.isEmpty()){
            int current  = queue.remove();
            if (adjacency[current][current] == 1){
                bipatritePossible = false;
                break;
            }
            int[] adj = adjacency[current];
            for ( int i = 0 ; i < adj.length ; i++) {
                if (adj[i] == 1 && color[i] == -1) {
                    color[i] = 1 - color[current];
                    queue.add(i);
                    continue;
                }
                if (adj[i] == 1 && color[i] == color[current]){
                    bipatritePossible = false;
                    break;
                }
            }
        }

        if (!bipatritePossible){
            System.out.println("BiPartite graph not possible");
        }

    }

    public static void main(String[] args) {
        int arr[][] = {
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 1}
        };

    }

}
