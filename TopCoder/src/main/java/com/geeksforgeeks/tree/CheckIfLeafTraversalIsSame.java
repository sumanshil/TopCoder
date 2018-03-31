package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 2/4/2016.
 */
public class CheckIfLeafTraversalIsSame {

	public void find(BinaryTreeNode root1, BinaryTreeNode root2) {
		List<Integer> list1 = new ArrayList<>();
		getLeafNodes(root1, list1);
		List<Integer> list2 = new ArrayList<>();
		getLeafNodes(root2, list2);
		boolean isSame = isSame(list1, list2);
		System.out.println(isSame);
	}

	private void getLeafNodes(BinaryTreeNode root, List<Integer> list) {
		if (root == null){
			return;
		}
		if (isLeaf(root)){
			list.add(root.getData());
			return;
		}
		getLeafNodes(root.getLeft(), list);
		getLeafNodes(root.getRight(), list);
	}

	private boolean isLeaf(BinaryTreeNode root) {
		return root.getLeft() == null && root.getRight() == null;
	}

	private boolean isSame(List<Integer> list1, List<Integer> list2) {
		if (list1.size() != list2.size()){
			return false;
		}
		int index = 0;
		for (Integer element : list1){
			if (!list2.get(index).equals(element)){
				return false;
			}
			index++;
		}
		return true;
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2).insertInLeft(4);
		root.insertInRight(3).insertInLeft(6);
		root.getRight().insertInRight(7);

		BinaryTreeNode root1 = new BinaryTreeNode(0);
		root1.insertInLeft(5);
		root1.getLeft().insertInRight(4);

		root1.insertInRight(8);
		root1.getRight().insertInRight(7);
		root1.getRight().insertInLeft(6);
		new CheckIfLeafTraversalIsSame().find(root, root1);

	}
}
