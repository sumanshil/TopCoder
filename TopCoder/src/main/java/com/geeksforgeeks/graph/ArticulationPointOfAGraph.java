package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
public class ArticulationPointOfAGraph {

    static class Graph {
         int numberOfVertices;
        private List<Integer> adjacency[];
        private int low[];
        private boolean visited[];
        private int discoveryTime[];
        private int parent[];
        private boolean articulationPoints[];
        private int time = 0;

        public Graph (int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            this.adjacency = new List[numberOfVertices];
            for ( int i = 0 ; i < this.numberOfVertices ; i++){
                adjacency[i] = new ArrayList<>();
            }
            this.low = new int[numberOfVertices];
            this.visited = new boolean[numberOfVertices];
            this.parent = new int[numberOfVertices];
            this.articulationPoints = new boolean[numberOfVertices];
            this.discoveryTime = new int[numberOfVertices];
            Arrays.fill(parent, -1);
        }

        public List<Integer>[] getAdjacencyList(){
            return adjacency;
        }

        public void addEdge( int u, int w){
            adjacency[u].add(w);
            adjacency[w].add(u);
        }

        public void findAP(){
            recursiveUtil(0);
            for (int i = 0 ; i < this.articulationPoints.length ; i++){
                if (this.articulationPoints[i]){
                    System.out.println(i+"->"+this.articulationPoints[i]);
                }
            }
        }

        private void recursiveUtil(int vertex) {
            time++;
            low[vertex]= time;
            discoveryTime[vertex] = time;
            visited[vertex] = true;
            int children = 0;
            List<Integer> adjacency = this.adjacency[vertex];
            for (Integer adj : adjacency){
                if (!visited[adj]) {
                    children++;
                    parent[adj] = vertex;
                    recursiveUtil(adj);
                    low[vertex] = Math.min(low[vertex], low[adj]);
                    if (parent[vertex] != -1 && discoveryTime[vertex] <= low[adj]) {
                        this.articulationPoints[vertex] = true;
                    }
                    if (children > 1 && parent[vertex] == -1){
                        this.articulationPoints[vertex] = true;
                    }

                } else if (adj != parent[vertex]){
                    low[vertex] = Math.min(low[vertex], low[adj]);
                }
            }

        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.findAP();
    }

}
