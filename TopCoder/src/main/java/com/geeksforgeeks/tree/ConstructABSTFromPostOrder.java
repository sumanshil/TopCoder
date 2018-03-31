package com.geeksforgeeks.tree;

/**
 * Created by sshil on 3/19/2016.
 */
public class ConstructABSTFromPostOrder {

	public void printInOrder(BinaryTreeNode root){
		if(root != null){
			printInOrder(root.getLeft());
			System.out.println(root.getData());
			printInOrder(root.getRight());
		}

	}
	public BinaryTreeNode construct(int[] arr){
		BinaryTreeNode root = constructRecursive(arr, 0, arr.length-1);
		return root;
	}

	private BinaryTreeNode constructRecursive(int[] arr, int low, int high) {
		if (low == high) {
			return new BinaryTreeNode(arr[low]);
		}
		if (low > high){
			return null;
		}
		int rootData = arr[high];
		int minIndex = findMin(arr, low, rootData);
		BinaryTreeNode root = new BinaryTreeNode(rootData);
		root.setLeft(constructRecursive(arr, low, minIndex));
		if (minIndex != -1) {
			root.setRight(constructRecursive(arr, minIndex + 1, high - 1));
		} else {
			root.setRight(constructRecursive(arr, low , high - 1));
		}
		return root;
	}

	private int findMin(int[] arr, int low, int rootData) {
		for ( int i = low ; i < arr.length ; i++) {
			if (arr[i] < rootData && i+1 < arr.length && arr[i+1]> rootData){
				return i;
			}
		}
		return -1;
	}


	public static void main(String[] args) {
		int[] arr = {1, 7, 5, 50, 40, 10};
		BinaryTreeNode root = new ConstructABSTFromPostOrder().construct(arr);
		new ConstructABSTFromPostOrder().printInOrder(root);
	//	System.out.println(root.getData());
	}
}
