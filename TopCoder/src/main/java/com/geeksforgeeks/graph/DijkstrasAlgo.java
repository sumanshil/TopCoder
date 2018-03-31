package com.geeksforgeeks.graph;

import com.heap.MinHeapKey;
import com.heap.MinHeapNode;
import com.heap.MinHeapWithMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 4/27/2016.
 */
public class DijkstrasAlgo {
    private Map<Vertex<String>, Vertex<String>> parentMap = new HashMap<>();
    private Map<Vertex<String>, Integer> distanceMap = new HashMap<>();


    public void calculate(List<Vertex<String>> list, Vertex<String> startVertex) {
        MinHeapWithMap<Vertex<String>> minHeapWithMap = new MinHeapWithMap<>(list.size());
        list
            .stream()
            .map(MinHeapKey::new)
            .forEach(e -> minHeapWithMap.update(e, Integer.MAX_VALUE, false ));
        minHeapWithMap.update(new MinHeapKey<>(startVertex), 0, false);
        parentMap.put(startVertex, null);
        recursiveUtil(minHeapWithMap);
        distanceMap.entrySet().stream().forEach(e -> System.out.println(e.getKey().getData()+"====>"+e.getValue()));

    }

    private void recursiveUtil(MinHeapWithMap<Vertex<String>> minHeapWithMap) {
        MinHeapNode<Vertex<String>> minHeapNode = minHeapWithMap.extract();
        if (minHeapNode != null){
            distanceMap.put(minHeapNode.getMinHeapKey().getKey(),minHeapNode.getHeapQualifier());
            Vertex<String> currentVertex = minHeapNode.getMinHeapKey().getKey();
            int distanceSoFar = minHeapNode.getHeapQualifier();
            for (Vertex<String> v : currentVertex.getNeighbors()){
                int distaceOfNeighbor = currentVertex.getNeighborDistanceMap().get(v);
                MinHeapKey<Vertex<String>> minHeapKey = new MinHeapKey<>(v);
                if (minHeapWithMap.contains(minHeapKey) && parentMap.get(currentVertex) != v) {
                    parentMap.put(v, currentVertex);
                    MinHeapNode<Vertex<String>> minHeapNode1 = minHeapWithMap.get(minHeapKey);
                    if (minHeapNode1.getHeapQualifier() > distanceSoFar + distaceOfNeighbor){
                        minHeapWithMap.update(minHeapKey, distanceSoFar + distaceOfNeighbor, false );
                    }
                }
            }
            recursiveUtil(minHeapWithMap);
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

        vertexA.addNeighborUnDirected(vertexB, 5);
        vertexA.addNeighborUnDirected(vertexE, 2);
        vertexA.addNeighborUnDirected(vertexD, 9);
        vertexB.addNeighborUnDirected(vertexC, 2);
        vertexC.addNeighborUnDirected(vertexD, 3);
        vertexD.addNeighborUnDirected(vertexF, 2);
        vertexF.addNeighborUnDirected(vertexE, 3);

        new DijkstrasAlgo().calculate(list, vertexA);

    }
}
