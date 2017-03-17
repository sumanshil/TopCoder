package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/check-if-removing-an-edge-can-divide-a-binary-tree-in-two-halves/
public class CheckIfRemovingAnEdgeCanHalve {
	
	public void find(BinaryTreeNode root) {
		int totalSize = findTotal(root);
		boolean result = recursiveUtil(root, totalSize);
		System.out.println(result);
	}
	
	private boolean recursiveUtil(BinaryTreeNode root, int totalSize) {
		if (root == null) {
			return false;
		}
		int lSize = findTotal(root.getLeft());
		int rSize = findTotal(root.getRight());
		int totalSubTreeSize = lSize + rSize + 1;
		int restSize = (totalSize - totalSubTreeSize);
		if ( (lSize +1 + restSize == rSize) || (rSize + 1 + restSize == lSize) ) {
			return true;
		} else if (recursiveUtil(root.getLeft(), totalSize)) {
			return true;
		} else if (recursiveUtil(root.getRight(), totalSize)){
			return true;
		} else {
			return false;
		}
	}

	private int findTotal(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}		
		return 1 + findTotal(root.getLeft()) + findTotal(root.getRight());
	}

	public static void main(String[] args) {
		/*
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.insertInLeft(1);
		root.insertInRight(6);
		root.getLeft().insertInLeft(3);
		root.getRight().insertInLeft(7);
		root.getRight().insertInRight(4);
		new CheckIfRemovingAnEdgeCanHalve().find(root);
		*/
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.insertInLeft(1);
		root.insertInRight(6);
		root.getRight().insertInLeft(7);
		root.getRight().insertInRight(4);
		root.getRight().getLeft().insertInLeft(3);
		root.getRight().getLeft().insertInRight(2);
		root.getRight().getRight().insertInRight(8);
		new CheckIfRemovingAnEdgeCanHalve().find(root);
	}

}
