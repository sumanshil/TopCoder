package com.geeksforgeeks.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by sshil on 4/17/2016.
 */
//https://www.youtube.com/watch?v=ddTC4Zovtbc&index=1&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j
public class TopologicalSortOfAGraph {
    Set<Vertex> visited = new HashSet<>();
    Stack<Vertex> stack = new Stack<>();

    public void sort(Graph<String> graph){
        graph
            .getNodes()
            .stream()
            .filter(vertex -> !visited.contains(vertex))
            .forEach(this::recursiveUtil);

        while(!stack.isEmpty()){
            System.out.println(stack.pop().getData());
        }
    }

    private void recursiveUtil(Vertex<String> vertex) {
        if (vertex == null){
            return;
        }
        visited.add(vertex);
        for (Vertex<String> vertex1 : vertex.getNeighbors()){
            if (!visited.contains(vertex1)){
                recursiveUtil(vertex1);
            }
        }
        stack.push(vertex);

    }


    public static void main(String[] args) {
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");
        Vertex<String> vertexC = new Vertex<>("C");
        Vertex<String> vertexE = new Vertex<>("E");
        Vertex<String> vertexD = new Vertex<>("D");
        Vertex<String> vertexF = new Vertex<>("F");
        Vertex<String> vertexG = new Vertex<>("G");

        Graph<String> graph = new Graph<>();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);
        graph.addVertex(vertexF);
        graph.addVertex(vertexG);

        vertexA.addNeighborDirected(vertexC);
        vertexB.addNeighborDirected(vertexC);
        vertexC.addNeighborDirected(vertexE);
        vertexB.addNeighborDirected(vertexD);
        vertexE.addNeighborDirected(vertexF);
        vertexD.addNeighborDirected(vertexF);
        vertexF.addNeighborDirected(vertexG);
        new TopologicalSortOfAGraph().sort(graph);
    }
}
