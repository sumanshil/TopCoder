package com.geeksforgeeks.tree;

import java.util.ArrayList;
//http://www.geeksforgeeks.org/print-nodes-distance-k-leaf-node/
public class PrintAllNodesAtKDistanceFromLeaf {

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
	
	public void printNodes(Node root, int k)
	{
		printNodesRecursive(root, k , new ArrayList<Integer>(), new ArrayList<Boolean>());
	}
	
	private void printNodesRecursive(Node root,
			                         int k,
			                         ArrayList<Integer> list,
			                         ArrayList<Boolean> visited)
	{
		if (root == null)
		{
			return;
		}
		if (root.left == null && root.right == null)
		{
			if (list.size() >= k)
			{
				int l = list.size()-k;
				if (!visited.get(l))
				{
					System.out.println(list.get(l));
					visited.add(l, true);
				}
			}
		//	list.add(root.data);
			return;
		}
		list.add(root.data);
		visited.add(false);
		printNodesRecursive(root.left, k, list, visited);
		printNodesRecursive(root.right, k, list, visited);
		list.remove(list.size()-1);
		visited.add(visited.size()-1, false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);
		
		root.right = new Node(3);
		root.right.right = new Node(7);
		root.right.left  = new Node(6);
		root.right.left.right  = new Node(8);
		
		new PrintAllNodesAtKDistanceFromLeaf().printNodes(root, 2);

	}

}
