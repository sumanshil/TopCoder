package com.geeksforgeeks.tree;

public class CheckIfLeftAndRightTreesAreMirror
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
    
    public boolean checkIfMirror(Node root)
    {
    	boolean  result = checkIfMirror(root.left,
    			                        root.right);
    	return result;
    }
    
	private boolean checkIfMirror(Node left,
			                      Node right)
	{
		if (left == null &&
		    right == null)
		{
			return true;
		}
		else if (notSame(left, right))
		{
			return false;
		}
		
		if (left.data == right.data
			&& checkIfMirror(left.left, right.right)
			&& checkIfMirror(left.right, right.left))
		{
			return true;
		}
		
		return false;
	}

	private boolean notSame(Node left, Node right)
	{
		return (left == null && right != null)
				||(left != null && right == null);
	}
//            1
//        2        2
//	  3    4     4   3
//	5   6 7  8  8 7  6 5 
	public static void main(String[] args)
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(2);
		
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		
		root.right.right = new Node(3);
		root.right.left  = new Node(4);
		
		root.left.left.left  = new Node(5);
		root.left.left.right = new Node(6);
		
		root.left.right.left  = new Node(7);
		root.left.right.right = new Node(8);
		
		root.right.left.left  = new Node(8);
		root.right.left.right = new Node(7);
		
		root.right.right.left  = new Node(6);
		root.right.right.right = new Node(5);
        boolean result = new CheckIfLeftAndRightTreesAreMirror().checkIfMirror(root);
        System.out.println(result);
	}

}
