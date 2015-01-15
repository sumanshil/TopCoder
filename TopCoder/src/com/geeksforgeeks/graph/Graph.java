package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int v;
    private List<ArrayList<Integer>> adjacencyArray;
    private int[] visited;
    
    public Graph(int v){
    	this.v = v;
    	adjacencyArray = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0 ; i < v; i++){
    		adjacencyArray.add(new ArrayList<Integer>());
    	}
    	visited = new int[v];
    }
    
    public void dfs(int v){
    	visited[v] = 1;
    	List<Integer> list = adjacencyArray.get(v);
    	System.out.println("Visiting "+v);
    	for(Integer l : list){
    		if (visited[l] == 0){    			
    			dfs(l);
    		}
    	}
    	visited[v] = 0;
    }
    public void addEdge(int v, int w){
    	List<Integer> list = adjacencyArray.get(v);
    	list.add(w);
    }
    
    public static void main(String[] args){
    	Graph g = new Graph(4);
    	g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.dfs(2);
    }
}
