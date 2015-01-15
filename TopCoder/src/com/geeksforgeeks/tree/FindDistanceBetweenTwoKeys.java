package com.geeksforgeeks.tree;

public class FindDistanceBetweenTwoKeys {
    static class Node
    {
    	int data;
    	Node left;
    	Node right;
    	
    	public Node(int data)
    	{
    		this.data = data;
    	}
    	
    	public String toString()
    	{
    		return ""+this.data;
    	}
    	
    }
	
    
    
    public int calculate(Node root, int key1, int key2)
    {
        Node lca = findLCA(root, key1, key2);
        int dist = findDistance(lca, key1, key2);
    	System.out.println(dist);
    	return dist;
    	
    }
	
	
	
	private int findDistance(Node lca, int key1, int key2) 
	{		
		int result = findDistanceRecursive(lca, key1, key2, 0);				
		return result;
	}



	private int findDistanceRecursive(Node lca, int key1, int key2, int level) 
	{
		if (lca == null)
		{
			return 0;
		}
		if (lca.data == key1 || lca.data == key2)
		{
			return level;
		}
		
		int lDistance = findDistanceRecursive(lca.left, key1, key2, level+1);
		int rDistance = findDistanceRecursive(lca.right, key1, key2, level+1);
		return lDistance+rDistance;
	}



	private Node findLCA(Node root, int key1, int key2)
	{
		return findLCARecursive(root, key1, key2);
	}



	private Node findLCARecursive(Node root, int key1, int key2)
	{
		if (root == null)
		{
			return null;
		}
		if (root.data == key1 || root.data == key2)
		{
			return root;
		}
		
		int lCount = getCount(root.left, key1, key2);
		int rCount = getCount(root.right, key1, key2);
		
		if (lCount == rCount)
		{
			return root;
		}
		else if (lCount > rCount)
		{
			return findLCARecursive(root.left, key1, key2);
		}
		else
		{
			return findLCARecursive(root.right, key1, key2);
		}
	}



	private int getCount(Node node, int key1, int key2) 
	{
	    if (node == null)
	    {
	    	return 0;
	    }
	    else if (node.data == key1 || node.data == key2)
	    {
	    	return 1;	    		    	
	    }
	    
	    int lCount = getCount(node.left, key1, key2);
	    int rCount = getCount(node.right, key1, key2);
		return lCount + rCount;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);		
		root.left.right = new Node(5);
		
		root.right = new Node(3);
		root.right.right = new Node(7);
		root.right.left = new Node(6);
		root.right.left.right = new Node(8);
		new FindDistanceBetweenTwoKeys().calculate(root, 8, 5);

	}

}
