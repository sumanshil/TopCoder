package com.geeksforgeeks.tree;

import com.geeksforgeeks.tree.AVLTree.Node;

public class PrintNodesAtKDistanceFromANode {
    static class Node{
 	   int data;
 	   Node left;
 	   Node right;
 	   int height;
 	   public Node(int data){
 		   this.data = data;
 	   }
    }
    
    private void printNodesAtKDown(Node root, int k)
    {
    	if (k == 0)
    	{
    		System.out.println(root.data);
    		return;
    	}
    	printNodesAtKDown(root.left, k-1);
    	printNodesAtKDown(root.right, k-1);
    }
    
    public int printNodesAtK(Node root, int k, Node target)
    {
    	if(root == null)
    	{
    		return -1;
    	}
    	
    	if(root == target)
    	{
    		return 0;
    	}
    	
    	int dl = printNodesAtK(root.left, k, target);
    	if ( dl != -1)
    	{
    		if (dl+1 == k)
    		{
    			System.out.println(root.data);
    		}
    		else
    		{
    			printNodesAtKDown(root.right, k-dl-2);
    		}
    		return dl+1;
    	}
    	
    	int dr = printNodesAtK(root.right, k, target);
    	if( dr != -1)
    	{
    		if (dr+1 == k)
    		{
    			System.out.println(root.data);
    		}
    		else
    		{
    			printNodesAtKDown(root.right, k-dr-2);
    		}
    		return dr+1;    		
    	}
    	return -1;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		

	}

}
