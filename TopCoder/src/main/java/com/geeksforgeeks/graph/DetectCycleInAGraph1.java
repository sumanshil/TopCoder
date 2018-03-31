package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
public class DetectCycleInAGraph1 {

    private Set<Integer> whiteSet = new HashSet<>();
    private Set<Integer> greySet = new HashSet<>();
    private Set<Integer> yellowSet = new HashSet<>();

    public void find (int source, List<Integer> edges[], int n) {
        for (int i = 0 ; i < n ; i++){
            whiteSet.add(i);
        }

        boolean result = findRecursive(source, edges);
        System.out.println(result);
    }

    private boolean findRecursive(int source, List<Integer> edges[]) {
        if (greySet.contains(source) || yellowSet.contains(source)){
            return true;
        }
        greySet.add(source);
        whiteSet.remove(source);

        List<Integer> adjacency = edges[source];
        for (Integer node : adjacency ) {
            if (findRecursive(node, edges)){
                return true;
            }
        }
        greySet.remove(source);
        yellowSet.add(source);
        return false;
    }


    public static void addEdge(List<Integer> edges[], int source, int destination) {
        edges[source].add(destination);
    }


    public static void main(String[] args) {
        int n = 4;
        List<Integer> edges[] = new List[n];
        for ( int i = 0 ; i < n ; i++){
            edges[i] = new ArrayList<>();
        }
        addEdge(edges, 0, 1);
        addEdge(edges, 0, 2);
        addEdge(edges, 1, 2);
      //  addEdge(edges, 2, 0);
        addEdge(edges, 2, 3);
        addEdge(edges, 3, 3);
        new DetectCycleInAGraph1().find(2, edges,n);


    }


}
