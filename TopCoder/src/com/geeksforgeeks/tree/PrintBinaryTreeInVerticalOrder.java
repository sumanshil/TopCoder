package com.geeksforgeeks.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//http://www.geeksforgeeks.org/print-binary-tree-vertical-order/
public class PrintBinaryTreeInVerticalOrder 
{

	static class Node
	{
		int data;
		Node left;
		Node right;
		
		public Node(int data)
		{
			this.data = data;
		}
	}
    
	private Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	
	
	public void printVertical(Node root)
	{
		buildMapRecursive(root, 0);
		Map<Integer, List<Integer>> newMap = new TreeMap<Integer, List<Integer>>(map);
		
		for(Map.Entry<Integer, List<Integer>> entry : newMap.entrySet())
		{
			System.out.println(entry.getValue());
		}
	}	
	
	
	private void buildMapRecursive(Node root, int index) 
	{
		if ( root == null)
		{
			return;
		}
		if (map.get(index) != null)
		{
			List<Integer> list = map.get(index);
			list.add(root.data);
		}
		else
		{
			List<Integer> list = new LinkedList<Integer>();
			list.add(root.data);
			map.put(index, list);
		}
		buildMapRecursive(root.left , index-1);
		buildMapRecursive(root.right, index+1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		root.right.left = new Node(6);
		root.right.left.right = new Node(8);
		
		root.right.right = new Node(7);
		root.right.right.right = new Node(9);
        new PrintBinaryTreeInVerticalOrder().printVertical(root);
	}

}
