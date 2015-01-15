package com.geeksforgeeks.tree;

public class CheckTwoNodesInBinaryTree 
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
	
	static Node parent1 = null;
	static Node parent2 = null;
	public static  boolean isCousin(Node root, Node a, Node b)
	{
		int l1 = findLevel(root, a, 0);
		int l2 = findLevel(root, b, 0);
		return  (l1 == l2 && parent1 != parent2);
	}
	
	private static int findLevel(Node root, Node a, int level)
	{
	    if (root == null)
	    {
	    	return 0;
	    }
	    else if (root.left == a
	    		|| root.right == a)
	    {
	    	if (parent1 == null)
	    	{
	    		parent1 = root;
	    	}
	    	else if (parent2 == null)
	    	{
	    		parent2 = root;
	    	}
	    	return level+1;
	    }
	    
	    int l1 = findLevel(root.left, a, level+1);
	    int l2 = findLevel(root.right, a, level+1);
	    if (l1 != 0)
	    {
	    	return l1;
	    }
	    else if (l2 != 0)
	    {
	    	return l2;
	    }
	    else
	    {
	    	return 0;
	    }
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
	    root.left.right.right = new Node(15);
	    root.right.left = new Node(6);
	    root.right.right = new Node(7);
	    root.right.left.right = new Node(8);
	 
	    Node Node1,Node2;
	    Node1 = root.left.left;
	    Node2 = root.left.right;
        boolean isResult = CheckTwoNodesInBinaryTree.isCousin(root, Node1, Node2);
        System.out.println(isResult);
	}

}
