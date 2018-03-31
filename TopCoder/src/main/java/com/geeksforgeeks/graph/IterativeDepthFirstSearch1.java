package com.geeksforgeeks.graph;

import java.util.List;
import java.util.Stack;

//http://www.geeksforgeeks.org/iterative-depth-first-traversal/
public class IterativeDepthFirstSearch1 {
    
    public void find (ArticulationPointOfAGraph.Graph graph) {
        iterative(graph, 0);
    }

    private void iterative(ArticulationPointOfAGraph.Graph graph, int vertex) {
        boolean visited[] = new boolean[graph.numberOfVertices];
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        while (!stack.isEmpty()){
            int peek = stack.peek();
            visited[peek] = true;
            List<Integer> list = graph.getAdjacencyList()[vertex];
            if (allNeighborsNotVisited(list, visited)){
                for (Integer adjacent : list){
                    if (!visited[adjacent]){
                        stack.push(adjacent);
                        break;
                    }
                }
            } else {
                int node = stack.pop();
                System.out.println(node);
            }
        }
    }

    private boolean allNeighborsNotVisited(List<Integer> list, boolean visited[]) {
        return  list.stream().anyMatch(e -> !visited[e]);
    }

    public static void main(String[] args) {
        ArticulationPointOfAGraph.Graph g
                = new ArticulationPointOfAGraph.Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        new IterativeDepthFirstSearch1().find(g);
    }

}
