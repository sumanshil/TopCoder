package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
public class BuildTreeFromInorderLevelOrder
{
    static class Node
    {
    	int data;
    	Node left;
    	Node right;
    	public Node( int data )
    	{
    		this.data = data;
    	}
    }
    int[] inorder    = {4, 8, 10, 12, 14, 20, 22};
    int[] levelOrder = {20, 8, 22, 4, 12, 10, 14};
    int levelIndex = 0;
    
    public Node buildTree()
    {
    	Node root = buildTreeRecursive(levelOrder, 0, inorder.length-1);
    	return root;
    }
    
    private Node buildTreeRecursive(int[] levelOrder, int lower, int higher)
    {
	    if (levelOrder.length == 0)
	    {
	    	return null;
	    }
		int element       = levelOrder[0];
		int inorderIndex  = getInorderIndex(element, lower, higher);
	    Node root         = new Node(element);
	    int[] levelOrder1 = extractKeys(lower, inorderIndex-1);   
	    root.left         = buildTreeRecursive(levelOrder1, lower, inorderIndex-1);
	    int[] levelOrder2 = extractKeys(inorderIndex+1, higher);
	    root.right        = buildTreeRecursive(levelOrder2, inorderIndex+1, higher);
	    return root;
	    
	}

	private int[] extractKeys(int lower, int higher) 
	{
		if (higher < lower)
		{
			return new int[0];
		}
		
		int[] result = new int[(higher-lower)+1];
		int index = 0;
		for(int j = 0 ; j < levelOrder.length ; j++)
		{
			for(int i = lower; i <= higher ; i++)
			{
				if (inorder[i] == levelOrder[j])
				{
					result[index++] = levelOrder[j];
				}
 			}
		}
		return result;
		
	}

	private int getInorderIndex( int element, int lower, int higher)
    {
    	for( int i = lower ; i <= higher ; i++)
    	{
    		if (inorder[i] == element)
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
	public static void inorder(Node root)
	{
	   if ( root != null)
	   {
		   inorder (root.left);
		   System.out.println(root.data+" ");
		   inorder(root.right);
	   }
	}
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
       Node root = new BuildTreeFromInorderLevelOrder().buildTree();
       BuildTreeFromInorderLevelOrder.inorder(root);
	}

}
