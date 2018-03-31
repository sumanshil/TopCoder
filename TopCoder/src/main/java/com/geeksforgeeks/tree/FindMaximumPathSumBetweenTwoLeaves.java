package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
public class FindMaximumPathSumBetweenTwoLeaves 
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
	
	public static int findMaximumSum(Node root)
	{
		recursive(root);
		return maxSum;
	}
	
	private static int maxSum = Integer.MIN_VALUE;
	public  static int recursive(Node root)
	{
		if (root == null)
		{
			return 0;
		}
		int lSum = recursive(root.left);
		int rSum = recursive(root.right);
		if (lSum + rSum + root.data > maxSum)
		{
			maxSum = (lSum + rSum + root.data);
		}
		return Math.max(lSum,  rSum) + root.data;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    Node root = new Node(-15);
	    root.left = new Node(5);
	    root.right = new Node(6);
	    root.left.left = new Node(-8);
	    root.left.right = new Node(1);
	    root.left.left.left = new Node(2);
	    root.left.left.right = new Node(6);
	    root.right.left = new Node(3);
	    root.right.right = new Node(9);
	    root.right.right.right= new Node(0);
	    root.right.right.right.left= new Node(4);
	    root.right.right.right.right= new Node(-1);
	    root.right.right.right.right.left= new Node(10);
        int result = FindMaximumPathSumBetweenTwoLeaves.findMaximumSum(root);
        System.out.println(result);
	}

}
