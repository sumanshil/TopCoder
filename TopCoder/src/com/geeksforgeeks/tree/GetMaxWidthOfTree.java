package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;
import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class GetMaxWidthOfTree {

	private int getMaxWidth(TreeNode root){
		int height = getHeight(root);
		int maxWidth=Integer.MIN_VALUE;
		for(int i = 1 ; i <= height ; i++){
			int width = getWidth(root, i);
			if (width > maxWidth){
				maxWidth = width;
			}
		}
		return maxWidth;
	}
	private int getWidth(TreeNode root, int level) {
		if (root == null){
			return 0;
		}
		
		if (level == 1){
			return 1;
		} else if (level > 1){
			int lWidth = getWidth(root.left, level-1);
			int rWidth = getWidth(root.right, level-1);
			return lWidth+rWidth;
		}
        
		return -1;
        
	}
	private int getHeight(TreeNode root) {
		if (root == null){
			return 0;
		}
		int lHeight = getHeight(root.left);
		int rHeight = getHeight(root.right);
		if (lHeight > rHeight)
			return lHeight + 1;
		else
			return rHeight+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(1);
		root.left = new BinarySearchTree.TreeNode(2);
		root.right = new BinarySearchTree.TreeNode(3);
		root.left.left = new BinarySearchTree.TreeNode(4);
		root.left.right = new BinarySearchTree.TreeNode(5);
		root.right.right = new BinarySearchTree.TreeNode(8);
		root.right.right.left = new BinarySearchTree.TreeNode(6);
		root.right.right.right = new BinarySearchTree.TreeNode(7);
		int maxWidth  = new GetMaxWidthOfTree().getMaxWidth(root);
		System.out.println(maxWidth);

	}


}
