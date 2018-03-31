package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.topcoder.geeksforgeeks.BinarySearchTree;
import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class ReverseLevelOrderTraversal {

	Stack<TreeNode> stack = new Stack<TreeNode>();
	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	
	public void process(TreeNode root){
		queue.add(root);
		
		while(!queue.isEmpty()){
			TreeNode obj = queue.remove();
			stack.push(obj);
			if (obj.right != null){
				queue.add(obj.right);
			}
			if (obj.left != null){
				queue.add(obj.left);
			}
		}
		
		while(!stack.isEmpty()){
			System.out.print(stack.pop().data+"=>");
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         BinarySearchTree bst = new BinarySearchTree();
         bst.insert(6);
         bst.insert(3);
         bst.insert(8);
         bst.insert(1);
         bst.insert(4);
         new ReverseLevelOrderTraversal().process(bst.getRoot());
	}

}
