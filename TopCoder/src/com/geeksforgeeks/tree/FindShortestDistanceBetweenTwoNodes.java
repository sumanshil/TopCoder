package com.geeksforgeeks.tree;

public class FindShortestDistanceBetweenTwoNodes 
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
	
	public int findShortestDistance(Node root, int key1, int key2)
	{
		if (root == null)
			return 0;
		if (root.data == key1)
		{
			int lDistance = recursiveGetDistance(root.left,  key2);
			int rDistance = recursiveGetDistance(root.right, key2);
			if (lDistance > 0)
			{
				return lDistance+1;
			}
			else if (rDistance > 0)
			{
				return rDistance+1;
			}
			else
			{
				return -1;
			}
		}
		else if (root.data == key2)
		{
			int lDistance = recursiveGetDistance(root.left,  key1);
			int rDistance = recursiveGetDistance(root.right, key1);
			if (lDistance > 0)
			{
				return lDistance+1;
			}
			else if (rDistance > 0)
			{
				return rDistance+1;
			}			
			else
			{
				return -1;
			}
		}
		else
		{
			int lDistance = recursiveGetDistance(root.left, key1, key2);
			int rDistance = recursiveGetDistance(root.right, key1, key2);
			if (lDistance > 0 && rDistance ==0)
			{
				return findShortestDistance(root.left, key1, key2);
			}
			else if (lDistance == 0 && rDistance > 0)
			{
				return findShortestDistance(root.right, key1, key2);
			}
			else if (lDistance > 0 && rDistance > 0)
			{			
				return rDistance+lDistance+1;
			}
			else
			{
				return -1;
			}
		}
	}
	
	
	private int recursiveGetDistance(Node root, int key)
	{
		if (root == null)
		   return 0;
	    if (root.data == key)
	    {
	    	return 1;
	    }
	    int lDistance = recursiveGetDistance(root.left, key);
	    int rDistance = recursiveGetDistance(root.right, key);
	    if (lDistance == 0 && rDistance == 0)
	    {
	    	return 0;
	    }
	    else
	    {
	    	return lDistance + rDistance+1;
	    }		
	}


	private int recursiveGetDistance(Node root, int key1, int key2)
	{
		if (root == null)
			return 0;
	    if (root.data == key1 || root.data == key2)
	    {
	    	return 1;
	    }
		
	    int lDistance = recursiveGetDistance(root.left, key1, key2);
	    int rDistance = recursiveGetDistance(root.right, key1, key2);
	    if (lDistance == 0 && rDistance == 0)
	    {
	    	return 0;
	    }
	    else
	    {
	    	return lDistance + rDistance+1;
	    }
	    
	    
	}

//	
//      1
//    /    \
//   2      3
//  / \    / \
// 4   5  6   7
//         \   \
//          8   9 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left  = new Node(4);
		root.left.right = new Node(5);
		
		root.right = new Node(3);
		root.right.right = new Node(7);
		root.right.right.right = new Node(9);
		root.right.left = new Node(6);
		root.right.left.right = new Node(8);
		
		int result = new FindShortestDistanceBetweenTwoNodes().
		                 findShortestDistance(root, 2,8);
		System.out.println(result);
	}

}
