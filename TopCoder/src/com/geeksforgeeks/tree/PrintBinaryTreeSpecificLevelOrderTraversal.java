package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
public class PrintBinaryTreeSpecificLevelOrderTraversal
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
	
	private int getLevel(Node root)
	{
		int level = 0;
		while(root != null)
		{
			level++;
			root = root.left;
		}
		return level;
	}

	private int getNumberOfElementsAtLevel(int level)
	{
		return (int)Math.pow(2, level);
	}

	private int getTotalNumberOfElements(int level)
	{
		int elements = 0;
		int currentLevel = 0;
		while(currentLevel < level)
		{
			elements += Math.pow(2, currentLevel++);
		}
		return elements;
	}
	
	public void traverse(Node root)
	{
		Queue<Node> left = new LinkedList<Node>();
		Queue<Node> right = new LinkedList<Node>();
		
		left.add(root.left);
		right.add(root.right);
		
		int count = 1;
		int level = getLevel(root);
		int elements = getTotalNumberOfElements(level);
		int[] arr = new int[elements];
		int index = 0;
		arr[index++] = root.data;
		while(true)
		{
			int temp = count;
			boolean breakLoop = false;
			while(temp > 0)
			{
				Node node = left.remove();
				if (node == null)
				{
					breakLoop = true;
					break;
				}
				System.out.print(node.data+" ");
				arr[index++] = node.data;
				left.add(node.left);
				left.add(node.right);
				
				node = right.remove();
				arr[index++] = node.data;
				System.out.print(node.data+" ");
				right.add(node.right);
				right.add(node.left);
				
				temp--;
			}
			count = count*2;
			if (breakLoop)
			{
				break;
			}
		}
		System.out.println();
		printReverseOrder(arr, level);
	}
	
	private void printReverseOrder(int[] arr, int level) 
	{
		while(level > 0)
		{
			int numberOfElementsAtLevel = getNumberOfElementsAtLevel(--level);
			int index = numberOfElementsAtLevel-1;
			
			for(int i = index ; i < index+numberOfElementsAtLevel; i++)
			{
				System.out.print(arr[i]+" ");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args)
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		root.left.left.left = new Node(8);
		root.left.left.left.left = new Node(16);
		root.left.left.left.right = new Node(17);
		
		root.left.left.right = new Node(9);
		root.left.left.right.left = new Node(18);
		root.left.left.right.right = new Node(19);
		
		root.left.right.left = new Node(10);
		root.left.right.left.left = new Node(20);
		root.left.right.left.right = new Node(21);
		
		
		root.left.right.right = new Node(11);
		root.left.right.right.left = new Node(22);
		root.left.right.right.right = new Node(23);
		
		root.right.left.left = new Node(12);
		root.right.left.left.left = new Node(24);
		root.right.left.left.right = new Node(25);
		
		
		root.right.left.right = new Node(13);
		root.right.left.right.left = new Node(26);
		root.right.left.right.right = new Node(27);
		
		root.right.right.left = new Node(14);
		root.right.right.left.left = new Node(28);
		root.right.right.left.right = new Node(29);
		
		root.right.right.right = new Node(15);
		root.right.right.right.left = new Node(30);
		root.right.right.right.right = new Node(31);
		
		new PrintBinaryTreeSpecificLevelOrderTraversal().traverse(root);
	}

}
