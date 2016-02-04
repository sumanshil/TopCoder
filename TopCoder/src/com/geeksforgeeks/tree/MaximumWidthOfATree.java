package com.geeksforgeeks.tree;

/**
 * Created by sshil on 1/27/2016.
 */
public class MaximumWidthOfATree {

	public void maximumWidth(BinaryTreeNode root){
		int height = height(root);
		int maxWidth = Integer.MIN_VALUE;
		for ( int i = 0 ; i < height ; i++){
			int width = getWidthAtHeight(root, i, 0);
			if (width > maxWidth) {
				maxWidth = width;
			}
		}
		System.out.println(maxWidth);


	}

	private int getWidthAtHeight(BinaryTreeNode root, int targetHeight, int currentHeight) {
		if (root == null) {
			return  0;
		}
		if (currentHeight == targetHeight) {
			return 1;
		}
		int lCount = getWidthAtHeight(root.getLeft(), targetHeight, currentHeight+1);
		int rCount = getWidthAtHeight(root.getRight(), targetHeight, currentHeight+1);
		return lCount+rCount;
	}

	private int height(BinaryTreeNode root) {
		if (root == null){
			return  0;
		}
		int lHeight = height(root.getLeft());
		int rHeight = height(root.getRight());
		if (lHeight >= rHeight){
			return lHeight+1;
		} else {
			return rHeight+1;
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2).insertInLeft(3);
		root.getLeft().insertInRight(4);

		root.insertInRight(5).insertInRight(6);
		root.getRight().insertInLeft(7);

		new MaximumWidthOfATree().maximumWidth(root);
	}

}
