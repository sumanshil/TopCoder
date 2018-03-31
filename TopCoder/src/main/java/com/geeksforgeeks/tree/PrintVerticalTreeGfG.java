package com.geeksforgeeks.tree;

import com.geeksforgeeks.tree.PrintBinaryTreeInVerticalOrder.Node;

public class PrintVerticalTreeGfG {

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
	
	private int max = Integer.MIN_VALUE;
	private int min = Integer.MAX_VALUE;
	
	private void findMaxMin(Node root, int lineNo)
	{
		if (root == null)
		{
			return;
		}
		if (root.left == null && root.right == null)
		{
			if (lineNo > max)
			{
				max = lineNo;
			}
			
			if (lineNo < min)
			{
				min = lineNo;
			}
			return;
		}
		findMaxMin(root.left, lineNo-1);
		findMaxMin(root.right, lineNo+1);
	}
	
	public void printVertical(Node root)
	{
		findMaxMin(root, 0);
		for(int i = min ; i <= max ; i++)
		{
			printVerticalRecursive(root, i, 0);
			System.out.println();
		}
			
	}
	
	
	private void printVerticalRecursive(Node root, int level, int currentLevel)
	{
		if (root == null)
		{
			return;
		}
		if ( level == currentLevel)
		{
			System.out.print(root.data  +" ");
		}
		printVerticalRecursive(root.left,  level, currentLevel-1);
		printVerticalRecursive(root.right, level, currentLevel+1);
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
        new PrintVerticalTreeGfG().printVertical(root);


	}

}
