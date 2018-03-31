package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/vertical-sum-in-binary-tree-set-space-optimized/
public class VerticalSumInATreeSpaceOptimized extends VerticalSumOfATree {
	class DoublyLinkedListNode {
		private int verticalHeight;
		private DoublyLinkedListNode next;
		private DoublyLinkedListNode prev;
		private List<BinaryTreeNode> list = new ArrayList<>();
		
	}
	
	class DLLManager{
		private DoublyLinkedListNode start;
		private DoublyLinkedListNode end;
		
		public void add (int verticalHeight, BinaryTreeNode node){
			if (start == null ) {
				start = new DoublyLinkedListNode();
				start.verticalHeight = verticalHeight;
				start.list.add(node);
				end = start;
			} else {
				DoublyLinkedListNode temp = start;
				boolean found = false;
				while (temp != null) {
					if (temp.verticalHeight == verticalHeight ){
						temp.list.add(node);
						found = true;
						break;
					}
				}
				if (!found){
		            DoublyLinkedListNode newNode = new DoublyLinkedListNode();
		            newNode.verticalHeight = verticalHeight;
		            newNode.list.add(node);
		            end.next = newNode;
		            newNode.prev = end;
		            end = newNode;
				}
			}
		}
				
		public void print() {
			DoublyLinkedListNode temp = start;
			while (temp != null) {
				System.out.println("Vertical height "+ temp.verticalHeight);
				for (BinaryTreeNode node : temp.list){
					System.out.println(node.getData());
				}
				temp = temp.next;
			}
		}
	}
	
	public void find (BinaryTreeNode root) {
		DLLManager dllManager = new DLLManager();
		int leftHeight = getLeftHeight(root);
		recursiveUtil(root, leftHeight, dllManager);
		dllManager.print();
	}
	
	private void recursiveUtil(BinaryTreeNode node,
							   int verticalHeight,
							   DLLManager dllManager) {
		if (node == null) {
			return;
		}
		
		dllManager.add(verticalHeight, node);
		recursiveUtil(node.getLeft(), verticalHeight+1, dllManager);
		recursiveUtil(node.getRight(), verticalHeight-1, dllManager);
	}

	public static void main(String[] args){
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2);
		root.insertInRight(3);
		root.getLeft().insertInLeft(4);
		root.getLeft().insertInRight(5);
		root.getRight().insertInLeft(6);
		root.getRight().insertInRight(7);
		new VerticalSumOfATree().find(root);
	}

}
