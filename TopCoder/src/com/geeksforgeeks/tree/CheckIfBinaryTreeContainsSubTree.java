package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/check-binary-tree-contains-duplicate-subtrees-size-2/
public class CheckIfBinaryTreeContainsSubTree {

	public void find (BinaryTreeNode root){
		boolean result = checkRecursive(root.getLeft(), root.getRight());
		System.out.println(result);
	}
	
	private boolean checkRecursive(BinaryTreeNode node1, BinaryTreeNode node2) {
		if (node1 == null && node2 != null) {
			return false;
		}
		
		if (node1 != null && node2 == null) {
			return false;
		}
		
		if (node1.getData() == node2.getData()) {
			boolean result = isSame(node1.getLeft(), node2.getLeft()) && isSame(node1.getRight(), node2.getRight());
			if (result && (node1.getLeft() != null || node2.getRight() != null)) {
				return true;
			}
		} 
		
		boolean isSame = isSame(node1.getLeft(), node2) 
				      || isSame(node1.getRight(), node2) 
				      || isSame(node1, node2.getLeft())
				      || isSame(node1, node2.getRight());
		
		if (isSame && (node1.getLeft() != null || node2.getRight() != null)){
			return true;
		} else {
			boolean case1 =  checkRecursive(node1.getLeft(), node2); 
			if (case1 && isSizeGreaterThan2(node1.getLeft(), 0) && isSizeGreaterThan2(node2, 0)){
				return true;
			}
			boolean case2 =  checkRecursive(node1.getRight(), node2);
			if (case2 && isSizeGreaterThan2(node1.getRight(), 0) && isSizeGreaterThan2(node2, 0)){
				return true;
			}
			boolean case3 =  checkRecursive(node1, node2.getLeft());
			if (case3 && isSizeGreaterThan2(node1, 0) && isSizeGreaterThan2(node2.getLeft(), 0)){
				return true;
			}
			boolean case4 =  checkRecursive(node1, node2.getRight());		
			if (case4 && isSizeGreaterThan2(node1, 0) && isSizeGreaterThan2(node2.getLeft(), 0)){
				return true;
			}
		}
		return false;
	}

	private boolean isSizeGreaterThan2(BinaryTreeNode node, int size) {
		if (size >= 2){
			return true;
		}
		if ( node == null) {
			return false;
		}
		return isSizeGreaterThan2(node.getLeft(), size+1) || isSizeGreaterThan2(node.getRight(), size+1);
		
	}

	private boolean isSame(BinaryTreeNode node1, BinaryTreeNode node2) {
		if ( node1 == null && node2 != null) {
			return false;
		}
		
		if (node1 != null && node2 == null) {
			return false;
		}
		
		if (node1 == null && node2 == null) {
			return true;
		}
		
		if (node1.getData() == node2.getData()) {
			return isSame(node1.getLeft(), node2.getLeft()) && isSame(node1.getRight(), node2.getRight());
		}		
		return false;
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2);
		root.insertInRight(3);
		
		root.getLeft().insertInLeft(4);
		root.getLeft().insertInRight(5);
		
		root.getRight().insertInRight(2);
		
		root.getRight().getRight().insertInLeft(4);
		root.getRight().getRight().insertInRight(5);
		
		new CheckIfBinaryTreeContainsSubTree().find(root);

	}

}
