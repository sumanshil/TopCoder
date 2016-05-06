package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private List<Vertex<T>> nodes = null;
	public Graph(){
		nodes = new ArrayList<>();
	}

	public void addVertex(Vertex<T> vertex){
		this.nodes.add(vertex);
	}

	public List<Vertex<T>> getNodes() {
		return nodes;
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


	}
}
