package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//http://www.geeksforgeeks.org/longest-path-undirected-tree/
public class DiameterOfAnUndirectedGraph {
	
	static class NodeDistance {
		int node;
		int distance;
		public NodeDistance(int node, int distance){
			this.node = node;
			this.distance = distance;
		}
	}
	
	static class Graph<T> {
		int count;
		List<List<Integer>> adj  = null;
		
		public Graph(int count){
			this.count = count;
			this.adj = new LinkedList<>();
			for ( int i = 0 ; i < count ; i++) {
				adj.add(new ArrayList<>());
			}
		}
		
		void addEdge(int s, int d) {
			if (adj.get(s) == null){
				adj.set(s, new ArrayList<>());
			}
			if (adj.get(d) == null) {
				adj.set(d, new ArrayList<>());
			}
			adj.get(s).add(d);
			adj.get(d).add(s);
		}
		
		public NodeDistance bfs(int start) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(start);
			int[] dist = new int[this.adj.size()];
			dist[start] = 0;
			boolean[] visited = new boolean[this.adj.size()];
			while (!queue.isEmpty()) {
				int s = queue.poll();
				visited[s] = true;
				List<Integer> list = this.adj.get(s);
				for ( Integer in : list) {
					if (!visited[in]){
						visited[in] = true;
					    queue.add(in);
					    dist[in] = dist[s] + 1;
					}
				}
			}
			int max = Integer.MIN_VALUE;
			int maxDest = 0;
			for ( int i = 0 ; i < dist.length ; i++) {
				if (dist[i] > max){
					max = dist[i];
					maxDest = i;
				}
			}
			return new NodeDistance(maxDest, max);
		}
	}

	
	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>(10);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 6);
		graph.addEdge(2, 9);
		graph.addEdge(2, 4);
		graph.addEdge(2, 3);
		graph.addEdge(4, 5);
		graph.addEdge(6, 7);
		graph.addEdge(6, 8);
		NodeDistance node = graph.bfs(0);
		System.out.println(node.distance);
		NodeDistance result = graph.bfs(node.node);
		System.out.println(result.distance);
	}
}
