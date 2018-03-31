package com.geeksforgeeks.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// Single source Shortest distance
// Bellman Ford Algorithm, complexity O(VE)
// With no negative edge, complexity O( E + VlogV)
//

public class ShortestPathUsingTopologicalSorting {

    static class AdjListNode {
        int v;
        int weight;
        AdjListNode(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    static class Graph {
        int v;
        List<AdjListNode>[] adjListNodeList;

        Graph (int v){
            this.v = v;
            adjListNodeList = new LinkedList[v];
            for ( int i = 0 ; i < v; i++) {
                adjListNodeList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight){
            AdjListNode adjListNode = new AdjListNode(destination, weight);
            adjListNodeList[source].add(adjListNode);
        }

        public void shortestPath(int vertex){

            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[this.v];

            for ( int i = 0 ; i < visited.length ; i++) {
                if (!visited[i]){
                    topologicalSort(stack, visited, i);
                }
            }

            int dist[] = new int[this.v];
            for (int i = 0 ; i < dist.length ; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            dist[vertex] = 0;

            while (!stack.isEmpty()){
                Integer node = stack.pop();
                if (dist[node] != Integer.MAX_VALUE) {
                    List<AdjListNode> adjListNodes = this.adjListNodeList[node];
                    for (AdjListNode listNode : adjListNodes) {
                        dist[listNode.v] = Math.min(dist[listNode.v], dist[node] + listNode.weight);
                    }
                }
            }

            for (int i = 0 ; i < this.v ; i++) {
                if (dist[i] == Integer.MAX_VALUE){
                    System.out.println(" INF ");
                } else {
                    System.out.println(dist[i] +" ");
                }
            }


        }

        private void topologicalSort(Stack<Integer> stack, boolean[] visited, int index) {
            visited[index] = true;

            List<AdjListNode> adjListNodes = adjListNodeList[index];
            for (AdjListNode adjListNode : adjListNodes) {
                if (!visited[adjListNode.v]){
                    topologicalSort(stack, visited, adjListNode.v);
                }
            }
            stack.add(index);
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        int s = 1;
        System.out.println("Following are shortest distances "+
                           "from source " + s );
        g.shortestPath(s);
    }

}
