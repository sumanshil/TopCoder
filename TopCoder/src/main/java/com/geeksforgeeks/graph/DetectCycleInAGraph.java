package com.geeksforgeeks.graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by sshil on 3/26/2016.
 */
public class DetectCycleInAGraph {
	private Set<Vertex<Integer>> whiteSet = new HashSet<>();
	private Set<Vertex<Integer>> blackSet = new HashSet<>();
	private Set<Vertex<Integer>> greySet = new HashSet<>();

	private Map<Vertex<Integer>, Vertex<Integer>> parentToChildMap = new HashMap<>();

	public boolean detectCycle(Graph<Integer> graph){
		List<Vertex<Integer>> nodes = graph.getNodes();
		if (nodes.size() > 0){
			nodes.stream().forEach(e -> whiteSet.add(e));
		}

		Optional<Vertex<Integer>> result = nodes.stream().filter(this::cycleExist).findFirst();

		if (result.isPresent()){
			System.out.println("Cycle exist at node "+result.get().getData());
			printCycle(result.get());
			return true;
		}

		return false;
	}

	private void printCycle(Vertex<Integer> vertex) {
		while(vertex != null){
			System.out.print(vertex.getData()+" -> ");
			vertex = parentToChildMap.get(vertex);
		}
	}

	private boolean cycleExist(Vertex<Integer> e) {
		return dfsUtil(e, null);
	}

	private boolean dfsUtil(Vertex<Integer> current, Vertex<Integer> parent) {
		if (parent != null) {
			parentToChildMap.put(parent, current);
		}
		moveToSet(current, whiteSet, greySet);
		for (Vertex<Integer> neighbor : current.getNeighbors()) {
			if ( blackSet.contains(neighbor)){
				continue;
			}

			if (greySet.contains(neighbor)){
				return true;
			}

			if (dfsUtil(neighbor, current)){
				return true;
			}
		}
		moveToSet(current, greySet, blackSet);
		return false;
	}

	private void moveToSet(Vertex<Integer> vertex, Set<Vertex<Integer>> fromSet, Set<Vertex<Integer>> toSet) {
		fromSet.remove(vertex);
		toSet.add(vertex);
	}


	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>();
		Vertex<Integer> vertex1 = new Vertex<>(1);
		Vertex<Integer> vertex2 = new Vertex<>(2);
		Vertex<Integer> vertex3 = new Vertex<>(3);
		Vertex<Integer> vertex4 = new Vertex<>(4);
		Vertex<Integer> vertex5 = new Vertex<>(5);
		Vertex<Integer> vertex6 = new Vertex<>(6);

		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addVertex(vertex4);
		graph.addVertex(vertex5);
		graph.addVertex(vertex6);

		vertex1.addNeighborDirected(vertex2);
		vertex2.addNeighborDirected(vertex3);
		vertex1.addNeighborDirected(vertex3);
		vertex4.addNeighborDirected(vertex1);
		vertex4.addNeighborDirected(vertex5);
		vertex5.addNeighborDirected(vertex6);
		vertex6.addNeighborDirected(vertex4);
		System.out.println(new DetectCycleInAGraph().detectCycle(graph));
	}
}
