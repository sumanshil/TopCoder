package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 5/13/2016.
 */
//https://www.youtube.com/watch?v=fAuF0EuZVCk&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j&index=4
public class KruskalsAlgoForMinimumSpanningTree {
    public void calculate(List<Vertex<String>> list, List<Edge<Vertex<String>>> edges){
        Map<Vertex<String>, DisjointSet<Vertex<String>>> disjointSetMap = new HashMap<>();

        list
            .stream()
            .forEach(v -> disjointSetMap.put(v, DisjointSet.makeSet(new DisjointSetNode<>(v))));


        List<Edge<Vertex<String>>> result = new ArrayList<>();
        edges.sort(Comparator.comparing(Edge::getWeight));
        edges
            .stream()
            .forEach(e -> {
                Vertex<String> source = e.getSource();
                Vertex<String> destination = e.getDestination();
                DisjointSet<Vertex<String>> sourceSet = disjointSetMap.get(source);
                DisjointSet<Vertex<String>> destinationSet = disjointSetMap.get(destination);
                if (!DisjointSet.isInSameSet(sourceSet.getRepresentative(), destinationSet.getRepresentative())){
                    result.add(e);
                    DisjointSet<Vertex<String>> resultSet = DisjointSet
                        .union(sourceSet,
                               destinationSet,
                               DisjointSetLeaderSelectionStrategy.DEFAULT);
                    disjointSetMap.put(source, resultSet);
                    disjointSetMap.put(destination, resultSet);

                } else {
                    // they are in same disjoint set. nothing to do
                }
            });

        result.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Vertex<String>> vertices = new ArrayList<>();
        Vertex<String> vertexA = new Vertex<>("A");
        vertices.add(vertexA);

        Vertex<String> vertexB = new Vertex<>("B");
        vertices.add(vertexB);

        Vertex<String> vertexC = new Vertex<>("C");
        vertices.add(vertexC);

        Vertex<String> vertexD = new Vertex<>("D");
        vertices.add(vertexD);

        Vertex<String> vertexE = new Vertex<>("E");
        vertices.add(vertexE);

        Vertex<String> vertexF = new Vertex<>("F");
        vertices.add(vertexF);

        List<Edge<Vertex<String>>> edges = new ArrayList<>();
        vertexA.addNeighborUnDirected(vertexD, 1);
        edges.add(new Edge<>(vertexA, vertexD, 1));

        vertexA.addNeighborUnDirected(vertexB, 3);
        edges.add(new Edge<>(vertexA, vertexB, 3));

        vertexB.addNeighborUnDirected(vertexD, 3);
        edges.add(new Edge<>(vertexB, vertexD, 3));

        vertexB.addNeighborUnDirected(vertexC, 1);
        edges.add(new Edge<>(vertexB, vertexC, 1));

        vertexC.addNeighborUnDirected(vertexD, 1);
        edges.add(new Edge<>(vertexC, vertexD, 1));

        vertexD.addNeighborUnDirected(vertexE, 6);
        edges.add(new Edge<>(vertexD, vertexE, 6));

        vertexC.addNeighborUnDirected(vertexE, 5);
        edges.add(new Edge<>(vertexC, vertexE, 5));

        vertexC.addNeighborUnDirected(vertexF, 4);
        edges.add(new Edge<>(vertexC, vertexF, 4));

        vertexE.addNeighborUnDirected(vertexF, 2);
        edges.add(new Edge<>(vertexE, vertexF, 2));
        new KruskalsAlgoForMinimumSpanningTree().calculate(vertices, edges);
    }
}
