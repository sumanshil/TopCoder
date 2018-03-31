package com.geeksforgeeks.tree;

//http://www.geeksforgeeks.org/check-sum-covered-uncovered-nodes-binary-tree/
public class CheckSumOfCoveredAndUncoveredBinaryTrees {
	private static int coveredSum = 0;
	private static int uncoveredSum = 0;
		
	public void find(BinaryTreeNode root) {
		recursiveUtil(root, false, false, false);
		System.out.println("Uncovered sum "+uncoveredSum);
		System.out.println("Covered sum "+coveredSum);
	}
		
	private void recursiveUtil(BinaryTreeNode root,
							   boolean isLeftChild,
							   boolean isRightChild,
							   boolean isCovered) {
		// TODO Auto-generated method stub
		if (root == null){
			return;
		}
		if ( !isLeftChild && !isRightChild && !isCovered) {
			// must be root
			uncoveredSum += root.getData();
			recursiveUtil(root.getLeft(),true, false, false);
			recursiveUtil(root.getRight(),false, true, false);
		} else if (isLeftChild && !isCovered) {
			uncoveredSum += root.getData();
			recursiveUtil(root.getLeft(),true, false, false);
			if (root.getRight() != null && root.getLeft() != null) {
			    recursiveUtil(root.getRight(),false, true, true);
			} else if (root.getRight() != null && root.getLeft() == null){
				recursiveUtil(root.getRight(),false, true, false);
			}
		} else if (isLeftChild && isCovered){
			coveredSum += root.getData();
			recursiveUtil(root.getLeft(),true, false, true);
			recursiveUtil(root.getRight(),false, true, true);
		} else if (isRightChild && !isCovered) {
			uncoveredSum += root.getData();
			recursiveUtil(root.getRight(),false, true, false);
			if (root.getLeft() != null && root.getRight() != null) {
			    recursiveUtil(root.getLeft(),true, false, true);
			} else if (root.getLeft() != null && root.getRight() == null){
				recursiveUtil(root.getLeft(),true, false, false);
			}
		} else if (isRightChild && isCovered){
			coveredSum += root.getData();
			recursiveUtil(root.getLeft(),true, false, true);
			recursiveUtil(root.getRight(),false, true, true);
		}
		
	}


	public static void main(String[] args){
		BinaryTreeNode root = new BinaryTreeNode(8);
		root.insertInLeft(3);
		root.insertInRight(10);
		root.getLeft().insertInLeft(1);
		root.getLeft().insertInRight(6);
		root.getLeft().getRight().insertInLeft(4);
		root.getLeft().getRight().insertInRight(7);
		root.getRight().insertInRight(14);
		root.getRight().getRight().insertInLeft(13);
		new CheckSumOfCoveredAndUncoveredBinaryTrees().find(root);
	}

}
