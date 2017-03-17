package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/vertical-sum-in-binary-tree-set-space-optimized/
public class VerticalSumOfABinaryTreeSpaceOptimized {
    class LLNode {
        int data;
    	LLNode next;
    	LLNode prev;
    }
	
    public void find(BinaryTreeNode root) {
    	LLNode newNode = new LLNode();
    	recursiveUtil(root, newNode);
    	System.out.println(newNode);
    	LLNode temp = newNode;
    	while (temp.prev != null){
    		temp = temp.prev;
    	}
    	while (temp != null){
    		System.out.println(temp.data);
    		temp = temp.next;
    	}
    }
	
	private void recursiveUtil(BinaryTreeNode root, LLNode newNode) {
		newNode.data = newNode.data + root.getData();
		
		if (root.getLeft() != null) {
			if (newNode.prev == null){
				LLNode node = new LLNode();
				newNode.prev = node;
				node.next = newNode;
			}
			recursiveUtil(root.getLeft(), newNode.prev);
		}
		
		if (root.getRight() != null ) {
			if (newNode.next == null){
				LLNode node = new LLNode();
				newNode.next = node;
				node.prev = newNode;				
			}
			recursiveUtil(root.getRight(), newNode.next);			
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2);
		root.insertInRight(3);
		root.getLeft().insertInLeft(4);
		root.getLeft().insertInRight(5);
		root.getRight().insertInLeft(6);
		root.getRight().insertInRight(7);
		new VerticalSumOfABinaryTreeSpaceOptimized().find(root);

	}

}
