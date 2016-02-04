package com.geeksforgeeks.tree;

import java.util.Stack;

/**
 * Created by sshil on 1/23/2016.
 */
public class InOrderNonRecursive {

	static class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int data){
			this.data = data;
		}

	}

	public void inOrderNonRecursive(TreeNode root){
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while(!stack.isEmpty()){
			TreeNode top = stack.peek();
			top = top.left;
			while(top != null){
				stack.push(top);
				top = top.left;
			}

			top = stack.pop();
			System.out.println(top.data);
			while(!stack.isEmpty() && top.right== null) {
				top = stack.pop();
				System.out.println(top.data);
			}
			if (top.right != null) {
				stack.push(top.right);
			}
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);

		root.right = new TreeNode(8);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		new InOrderNonRecursive().inOrderNonRecursive(root);
	}
}
