package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

// http://www.geeksforgeeks.org/print-leftmost-and-rightmost-nodes-of-a-binary-tree/
public class PrintLeftMostAndRightMostNodes {
	boolean isLastNodeBoundaryNode = false;
	BinaryTreeNode lastNode = null;
	public void print(BinaryTreeNode root){
		Queue<BinaryTreeNode> queue1 = new LinkedList<>();
		Queue<BinaryTreeNode> queue2 = new LinkedList<>();
		Queue<BinaryTreeNode> source = null;
		Queue<BinaryTreeNode> destination = null;
		queue1.add(root);
		source = queue1;
		destination = queue2;
		while (!source.isEmpty()) {
			int size = source.size();
			for ( int i = 0 ; i < size ; i ++){
				BinaryTreeNode node = source.poll();
				if (node.getLeft() != null) {
					destination.add(node.getLeft());
				} 
				if (node.getRight() != null) {
					destination.add(node.getRight());
				}
				if (i == 0) {
					System.out.println(node.getData());
				}
				if ( i == source.size()-1) {
					System.out.println(node.getData());
				}
			}
			Queue<BinaryTreeNode> temp = source;
			source = destination;
			destination = temp;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
