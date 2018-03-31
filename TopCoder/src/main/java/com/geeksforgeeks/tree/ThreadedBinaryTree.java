package com.geeksforgeeks.tree;

public class ThreadedBinaryTree
{
    static class Node    
    {
    	int data;
    	Node left;
    	Node right;
    	// to chcek if this node is linked by a threaded link
    	boolean isThreaded;
    	
    	public Node(int data)
    	{
    		this.data = data;
    	}
    }
	
    public Node makeThreadedTree(Node root)
    {
    	makeThreadedTreeRecursive(root);
    	return root;
    }
    
    
    private Node previous = null;
	private void makeThreadedTreeRecursive(Node root)
	{
		if (root != null)
		{
			makeThreadedTreeRecursive(root.left);
			if (previous != null)
			{
				if (previous.right == null)
				{
					previous.right = root;
					previous.isThreaded = true;
				}				
			}
			previous = root;
			makeThreadedTreeRecursive(root.right);
		}
		
	}


	public static  void traverseThreadedTree(Node root)
	{
		boolean traverseLeft = true;
		while( root != null)
		{
			if (root.left != null && 
				traverseLeft)
			{
				root = root.left;
			}
			else 
			{
				System.out.println(root.data);				
				if (root.isThreaded)
				{
					traverseLeft = false;
				}
				else
				{
					traverseLeft = true;
				}
				root = root.right;
			}
		}
	}
	
	public static void main(String[] args)
	{
		Node root = new Node(6);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(5);
		
		root.right = new Node(8);
		root.right.left = new Node(7);
		root.right.right = new Node(11);
		root.right.right.left = new Node(9);
		root.right.right.right = new Node(13);
        new ThreadedBinaryTree().makeThreadedTree(root);
        ThreadedBinaryTree.traverseThreadedTree(root);
	}

}
