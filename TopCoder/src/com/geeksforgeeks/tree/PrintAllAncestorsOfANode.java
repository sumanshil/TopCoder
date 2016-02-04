package com.geeksforgeeks.tree;

/**
 * Created by sshil on 1/27/2016.
 */
public class PrintAllAncestorsOfANode {

	public void print(BinaryTreeNode binaryTreeNode, int key) {
		printRecursive(binaryTreeNode, key);
	}

	private int printRecursive(BinaryTreeNode root, int key) {
		if (root == null) {
			return 0;
		}
		if (root.getData() == key) {
			return 1;
		}

		int lCount = printRecursive(root.getLeft(), key);
		int rCount = printRecursive(root.getRight(), key);
		if (lCount == 1 && rCount == 0) {
			System.out.println(root.getLeft().getData());
			return lCount;
		} else if (rCount == 1 && lCount == 0) {
			System.out.println(root.getRight().getData());
			return rCount;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2).insertInLeft(4).insertInLeft(7);
		root.getLeft().insertInRight(5);
		root.insertInRight(3);
		new PrintAllAncestorsOfANode().print(root, 5);
	}
}
