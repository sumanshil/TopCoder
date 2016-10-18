package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by sshil on 10/14/16.
 */
public class RootsOfATreeWhichGivesMinimumHeight {

    static class Graph {

        private boolean[] deleted = null;
        private int edges;
        private List<GraphNode> nodes = new ArrayList<>();

        public Graph( int edges) {
            deleted = new boolean[edges];
            this.edges = edges;
            for ( int i = 0 ; i < this.edges; i++){
                nodes.add(new GraphNode(i));
            }
        }

        public void setEdges(int edges){
            this.edges = edges;
        }

        public int getEdges(){
            return edges;
        }

        public void addEdge(int source, int destination) {
            GraphNode sourceN = this.nodes.get(source);
            GraphNode destN = this.nodes.get(destination);
            sourceN.add(destN);
            destN.add(sourceN);
        }

        public void removeNode(GraphNode node){
            this.deleted[node.data] = true;
            node.adjacentList.stream().forEach(e -> {
                e.remove(node);
            });
            edges--;
        }

        public void findMid(){

            Queue<GraphNode> queue = new LinkedList<>();

            for ( int i = 0 ; i < this.edges ; i++){
                GraphNode node = this.nodes.get(i);
                if (node.getDegree() == 1) {
                    queue.add(node);
                }
            }

            while (this.edges > 2) {
                GraphNode gn = queue.poll();
                List<GraphNode> adjList = gn.adjacentList;
                this.removeNode(gn);
                adjList.stream().
                        filter(e -> e.getDegree() == 1).
                        peek(e -> System.out.println("Adding node "+e.data)).
                        forEach(queue::add);
            }


            System.out.println("Result");
            queue.stream().forEach(System.out::println);
        }
    }


    static class GraphNode {

        private int data;
        private List<GraphNode> adjacentList = new ArrayList<>();
        public GraphNode( int data) {
            this.data = data;
        }

        public void add(GraphNode node){
            adjacentList.add(node);
        }

        public void remove(GraphNode graphNode){
            adjacentList.remove(graphNode);
        }

        public int getDegree(){
            return adjacentList.size();
        }

        public boolean equals(GraphNode graphNode){
            return this.data == graphNode.data;
        }

        public int hashCode(){
            return this.data;
        }

        public String toString(){
            return this.data+"";
        }
    }

    public static void main(String[] args) {
        /*
        Graph graph = new Graph(6);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);
        graph.addEdge(5, 4);
        graph.findMid();
        */
        Graph graph = new Graph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.findMid();

    }
}
