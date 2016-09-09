package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sshil on 3/17/2016.
 */
public class ConvertBinaryTreeToBinarySearchTree {

	public BinaryTreeNode convert(BinaryTreeNode root) throws Exception {
		List<Integer> list = convertToInOrder(root);
		int[] inOrderArray = new int[list.size()];
		int index = 0;
		for (Integer integer : list){
			inOrderArray[index++] = integer;
		}

		int[] sorted = Arrays.copyOf(inOrderArray, inOrderArray.length);
		Arrays.sort(sorted);

		BinaryTreeNode bst = convertToBST(root, inOrderArray, sorted);
		return bst;
	}

	private BinaryTreeNode convertToBST(BinaryTreeNode root,
										int[] inOrderArray,
										int[] sorted) throws Exception {
		if (root == null){
			return null;
		}

		int data = root.getData();
		int index = getIndexInOriginalOrder(data, inOrderArray);
		int element = sorted[index];
		BinaryTreeNode newRoot = new BinaryTreeNode(element);
		newRoot.setLeft(convertToBST(root.getLeft(), inOrderArray,sorted));
		newRoot.setRight(convertToBST(root.getRight(), inOrderArray, sorted));
		return newRoot;
	}

	private int getIndexInOriginalOrder(int data, int[] inOrderArray) throws Exception {
		for ( int i = 0 ; i < inOrderArray.length; i++){
			if (inOrderArray[i] == data){
				return i;
			}
		}
		throw new Exception("Could not inOrder the element");
	}

	private List<Integer> convertToInOrder(BinaryTreeNode root) {
		List<Integer> list = new ArrayList<>();
		addAsInOrder(root, list);
		return list;
	}

	private void addAsInOrder(BinaryTreeNode root, List<Integer> list) {
		if (root != null){
			addAsInOrder(root.getLeft(), list);
			list.add(root.getData());
			addAsInOrder(root.getRight(), list);
		}
	}


	public static void main(String[] args) throws Exception {
		BinaryTreeNode root = new BinaryTreeNode(10);
		root.setRight(new BinaryTreeNode(8));
		root.getRight().setRight(new BinaryTreeNode(7));
		root.getRight().setLeft(new BinaryTreeNode(9));

		root.setLeft(new BinaryTreeNode(15));
		root.getLeft().setLeft(new BinaryTreeNode(20));
		root.getLeft().setRight(new BinaryTreeNode(11));
		new ConvertBinaryTreeToBinarySearchTree().convert(root);

	}
}
