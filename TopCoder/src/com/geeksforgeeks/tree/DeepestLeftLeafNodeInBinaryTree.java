package com.geeksforgeeks.tree;

public class DeepestLeftLeafNodeInBinaryTree 
{
    static class Node
    {
    	int data;
    	Node left;
    	Node right;
    	Node(int data)
    	{
    		this.data = data;
    	}
    }
    
    private int maxHeight = Integer.MIN_VALUE;
    private int result = 0;
    public int getDeepestleftNode(Node root)
    {
    	getDeepestRecursive(root, false, 0);
    	return result;
    }
	
    private void getDeepestRecursive(Node node, boolean isLeft, int heightSoFar)
	{
    	if (node == null)
    	{
    		return;
    	}
    	else if (isLeafNode(node))
		{
			if (isLeft)
			{
				if (heightSoFar > maxHeight)
				{
					maxHeight = heightSoFar;
					result    = node.data;
				}
			}
			return;
		}
		getDeepestRecursive(node.left , true, heightSoFar+1);
		getDeepestRecursive(node.right, false, heightSoFar+1);
	}
	private boolean isLeafNode(Node node) 
	{
	    return (node.left == null && node.right == null);	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Node root      = new Node(1);
		root.left      = new Node(2);
		root.left.left = new Node(4);

		root.right = new Node(3);
		root.right.right = new Node(6);
		root.right.right.right  = new Node(8);
		root.right.right.right.right  = new Node(10);
		
		root.right.left = new Node(5);
		root.right.left.right = new Node(7);
		root.right.left.right.left = new Node(9);
		int result = new DeepestLeftLeafNodeInBinaryTree().getDeepestleftNode(root);
		System.out.println(result);
	}

}
