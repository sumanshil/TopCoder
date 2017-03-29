package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DetectCycleInaGraphUsingColor {

	private static Set<GraphVertex> whiteSet = new HashSet<>();
	private static Set<GraphVertex> greySet = new HashSet<>();
	private static Set<GraphVertex> blackSet = new HashSet<>();
	
	static class GraphVertex {
		int data;
		List<GraphVertex> neighbor = new ArrayList<>();
		public GraphVertex(int data) {
			this.data = data;
		}
		
		public void addNeighbor(GraphVertex neighbor){
			this.neighbor.add(neighbor);
		}
		
		@Override
		public int hashCode() {
			return data;
		}
		
		@Override
		public boolean equals(Object o) {
			GraphVertex vertex = (GraphVertex)o;
			return this.data == vertex.data;
		}
	}
	
	
	public void findCycle(GraphVertex root) {
		boolean exists = cycleUtil(root);
		System.out.println(exists);
	}
	
	
	
	private boolean cycleUtil(GraphVertex root) {
		if (greySet.contains(root) || blackSet.contains(root)) {
			return true;
		}
		
		whiteSet.remove(root);
		greySet.add(root);
		
		for (GraphVertex neighbor : root.neighbor) {
			if (whiteSet.contains(neighbor) && cycleUtil(neighbor)){
				return true;
			}
		}
		greySet.remove(root);
		blackSet.add(root);
		return false;
	}



	public static void main(String[] args) {
		GraphVertex vertex0 = new GraphVertex(0);
		GraphVertex vertex1 = new GraphVertex(1);
		GraphVertex vertex2 = new GraphVertex(2);
		GraphVertex vertex3 = new GraphVertex(3);
		whiteSet.add(vertex0);
		whiteSet.add(vertex1);
		whiteSet.add(vertex2);
		whiteSet.add(vertex3);
		
		vertex0.addNeighbor(vertex1);
		vertex0.addNeighbor(vertex2);
		vertex2.addNeighbor(vertex0);
		vertex1.addNeighbor(vertex2);
		vertex2.addNeighbor(vertex3);
		vertex3.addNeighbor(vertex3);
		DetectCycleInaGraphUsingColor detectCycleInaGraphUsingColor = new DetectCycleInaGraphUsingColor();
		detectCycleInaGraphUsingColor.findCycle(vertex0);
	}
}
