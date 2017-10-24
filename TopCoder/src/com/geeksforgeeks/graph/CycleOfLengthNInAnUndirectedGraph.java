package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/cycles-of-length-n-in-an-undirected-and-connected-graph/
public class CycleOfLengthNInAnUndirectedGraph {

    static class Graph {
        int v;
        int e;
        int matrix[][];
        Graph(int v, int e) {
            this.v = v;
            this.e = e;
            matrix = new int[v][v];
            for ( int i = 0 ; i < v ; i++){
                matrix[i] = new int[v];
            }
        }

        public void addEdge( int src, int destination) {
            matrix[src][destination] = 1;
            matrix[destination][src] = 1;
        }

        public void printCycles(int cycleLength){
            Set<Integer> alreadyInCycle= new HashSet<>();
            for (int i = 0 ; i < v ; i++) {
                List<Integer> currentpath = new ArrayList<>();
                findCycle(i, currentpath, i, cycleLength, alreadyInCycle);
            }
        }

        private void findCycle(int sourceIndex,
                               List<Integer> currentpath,
                               int currentNode,
                               int cycleLength,
                               Set<Integer> alreadyInCycle) {

            if (currentpath.size() == cycleLength) {
                if (currentNode == sourceIndex && isValidPath(alreadyInCycle, currentpath)) {
                    alreadyInCycle.addAll(currentpath);
                    String currentPath = currentpath.stream().map(e -> e+"").collect(Collectors.joining("=>"));
                    System.out.println(currentPath);
                }
                return;
            }
            if (currentpath.contains(currentNode) && currentpath.size() > 1){
                return;
            }
            currentpath.add(currentNode);

            int[] adjArr = matrix[currentNode];
            for ( int i = 0 ; i < adjArr.length ; i++) {
                if (adjArr[i] == 1) {
                    findCycle(sourceIndex, currentpath, i, cycleLength, alreadyInCycle );
                }
            }
            currentpath.remove(currentpath.size()-1);

        }

        private boolean isValidPath(Set<Integer> alreadyInCycle, List<Integer> currentpath) {
            return currentpath
                    .stream()
                    .anyMatch(e -> !alreadyInCycle.contains(e));
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(5, 6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(0, 3);
        graph.printCycles(4);
    }
}
