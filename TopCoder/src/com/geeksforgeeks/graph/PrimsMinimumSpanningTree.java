package com.geeksforgeeks.graph;

import com.heap.MinHeapKey;
import com.heap.MinHeapNode;
import com.heap.MinHeapWithMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 5/5/2016.
 */
public class PrimsMinimumSpanningTree {
    private MinHeapWithMap<Vertex<String>> minHeapWithMap = null;
    private Map<Vertex<String>, Edge<Vertex<String>>> edgeMap = new HashMap<>();

    private List<Edge<Vertex<String>>> resultList = new ArrayList<>();
    public void calculate(Vertex<String> source, List<Vertex<String>> listOfVertices){
        minHeapWithMap = new MinHeapWithMap<>(listOfVertices.size());
        listOfVertices
            .stream()
            .map(MinHeapKey::new)
            .forEach(e ->minHeapWithMap.update(e,Integer.MAX_VALUE, false));
        minHeapWithMap.update(new MinHeapKey<>(source), 0, false);
        util();
        edgeMap.entrySet().stream().forEach(System.out::println);
    }

    private void util() {
        while(!minHeapWithMap.isEmpty()){
            MinHeapNode<Vertex<String>> root = minHeapWithMap.extract();
            if (edgeMap.containsKey(root)){
                resultList.add(edgeMap.get(root));
            }
            System.out.println("Current vertex "+root.getData().getData());
            Vertex<String> currentVertex = root.getMinHeapKey().getKey();
                currentVertex
                .getNeighbors()
                .stream()
                .forEach(e -> {
                    System.out.println("Considering neighbor "+e.getData());
                    int distance = currentVertex.getNeighborDistanceMap().get(e);
                    if (minHeapWithMap.contains(new MinHeapKey<>(e)) && minHeapWithMap.get(new MinHeapKey<>(e)).getHeapQualifier() > distance){
                        System.out.println("Neighbor "+e.getData()+" is valid. Updating in Minheap");
                        minHeapWithMap.update(new MinHeapKey<>(e), distance, false);
                        edgeMap.put(e, new Edge<>(currentVertex, e));
                    }
                });
        }

    }


    public static void main(String[] args) {
        List<Vertex<String>> list = new ArrayList<>();
        Vertex<String> vertexA = new Vertex<>("A");
        list.add(vertexA);
        Vertex<String> vertexB = new Vertex<>("B");
        list.add(vertexB);
        Vertex<String> vertexC = new Vertex<>("C");
        list.add(vertexC);
        Vertex<String> vertexD = new Vertex<>("D");
        list.add(vertexD);
        Vertex<String> vertexE = new Vertex<>("E");
        list.add(vertexE);
        Vertex<String> vertexF = new Vertex<>("F");
        list.add(vertexF);

        vertexA.addNeighborUnDirected(vertexD, 1);
        vertexA.addNeighborUnDirected(vertexB, 3);
        vertexB.addNeighborUnDirected(vertexD, 3);
        vertexB.addNeighborUnDirected(vertexC, 1);
        vertexD.addNeighborUnDirected(vertexC, 1);
        vertexD.addNeighborUnDirected(vertexE, 6);
        vertexC.addNeighborUnDirected(vertexE, 5);
        vertexC.addNeighborUnDirected(vertexF, 4);
        vertexE.addNeighborUnDirected(vertexF, 2);

        new PrimsMinimumSpanningTree().calculate(vertexA, list);
    }
}
