package com.geeksforgeeks.advanced;

/**
 * Created by sshil on 1/23/2016.
 */
public class AVLTree1 {
	static class Node {
		private int data;
		private int height;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}

	}

	private Node root = null;

	public AVLTree1( int data) {
		root = new Node(data);
	}
	public void insert(int key) {
		root = insertUtil(root, key);
	}

	private Node insertUtil(Node root, int key) {
		if  ( root == null ) {
			Node node = new Node(key);
			node.height = 0;
			return node;
		}

		if (root.data > key){
			root.left = insertUtil(root.left, key);
		} else {
			root.right = insertUtil(root.right, key);
		}

		root.height = Math.max(root.left.height, root.right.height)+1;
		int balance = getBalance(root);
		if (balance > 1) {
			if (key < root.left.data){
				// left left case
				return rightRotate(root);
			}  else {
				// left right case
				root.left = leftRotate(root.left);
				return rightRotate(root);
			}
		} else if (balance < -1){
			if (key > root.right.data) {
				return leftRotate(root);
			} else {
				root.right = rightRotate(root.right);
				return leftRotate(root);
			}
		}
		return root;
	}


	private Node leftRotate(Node root) {
		Node rootRight = root.right;
		Node rootRightLeft = rootRight.left;

		rootRight.left = root;
		root.right = rootRightLeft;

		rootRight.height = Math.max(height(root.left), height(root.right))+1;
		root.height = Math.max(height(root.left), height(root.right))+1;
		return rootRight;
	}

	private int height(Node root) {
		if (root == null){
			return  0;
		}
		int lHeight = height(root.left);
		int rHeight = height(root.right);
		return lHeight > rHeight ? lHeight+1 : rHeight;
	}

	private Node rightRotate(Node root) {
		Node rootLeft = root.left;
		Node rootLeftRight = rootLeft.right;

		rootLeft.right = root;
		root.left = rootLeftRight;

		root.height = Math.max(height(root.left), height(root.right))+1;
		rootLeft.height = Math.max(height(rootLeft.left), height(rootLeft.right))+1;
		return rootLeft;
	}

	private int getBalance(Node root) {
		return root.left.height - root.right.height;
	}


	public static void main(String[] args) {
		AVLTree1 tree = new AVLTree1(1);
		tree.insert(2);
		tree.insert(3);
		tree.inOrder();
	}

	private void inOrder() {
		inOrderRecursive(this.root);
	}

	private void inOrderRecursive(Node root) {
		if (root != null){
			inOrderRecursive(root.left);
			System.out.println(root.data);
			inOrderRecursive(root.right);
		}

	}
}
