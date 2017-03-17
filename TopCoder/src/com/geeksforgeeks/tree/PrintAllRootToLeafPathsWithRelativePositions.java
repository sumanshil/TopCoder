package com.geeksforgeeks.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//http://www.geeksforgeeks.org/print-all-root-to-leaf-paths-with-there-relative-positions/
public class PrintAllRootToLeafPathsWithRelativePositions {

	private Map<BinaryTreeNode, Integer> verticalHeightMap = new HashMap<BinaryTreeNode, Integer>();
	
	public void find (BinaryTreeNode node) {
		populateVerticalMap(node, 0);
		print(node, new LinkedList<BinaryTreeNode>());
	}
	
	private void print(BinaryTreeNode node, List<BinaryTreeNode> list) {
		if ( node == null ) {
			printList(list);
			System.out.println("=======");
			return;
		}
		
		list.add(node);
		print(node.getLeft(),list);
		print(node.getRight(), list);
		list.remove(list.size()-1);
	}

	private void printList(List<BinaryTreeNode> list) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (BinaryTreeNode node : list) {
			int vert = verticalHeightMap.get(node);
			if (vert > max) {
				max = vert;
			} 
			
			if (vert < min) {
				min = vert;
			}
		}
		
		if (min < 0){
			for (BinaryTreeNode node : list){
				int vert = verticalHeightMap.get(node);
				int num = vert - min;
				StringBuilder sb = new StringBuilder();
				for ( int i = 0 ; i < num ; i++){
					sb.append("_");
				}
				sb.append(node.getData());
				System.out.println(sb.toString());
			}
		} else {
			for (BinaryTreeNode node : list){
				int vert = verticalHeightMap.get(node);
				StringBuilder sb = new StringBuilder();
				for ( int i = 0 ; i < vert ; i++){
					sb.append("_");
				}
				sb.append(node.getData());
				System.out.println(sb.toString());
			}			
		}
	}

	private void populateVerticalMap(BinaryTreeNode node, int vertical) {
		if ( node == null) {
			return;
		}
		verticalHeightMap.put(node, vertical);
		populateVerticalMap(node.getLeft(), vertical-1);
		populateVerticalMap(node.getRight(), vertical+1);				
	}

	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2);
		root.insertInRight(3);
		
		root.getLeft().insertInLeft(4);
		root.getLeft().insertInRight(5);
		
		root.getRight().insertInLeft(6);
		root.getRight().insertInRight(7);
		new PrintAllRootToLeafPathsWithRelativePositions().find(root);
	}

}
