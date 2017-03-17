package com.geeksforgeeks.graph;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by sshil on 3/26/2016.
 */
public class Vertex<T> implements Comparable<Vertex<T>>{
	private T data;
	private List<Vertex<T>> neighbors = new ArrayList<>();
	private Map<Vertex<T>, Integer> neighborDistanceMap = new HashMap<>();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Vertex(T data){
		this.data = data;

	}

	public void addNeighborDirected(Vertex<T> neighbor){
		this.neighbors.add(neighbor);
	}

	public void addNeighborDirected(Vertex<T> neighbor, int distance){
		this.neighbors.add(neighbor);
		this.neighborDistanceMap.put(neighbor, distance);
	}

	public void addNeighborUnDirected(Vertex<T> neighbor){
		Assert.assertNotNull(neighbor);
		this.neighbors.add(neighbor);
		neighbor.getNeighbors().add(this);
	}

	public void addNeighborUnDirected(Vertex<T> neighbor, int distance){
		Assert.assertNotNull(neighbor);
		this.neighbors.add(neighbor);
		this.neighborDistanceMap.put(neighbor, distance);
		neighbor.getNeighbors().add(this);
		neighbor.getNeighborDistanceMap().put(this, distance);
	}

	public Map<Vertex<T>, Integer> getNeighborDistanceMap() {
		return neighborDistanceMap;
	}

	public Integer getNeighhborDistance(Vertex<T> vertex){
		return neighborDistanceMap.get(vertex);
	}

	public void setNeighborDistanceMap(Map<Vertex<T>, Integer> neighborDistanceMap) {
		this.neighborDistanceMap = neighborDistanceMap;
	}

	public List<Vertex<T>> getNeighbors(){
		return this.neighbors;
	}

	@Override
	public int hashCode(){
		//return T.hashCode();
		return -1;
	}

	@Override
	public boolean equals(Object other){
		Vertex<T> otherVertex = (Vertex<T>)other;
		return otherVertex.getData().equals(this.getData());
	}

	public String toString(){
		return this.data.toString();
	}

	@Override
	public int compareTo(Vertex<T> o) {
		return o.getData().equals(this.getData()) ? 0 : -1;
	}
}
