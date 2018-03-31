package com.geeksforgeeks.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by sshil on 3/28/2016.
 */
//https://www.youtube.com/watch?v=2kREIkF9UAs&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j&index=8
public class ArticulationPointOfGraph {

    private Set<Vertex<Integer>> visited = new HashSet<>();
    private Map<Vertex<Integer>, Integer> visitedTimeMap = new HashMap<>();
    private Map<Vertex<Integer>, Integer> lowTimeMap = new HashMap<>();
    private Map<Vertex<Integer>, Vertex<Integer>> childToParentMap = new HashMap<>();
    private static Integer visitTime = 0;
    public void tarzansAlgo(Vertex<Integer> root) {
        dfsUtil(root,  null);
    }

    private void dfsUtil(Vertex<Integer> root, Vertex<Integer> parent) {
        visited.add(root);
        visitedTimeMap.put(root, visitTime+1);
        childToParentMap.put(root, parent);

        int currentVisitTime = visitTime+1;
        visitedTimeMap.put(root, currentVisitTime);
        int currentLowTime = visitTime+1;
        lowTimeMap.put(root, currentLowTime);
        visitTime++;
        int minNeighborLowTime = Integer.MAX_VALUE;
        for ( Vertex<Integer> neighbor : root.getNeighbors()) {
            if (childToParentMap.get(root) != neighbor && !visited.contains(neighbor)){
                dfsUtil(neighbor, root);
            }
        }
        for ( Vertex<Integer> neighbor : root.getNeighbors()) {
            if (lowTimeMap.containsKey(neighbor)
                && childToParentMap.get(root) != neighbor
                &&lowTimeMap.get(neighbor) < minNeighborLowTime){

                if (lowTimeMap.get(neighbor) > currentLowTime){
                    System.out.println("Articulation point found at "+root.getData());
                }
                minNeighborLowTime = lowTimeMap.get(neighbor);
            }
        }
        if (currentLowTime > minNeighborLowTime){
            // not an articulation point
            currentLowTime = minNeighborLowTime;
        }
        lowTimeMap.put(root, currentLowTime);
    }


    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        vertex1.addNeighborUnDirected(vertex2);
        vertex2.addNeighborUnDirected(vertex3);
        vertex1.addNeighborUnDirected(vertex3);
        vertex3.addNeighborUnDirected(vertex4);
        new ArticulationPointOfGraph().tarzansAlgo(vertex1);
    }
}
