package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/construct-ancestor-matrix-from-a-given-binary-tree/
public class ConstructAncestorMatrixFromAGivenBinaryTree {

	public void build (BinaryTreeNode root, int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		recursiveUtil(root, list, matrix);
		for ( int i = 0 ; i < matrix.length; i++) {
			for (int j = 0 ; j < matrix[i].length ; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}


	private void recursiveUtil(BinaryTreeNode root, List<Integer> list, int[][] matrix) {
		if (root == null) {
			return;
		}
		
		int j = root.getData();
		for ( int in : list) {
			matrix[in][j] = 1;
		}
		list.add(root.getData());
		recursiveUtil(root.getLeft(), list, matrix);
		recursiveUtil(root.getRight(), list, matrix);
		list.remove(list.size()-1);
	}




	public static void main(String[] args){
		/*
		BinaryTreeNode root = new BinaryTreeNode(0);
		root.insertInLeft(1);
		root.insertInRight(2);
		int[][] matrix = {
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};
		*/
		int[][] matrix = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0}
		};
		BinaryTreeNode root = new BinaryTreeNode(5);
		root.insertInLeft(1);
		root.insertInRight(2);
		root.getLeft().insertInLeft(0);
		root.getLeft().insertInRight(4);
		root.getRight().insertInLeft(3);
		new ConstructAncestorMatrixFromAGivenBinaryTree().build(root, matrix);
	}
}