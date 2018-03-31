package com.geeksforgeeks.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//http://www.geeksforgeeks.org/strongly-connected-components/
public class StronglyConnectedComponent {

    static class AdjacentNode {
        int v;
        AdjacentNode(int v) {
            this.v = v;
        }

        public int getV(){
            return this.v;
        }
    }

    static class Graph {
        int numberOfVertices;
        List<AdjacentNode>[] adjacencyList;
        Graph(int numberOfVertices){
            this.numberOfVertices = numberOfVertices;
            adjacencyList = new LinkedList[numberOfVertices];
            for (int i = 0 ; i < this.numberOfVertices ; i++){
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination){
            AdjacentNode adjacentNode = new AdjacentNode(destination);
            adjacencyList[source].add(adjacentNode);
        }

        public void find(){
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[this.numberOfVertices];
            for (int i = 0 ; i < numberOfVertices ; i++) {
                if (!visited[i]) {
                    recursive(i, stack, visited);
                }
            }
            visited = new boolean[this.numberOfVertices];

            Graph transposedGraph = getTranspose();
            while (!stack.isEmpty()){
                Stack<Integer> stack1 = new Stack<>();
                int node = stack.pop();
                if (!visited[node]) {
                    transposedGraph.recursive(node, stack1, visited);
                    while (!stack1.isEmpty()){
                        System.out.println(stack1.pop());
                    }
                    System.out.println("============");
                }
            }
        }

        public Graph getTranspose(){
            Graph transposedgraph = new Graph(this.numberOfVertices);
            for ( int i = 0 ; i < numberOfVertices ; i++) {
                List<AdjacentNode> list = adjacencyList[i];
                for (AdjacentNode adjacentNode : list ) {
                    transposedgraph.addEdge(adjacentNode.v, i);
                }
            }
            return transposedgraph;
        }

        private void recursive(int node, Stack<Integer> stack, boolean[] visited) {
            visited[node] = true;
            List<AdjacentNode> list = this.adjacencyList[node];
            for (AdjacentNode adjacentNode : list){
                if (!visited[adjacentNode.v]) {
                    recursive(adjacentNode.v, stack, visited);
                }
            }
            stack.push(node);
        }
    }


    static void textQueries(String[] sentences, String[] queries) {
        Map<String, Set<Integer>> map = new HashMap<>();
        int index = 0;
        for (String str : sentences){
            String[] arr = str.split("\\s+");
            for (String word : arr){
                if (map.containsKey(word)) {
                    map.get(word).add(index);
                } else {
                    Set<Integer> set = new HashSet<>();
                    set.add(index);
                    map.put(word, set);
                }
            }
            index++;
        }

        for (String query : queries) {
            Set<Integer> result = new LinkedHashSet<>();
            String[] arr = query.split("\\s+");
            for (String str : arr) {
                if (map.containsKey(str)) {
                    Set<Integer> res = map.get(str);
                    if (result.size() > 0){
                        result.retainAll(res);
                    } else {
                        result = res;
                    }
                }
            }
            result.stream().forEach(System.out::print);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*
        Graph graph = new Graph(5);
        graph.addEdge(0 , 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.find();
        */

        textQueries(new String[]{"jim likes mary", "kate likes tom", "tom does not like jim"},
                    new String[] {"jim tom", "likes"});
    }



}
