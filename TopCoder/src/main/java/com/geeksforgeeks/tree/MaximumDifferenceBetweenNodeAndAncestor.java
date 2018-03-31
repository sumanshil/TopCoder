package com.geeksforgeeks.tree;

/**
 * Created by sshil on 2/23/2016.
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

	private int maxDiff = Integer.MIN_VALUE;
	public void findMaxDiff(BinaryTreeNode root) {
		findRecursive(root, root, root);
		System.out.println(maxDiff);
	}

	private void findRecursive(BinaryTreeNode root, BinaryTreeNode maxRoot, BinaryTreeNode minRoot) {
		if (root == null) {
			return;
		}

		if (maxRoot.getData()-root.getData() > maxDiff){
			maxDiff = maxRoot.getData()-root.getData();
		}
		if (minRoot.getData() -root.getData() > maxDiff) {
			maxDiff = maxRoot.getData() - root.getData();
		}

		findRecursive(root.getLeft(), root.getData() > maxRoot.getData() ? root : maxRoot,
					                  root.getData() < minRoot.getData() ? root : minRoot);

		findRecursive(root.getRight(), root.getData() > maxRoot.getData() ? root : maxRoot,  root);
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(8);
		root.insertInRight(10);
		root.insertInLeft(3);

		root.getLeft().insertInLeft(1);
		root.getLeft().insertInRight(6);

		root.getLeft().getRight().insertInLeft(4);
		root.getLeft().getRight().insertInRight(7);

		root.getRight().insertInRight(14);
		root.getRight().getRight().insertInLeft(13);
		new MaximumDifferenceBetweenNodeAndAncestor().findMaxDiff(root);
	}
}
