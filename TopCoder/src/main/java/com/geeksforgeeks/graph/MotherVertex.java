package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/
public class MotherVertex {

    static class Graph {
        int numberOfVertices;
        List<Integer> adjacency[];

        public Graph(int nunberOfVertices){
            this.numberOfVertices = nunberOfVertices;
            adjacency = new List[nunberOfVertices];
            for ( int i = 0 ; i < this.numberOfVertices ; i++){
                adjacency[i] = new ArrayList<>();
            }
        }

        public void addEdge (int source, int destination){
            this.adjacency[source].add(destination);
        }
    }

    boolean visited[] = null;
    public void findMother (Graph graph){
        int v = 0;
        visited = new boolean[graph.numberOfVertices];
        for (int i = 0 ; i < graph.numberOfVertices ; i++) {
            if (!visited[i]){
                dfsUtil(i, visited, graph);
                v = i;
            }
        }

        visited =  new boolean[graph.numberOfVertices];
        dfsUtil(v, visited, graph);
        boolean found = true;
        for (int i = 0 ; i < graph.numberOfVertices; i++){
            if (!visited[i]){
                found = false;
                break;
            }
        }
        if (found){
            System.out.println("Mother vertex found at "+v);
        }
    }

    private void dfsUtil(int i, boolean[] visited, Graph graph) {
        visited[i] = true;

        List<Integer> list = graph.adjacency[i];
        for (Integer in : list) {
            if (!visited[in]) {
                dfsUtil(in, visited, graph);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 1);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 2);
        g.addEdge(6, 0);
        new MotherVertex().findMother(g);
    }


}
