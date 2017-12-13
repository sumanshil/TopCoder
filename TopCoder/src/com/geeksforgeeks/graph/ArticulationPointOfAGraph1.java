package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/biconnectivity-in-a-graph/
public class ArticulationPointOfAGraph1 {

    class Graph {
        private int v;
        private List<Integer> adjacency[];
        private int time = 0;

        public Graph(int v) {
            this.v = v;
            adjacency = new List[v];
            for ( int i = 0 ; i < v ; i++) {
                adjacency[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v){
            adjacency[u].add(v);
            adjacency[v].add(u);
        }


        public void biconnectedUtil(int parentVertex,
                                    int parent[],
                                    int low[],
                                    int discovery[],
                                    boolean visited[]){
            int childrenCount = 0;
            low[parentVertex] = discovery[parentVertex] = ++time;
            List<Integer> adjList = this.adjacency[parentVertex];
            for (Integer child : adjList){
                if (!visited[child]) {
                    childrenCount++;
                    visited[child] = true;
                }
            }
        }
    }



}
