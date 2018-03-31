package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.topcoder.geeksforgeeks.BinarySearchTree;
import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class SumTree {

	public static void printInOrder(TreeNode root){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode temp = root;
		while(true){
			while(temp != null){
				stack.push(temp);
				temp = temp.left;
			}
			
			TreeNode top = stack.pop();
			System.out.println(top.data);
		    if (top.right != null){
		    	temp = top.right;
		    }else{
		    	if (stack.isEmpty()){
		    		break;
		    	}
		    }
		}
	}
	
	
	public static boolean hasPathSum(TreeNode root, int sum){
		if (root == null && sum >= 0){
			return false;
		} else if (root != null && sum <= 0){
			return false;
		}
		int subSum = sum - root.data;
		return hasPathSum(root.left, subSum) || hasPathSum(root.right, subSum);
	}
	
	private static List<TreeNode> list = new LinkedList<TreeNode>();
	private static int maxSum = Integer.MIN_VALUE;
	public static void getMaxPath(TreeNode root){
		if (root == null){
			int sum = getSumFromList();
			if (sum > maxSum){
				maxSum = sum;
			}
		} else {
			list.add(root);
			getMaxPath(root.left);
			getMaxPath(root.right);
			list.remove(list.size()-1);
		}
		
	}
	private static int getSumFromList() {
		int sum = 0;
		for(TreeNode node : list){
			sum+= node.data;
		}
		return sum;
	}

	private static TreeNode lca = null;
	private static int getLCA(TreeNode root, int nodeData1, int nodeData2){
		if (root == null){
			return 0;
		} else if (root.data == nodeData1){
			return 1;
		}else if (root.data == nodeData2){
			return 1;
		} else{
			int lCount = getLCA(root.left, nodeData1, nodeData2);
			int rCount = getLCA(root.right, nodeData1, nodeData2);
			if (lCount == 1 && rCount == 1){
				lca = root;				
			}else {
				return lCount + rCount;
			}
			return 0;
		}
	}

	public static int getLevel(TreeNode root, int targetNode){
		return getLevelUtil(root, targetNode, 1);
	}
	private static int getLevelUtil(TreeNode root, int targetNode, int level) {
		if (root == null)
			return 0;
		
		if (root.data == targetNode){
			return level;
		} else {
			int lLevel = getLevelUtil(root.left, targetNode, level+1);
			int rLevel = getLevelUtil(root.right, targetNode, level+1);
			return lLevel+rLevel;
		}
	}


	/**
	 * @param args
	 */
//	    10
//	  8      2
//	3   5  4 
//	    
	
	public static void main(String[] args) {
		BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(10);
		root.left = new BinarySearchTree.TreeNode(8);
		root.left.left = new BinarySearchTree.TreeNode(3);
		root.left.right = new BinarySearchTree.TreeNode(5);
		
		root.right = new BinarySearchTree.TreeNode(2);
		root.right.left = new BinarySearchTree.TreeNode(4);
        //System.out.println(hasPathSum(root, 11));
        //printInOrder(root);
        //getMaxPath(root);
		//getLCA(root, 4, 5);
		int result = getLevel(root, 3);
        System.out.println(result);
	}

}
