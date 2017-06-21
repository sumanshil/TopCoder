package com.geeksforgeeks.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 4/1/17.
 */
//http://www.geeksforgeeks.org/union-find/
public class DetectCyleUsingUnionFindAlgo<T extends Integer> {

    static class Edge<T extends Integer> {
        T source;
        T destination;
        Edge( T source, T destination){
            this.source = source;
            this.destination = destination;
        }
    }

    static class Graph<T extends Integer> {
        private List<Edge<T>> edges = new LinkedList<>();
        private int size;
        public Graph(int  size) {
            this.size = size;
        }

        public void addEdge (T source, T destination) {
            this.edges.add(new Edge<>(source, destination));
        }

        public int getSize() {
            return this.size;
        }
    }


    int[] parent = null;

    public  void  findCycle (Graph<T> graph){
        parent = new int[graph.size];
        Arrays.fill(parent, -1);
        for ( int i = 0 ; i < graph.edges.size() ; i++) {
            Edge<T> edge = graph.edges.get(i);
            int member1 = find(edge.source);
            int member2 = find(edge.destination);

            if (member1 == member2) {
                System.out.println("Cycle exists");
                break;
            }
            union(member1, member2);
        }
    }

    private void union(int member1, int member2) {
        int xSet = find(member1);
        int ySet = find(member2);
        parent[xSet] = ySet;
    }

    private int find(int source) {
        if (parent[source] == -1){
            return source;
        }
        return find(parent[source]);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(4);
        graph.addEdge(0,1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        new DetectCyleUsingUnionFindAlgo<>().findCycle(graph);
    }
}
