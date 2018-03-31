package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
public class VerticalSumOfATree {
	
	public void find(BinaryTreeNode root){
		int leftHeight = getLeftHeight(root);
		Map<Integer,List<BinaryTreeNode>> map = new HashMap<>();
		recursiveUtil(map, root, leftHeight);
		printMap(map);
	}
	
	
	private void printMap(Map<Integer, List<BinaryTreeNode>> map) {
		for(Map.Entry<Integer, List<BinaryTreeNode>> entry : map.entrySet()){
			System.out.println("Level is "+entry.getKey());
			System.out.println("Nodes are");
			
			for (BinaryTreeNode node : entry.getValue()){
				System.out.println(node.getData()+" -> ");
			}
		}
		
	}


	private void recursiveUtil(Map<Integer, List<BinaryTreeNode>> map,
							   BinaryTreeNode root,
							   int verticalHeights) {
		if (root == null) {
			return;
		}
		if (map.containsKey(verticalHeights)){
			map.get(verticalHeights).add(root);
		} else {
			List<BinaryTreeNode> list = new ArrayList<>();
			list.add(root);
			map.put(verticalHeights, list);
		}
		recursiveUtil(map, root.getLeft(), verticalHeights-1);
		recursiveUtil(map, root.getRight(), verticalHeights+1);
	}


	protected int getLeftHeight(BinaryTreeNode root) {
		int height = 0;
		BinaryTreeNode temp = root;
		while (temp != null) {
			height++;
			temp = temp.getLeft();
		}
		return height;
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
