package com.geeksforgeeks.tree;

/**
 * Created by sshil on 1/24/2016.
 */
public class ClosestLeafToAGivenNodeInBinaryTree {
	static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		TreeNode(int data) {
			this.data = data;
		}
	}

	private int minDistance = Integer.MAX_VALUE;

	public void find(TreeNode root, TreeNode keyNode) {
		int minDistanceInTree = findNearestLeafNode(keyNode, 0);
		if (minDistanceInTree < minDistance) {
			minDistance = minDistanceInTree;
		}

		findDistanceRecursive(root, keyNode, 0);
		System.out.println(minDistance);
	}

	private int findDistanceRecursive(TreeNode root, TreeNode keyNode, int distance) {
		if (root == null) {
			return  0;
		}
		if ( root == keyNode) {
			return distance;
		}

		int lDistance = findDistanceRecursive(root.left, keyNode, distance+1);
		int rDistance = findDistanceRecursive(root.right, keyNode, distance+1);
		if ( lDistance == 0 && rDistance > 0 ) {
			// key node is in the right side
			// inOrder the min leaf node distance at the left tree
			int minDistance = findNearestLeafNode(root.left, 0);
			if (minDistance + rDistance < this.minDistance) {
				this.minDistance = minDistance+rDistance;
			}
		} else if (lDistance > 0 && rDistance == 0){
			int minDistance = findNearestLeafNode(root.right, 0);
			if (minDistance + lDistance < this.minDistance) {
				this.minDistance = minDistance+lDistance;
			}
		}
		if (lDistance == 0 && rDistance == 0){
			// not found;
			return  0;
		} else if (lDistance > 0 && rDistance == 0){
			return  lDistance+1;
		} else {
			return rDistance+1;
		}

	}


	private int findNearestLeafNode(TreeNode root, int distance){
		if (root == null) {
			return  distance;
		}

		int minDistanceAtLeftTree = findNearestLeafNode(root.left,distance+1);
		int minDistanceAtRightTree = findNearestLeafNode(root.right, distance+1);
		if (minDistanceAtLeftTree == minDistanceAtRightTree){
			return minDistanceAtLeftTree;
		}
		return  minDistanceAtLeftTree < minDistanceAtRightTree ? minDistanceAtLeftTree : minDistanceAtRightTree;
	}


	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left  = new TreeNode(12);
		root.right = new TreeNode(13);

		root.right.left = new TreeNode(14);
		root.right.right = new TreeNode(15);

		root.right.left.left = new TreeNode(21);
		root.right.left.right = new TreeNode(22);

		root.right.right.left = new TreeNode(23);
		root.right.right.right = new TreeNode(24);

		root.right.left.left.left = new TreeNode(1);
		root.right.left.left.right = new TreeNode(2);

		root.right.left.right.left = new TreeNode(3);
		root.right.left.right.right = new TreeNode(4);

		root.right.right.left.left = new TreeNode(5);
		root.right.right.left.right = new TreeNode(6);

		root.right.right.right.left = new TreeNode(7);
		root.right.right.right.right = new TreeNode(8);
		new ClosestLeafToAGivenNodeInBinaryTree().find(root, root.right);
	}
}
