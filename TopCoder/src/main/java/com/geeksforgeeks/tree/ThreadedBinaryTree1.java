package com.geeksforgeeks.tree;

import java.util.Stack;

/**
 * Created by sshil on 2/6/2016.
 */
public class ThreadedBinaryTree1 {

	public ThreadedBinaryTreeNode build(ThreadedBinaryTreeNode root, int key) {
		ThreadedBinaryTreeNode newRoot = buildRecrusive(root, key, null);
		return newRoot;
	}

	public void printInOrder(ThreadedBinaryTreeNode root) {
		ThreadedBinaryTreeNode node = getLeftMost(root);
		while (node != null ) {
			System.out.println(node.getData());
			if (node.isRightThreaded()) {
				node = (ThreadedBinaryTreeNode) node.getRight();
			} else {
				node = (ThreadedBinaryTreeNode) node.getRight();
				node = getLeftMost(node);
			}
		}
	}

	public void removeThread(ThreadedBinaryTreeNode root) {
		ThreadedBinaryTreeNode node1 = getLeftMost(root);
		while(node1 != null) {
			if (node1.isRightThreaded()){
				ThreadedBinaryTreeNode temp  = (ThreadedBinaryTreeNode) node1.getRight();
				node1.setRightThreaded(false);
				node1.setRight(null);
				node1 = temp;
			} else {
				node1 = (ThreadedBinaryTreeNode) node1.getRight();
				node1 = getLeftMost(node1);
			}
		}
	}

	public void printInOrderUsingStack(ThreadedBinaryTreeNode node){
		Stack<ThreadedBinaryTreeNode> stack = new Stack<>();
		stack.push(node);
		while(!stack.isEmpty()){
			ThreadedBinaryTreeNode node1 = stack.peek();
			while (node1.getLeft() != null) {
				ThreadedBinaryTreeNode left = (ThreadedBinaryTreeNode) node1.getLeft();
				stack.push(left);
				node1 = left;
			}
			while(true){
				ThreadedBinaryTreeNode node2 = stack.pop();
				System.out.println(node2.getData());
				if ( node2.getRight() != null ) {
					stack.push((ThreadedBinaryTreeNode) node2.getRight());
					break;
				}
				if (stack.isEmpty()) {
					break;
				}
			}
		}
	}


	private ThreadedBinaryTreeNode getLeftMost(ThreadedBinaryTreeNode root) {
		if (root == null) {
			return null;
		}
		while (root.getLeft() != null){
			root = (ThreadedBinaryTreeNode)root.getLeft();
		}
		return root;
	}

	private ThreadedBinaryTreeNode buildRecrusive(ThreadedBinaryTreeNode root,
										  		  int key,
										  		  ThreadedBinaryTreeNode leftParent) {
		if ( root == null){
			root = new ThreadedBinaryTreeNode(key);
			if (leftParent != null) {
				// set the threading
				root.setRight(leftParent);
				root.setRightThreaded(true);
			}
			return root;
		}

		if (root.getData() > key) {
			root.setLeft(buildRecrusive((ThreadedBinaryTreeNode)root.getLeft(), key, root));
		} else {
			if (root.isRightThreaded()) {
				ThreadedBinaryTreeNode tempNode = (ThreadedBinaryTreeNode) root.getRight();
				ThreadedBinaryTreeNode newNode = new ThreadedBinaryTreeNode(key);
				root.setRight(newNode);
				newNode.setRight(tempNode);
				newNode.setRightThreaded(true);
				root.setRightThreaded(false);
			} else {
				root.setRight(buildRecrusive((ThreadedBinaryTreeNode) root.getRight(), key, leftParent));
			}
		}
		return root;
	}

	public static void main(String[] args) {
		ThreadedBinaryTreeNode root = new ThreadedBinaryTreeNode(5);
		ThreadedBinaryTree1 tbt = new ThreadedBinaryTree1();
		tbt.build(root, 3);
		tbt.build(root, 4);
		tbt.build(root, 2);
		tbt.build(root, 1);
		tbt.build(root, 7);
		tbt.build(root, 6);
		tbt.build(root, 8);
		tbt.printInOrder(root);
		tbt.removeThread(root);
		System.out.println("====================================");
		tbt.printInOrderUsingStack(root);
	}
}
