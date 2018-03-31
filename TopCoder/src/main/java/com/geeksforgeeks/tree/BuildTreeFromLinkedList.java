package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class BuildTreeFromLinkedList {

	static class Node{
		public Node(int data) {
			this.data = data;
		}
		public int data;
		public Node next;
	}
	
	Node start=null;
	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	public void addNodeToLinkedList(int data){
		if (start == null){
			start = new Node(data);
		} else {
			Node temp = start;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next= new Node(data);
		}
	}
    TreeNode root = null;
    
	public void buildTree(){

	    Node temp = this.start;
	    //(2*i + 1) and (2*i+2)

	    TreeNode currentNode;
	    if (root == null){
	    	root = new TreeNode(temp.data);
	    	queue.add(root);
	    }
	    while(!queue.isEmpty()){
	    	currentNode = queue.remove();
	    	if (temp.next!= null){
	    		temp = temp.next;
		    	currentNode.left = new TreeNode(temp.data);
		    	queue.add(currentNode.left);	    		
	    	}
	    	if (temp.next != null){
	    		temp = temp.next;
		    	currentNode.right = new TreeNode(temp.data);
		    	queue.add(currentNode.right);	    		
	    	}
	    }
	}
	

	public void printInOrder(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode currentNode = this.root;
		stack.push(currentNode);
        currentNode = currentNode.left;		
		while(true){
			if (currentNode != null){
				stack.push(currentNode);				
				currentNode = currentNode.left;
			}else{
				if (stack.isEmpty()){
					break;
				}else{
					currentNode = stack.pop();
					System.out.println(currentNode.data);
					currentNode = currentNode.right;
				}
			}
		}
		
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BuildTreeFromLinkedList linkedList = new BuildTreeFromLinkedList();
        linkedList.addNodeToLinkedList(10);
        linkedList.addNodeToLinkedList(12);
        linkedList.addNodeToLinkedList(15);
        linkedList.addNodeToLinkedList(25);
        linkedList.addNodeToLinkedList(30);
        linkedList.addNodeToLinkedList(36);
        linkedList.buildTree();
        linkedList.printInOrder();
	}

}
