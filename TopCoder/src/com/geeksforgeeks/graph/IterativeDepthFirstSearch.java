package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//http://www.geeksforgeeks.org/iterative-depth-first-traversal/
public class IterativeDepthFirstSearch {

    static class Graph {
        private int numberOfVertices;
        private List<Integer> adjacencyMatrix[];
        private boolean visited[];

        public Graph (int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            this.adjacencyMatrix = new List[this.numberOfVertices];
            for ( int i = 0 ; i < this.numberOfVertices ; i++){
                this.adjacencyMatrix[i] = new ArrayList<>();
            }
            visited = new boolean[this.numberOfVertices];
        }

        public void addEdge (int source, int destination){
            this.adjacencyMatrix[source].add(destination);
        }

        public void dfsIterative( int source ) {
            Stack<Integer> stack = new Stack<>();
            stack.add(source);
            while (!stack.isEmpty()) {
                Integer currentTop = stack.peek();
                visited[currentTop] = true;
                List<Integer> adjacentList = this.adjacencyMatrix[currentTop];
                if (adjacentList.isEmpty() || allVisited(adjacentList)){
                    int top = stack.pop();
                    System.out.println(top);
                } else {
                    for (Integer adj : adjacentList) {
                        if (!visited[adj]){
                            stack.push(adj);
                            break;
                        }
                    }
                }
            }
        }

        private boolean allVisited(List<Integer> adjacentList) {
            for (Integer integer : adjacentList){
                if (!visited[integer]){
                    return false;
                }
            }
            return true;
        }

    }
    public static void main(String[] args) {
        Graph g = new Graph(5); // Total 5 vertices in graph
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.dfsIterative(0);
    }

}
