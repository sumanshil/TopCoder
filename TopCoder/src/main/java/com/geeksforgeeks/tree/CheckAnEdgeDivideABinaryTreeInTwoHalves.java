package com.geeksforgeeks.tree;

/**
 * Created by sshil on 3/27/2016.
 */
public class CheckAnEdgeDivideABinaryTreeInTwoHalves {

	public void check(BinaryTreeNode root){
		int totalNumberOfNodes = count(root);
		checkRecursively(root, totalNumberOfNodes);
	}

	private int checkRecursively(BinaryTreeNode root, int totalNumberOfNodes) {
		if (root == null){
			return 0;
		}
		int lCount = checkRecursively(root.getLeft(), totalNumberOfNodes);
		int rCount = checkRecursively(root.getRight(), totalNumberOfNodes);
		if (totalNumberOfNodes - lCount == lCount){
			System.out.println("Found Edge between "+root.getData()+" and "+root.getLeft().getData());
		} else if (totalNumberOfNodes - rCount == rCount) {
			System.out.println("Found Edge between "+root.getData()+" and "+root.getRight().getData());
		}
		return lCount+rCount+1;
	}

	private int count(BinaryTreeNode root) {
		if (root == null){
			return 0;
		}
		int lCount = count(root.getLeft());
		int rCount = count(root.getRight());
		return lCount+rCount+1;
	}


	public static void main(String[] args) {
		/*
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.setLeft(new BinaryTreeNode(1));
		root.getLeft().setLeft(new BinaryTreeNode(3));

		root.setRight(new BinaryTreeNode(6));
		root.getRight().setLeft(new BinaryTreeNode(7));
		root.getRight().setRight(new BinaryTreeNode(4));
		*/
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.setLeft(new BinaryTreeNode(1));

		root.setRight(new BinaryTreeNode(6));
		root.getRight().setLeft(new BinaryTreeNode(7));
		root.getRight().getLeft().setLeft(new BinaryTreeNode(3));
		root.getRight().getLeft().setRight(new BinaryTreeNode(2));

		root.getRight().setRight(new BinaryTreeNode(4));
		root.getRight().getRight().setRight(new BinaryTreeNode(8));

		new CheckAnEdgeDivideABinaryTreeInTwoHalves().check(root);
	}
}
