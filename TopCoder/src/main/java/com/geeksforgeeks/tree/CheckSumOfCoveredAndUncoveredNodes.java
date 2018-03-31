package com.geeksforgeeks.tree;

/**
 * Created by sshil on 3/18/2016.
 */
//http://www.geeksforgeeks.org/check-sum-covered-uncovered-nodes-binary-tree/
public class CheckSumOfCoveredAndUncoveredNodes {

	private int coveredSum = 0;
	private int uncoveredSum = 0;
	public void check(BinaryTreeNode root) {
		uncoveredSum += root.getData();
		checkUsingDFS(root.getLeft(), true, false);
		checkUsingDFS(root.getRight(), false, true);
		System.out.println(coveredSum == uncoveredSum);
	}

	private void checkUsingDFS(BinaryTreeNode root, boolean isLeftSide, boolean isRightSide) {
		if (root == null) {
			return;
		}
		if (isLeftSide){
			uncoveredSum += root.getData();
			if (root.getLeft() != null){
				checkUsingDFS(root.getLeft(), true, false);
			}

			if (root.getLeft() == null && root.getRight() != null){
				checkUsingDFS(root.getRight(), true, false);
			}

			if (root.getLeft() != null && root.getRight() != null){
				checkUsingDFS(root.getRight(), false, false);
			}
		} else if (isRightSide){
			uncoveredSum += root.getData();

			if (root.getLeft() != null && root.getRight() == null){
				checkUsingDFS(root.getLeft(), false, true);
			}

			if (root.getLeft() == null && root.getRight() != null){
				checkUsingDFS(root.getRight(), false, true);
			}

			if (root.getLeft() != null && root.getRight() != null){
				checkUsingDFS(root.getRight(), false, true);
			}
		} else {
			coveredSum += root.getData();
			checkUsingDFS(root.getLeft(), false, false);
			checkUsingDFS(root.getRight(), false, false);
		}
	}



	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(8);
		root.setLeft(new BinaryTreeNode(3));
		root.getLeft().setLeft(new BinaryTreeNode(1));

		root.getLeft().setRight(new BinaryTreeNode(6));
		root.getLeft().getRight().setLeft(new BinaryTreeNode(4));
		root.getLeft().getRight().setRight(new BinaryTreeNode(7));
		root.getLeft().getRight().getRight().setRight(new BinaryTreeNode(32));

		root.setRight(new BinaryTreeNode(10));
		root.getRight().setRight(new BinaryTreeNode(14));
		root.getRight().getRight().setLeft(new BinaryTreeNode(13));
		new CheckSumOfCoveredAndUncoveredNodes().check(root);
	}
}
