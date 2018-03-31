package com.topcoder.geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {

	public static class TreeNode{
		public int data;
		public TreeNode left;
		public TreeNode right;
		public boolean isNonLeaf ;
		public TreeNode(int data){
			this.data = data;
		}
		
		public String toString(){
			return Integer.toString(this.data);
		}
	}
	
	private TreeNode root;
	
	private TreeNode head;
	private TreeNode tail;
	
	public void insert(int data){
		root = insertNewNode(root, data);
	}
	
	private TreeNode insertNewNode(TreeNode root, int data) {
        if (root == null){
        	return new TreeNode(data);
        }
		
        if (root.data > data){
        	root.left = insertNewNode(root.left, data);
        } else if (root.data < data){
        	root.right = insertNewNode(root.right, data);
        }
        
		return root;
	}

	public void printInorder(){
		printInorder(root);
	}
	private void printInorder(TreeNode root) {
		if (root != null){
			printInorder(root.left);
			System.out.println(root.data);
			printInorder(root.right);
		}
	}
	
	public void convertToDLL(){
		convertToDLL(root);
	}
	public void convertToDLL(TreeNode root){
		if (root == null){
			return ;
		}
		
		convertToDLL(root.left);
		root.left = tail;
		
		if (tail != null)
			tail.right = root;
		else
			head = root;
		
		tail = root;
		if (root.right != null)
			convertToDLL(root.right);
	}

	public TreeNode convertToDLL1(){
		TreeNode root = convertToDLL1(this.root);
		while(root.left != null){
			root = root.left;
		}
		return root;
		
	}
	private TreeNode convertToDLL1(TreeNode root) {
	    if (root == null){
	    	return root;
	    }
		
	    if (root.left != null){
	    	 TreeNode left = convertToDLL1(root.left);
	    	 while(left.right != null){
	    		 left = left.right;
	    	 }
	    	 root.left = left;
	    	 left.right = root;
	    }
	    
	    if (root.right != null){
	    	TreeNode right = convertToDLL1(root.right);
	    	while(right.left != null){
	    		right = right.left;
	    	}
	    	right.left = root;
	    	root.right = right;
	    }
	    
	    return root;
	}

	public void printDLL(){
		TreeNode head = this.head;
		while(head != null){
			System.out.print(head.data +"==>");
			head = head.right;
		}
		
	}
	
	public TreeNode getRoot(){
		return this.root;
	}
	
	public void printAllpaths(){
		printAllpaths(this.root);
	}
	
    private static LinkedList<TreeNode> list = new LinkedList<BinarySearchTree.TreeNode>();
	private void printAllpaths(TreeNode root) {
		if (root == null){
			return ;
		} else if (root.left == null && root.right == null){
			list.add(root);
			printLinkedList();
			return;
		}else{
			list.add(root);
			printAllpaths(root.left);
			printAllpaths(root.right);
			list.remove(list.size()-1);
		}
		
	}

	private void printLinkedList() {
        for(TreeNode node : list){
        	System.out.print(" "+node.data);
        }
		System.out.println();
	}

	private void reverseInorderTraversal(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root  = this.root;
		while(true){
			while(root!=null){
				stack.push(root);
				root = root.right;
			}
			if (stack.isEmpty()){
				break;
			} else {
				TreeNode t1 = stack.pop();
				System.out.println(t1.data);
				root = t1.left;
			}
		}
		
	}
	
	public int getHeightIterative(){
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		int nodeCount = 0;
		int height = 0;
		q.add(this.root);
		while(!q.isEmpty()){
			nodeCount = q.size();
			if (nodeCount > 0 ){
				height++;
			}
			while(nodeCount > 0){
				TreeNode n = q.remove();
				if(n.left != null){
					q.add(n.left);
				} 
				if (n.right != null){
					q.add(n.right);
				}
				nodeCount--;
			}
		}
		return height;
	}
	
	public void inorderTraversalWithoutStackAndWithoutRecursion(){
		TreeNode current = this.root;
		
		while(current != null){
			if (current.left == null){
				System.out.println(current.data);
				current = current.right;
			} else {
				
				TreeNode temp = current.left;
				while(temp.right != null && temp.right != current){
					temp = temp.right;
				}
				if (temp.right == null){
				    temp.right = current;
				    //current.left = temp;
				    current = current.left;
				} else {
					temp.right = null;
					System.out.println(current.data);
					current = current.right;
				}
			}
		}
		
		
	}
	
	/**
	 * @param args
	 */
//          6
//	 -13         14
//	    -8    13    15
//	        7
	public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(-13);
        bst.insert(14);
        bst.insert(-8);
        bst.insert(15);
        bst.insert(13);
        bst.insert(7);
        //bst.printInorder();
        //bst.convertToDLL();
        //bst.printDLL();
//        TreeNode root = bst.convertToDLL1();
//        //bst.printAllpaths();
//        
//        while(root != null){
//        	System.out.print(root.data +" ====> ");
//        	root = root.right;
//        }
        //bst.reverseInorderTraversal();
        //int height = bst.getHeightIterative();
        
        //System.out.println(height);
        bst.inorderTraversalWithoutStackAndWithoutRecursion();
	}

}
