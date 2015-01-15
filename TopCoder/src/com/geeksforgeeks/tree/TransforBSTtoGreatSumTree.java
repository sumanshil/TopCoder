package com.geeksforgeeks.tree;

public class TransforBSTtoGreatSumTree 
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
	private Node root = null;
	public Node doWork(Node root)
	{
		this.root = root;
		recursive(this.root, 0);
	    return this.root;	
	}
	
	public int recursive(Node root, int sum)
	{
		if (root  == null)
		{
			return 0;
		}
		
		int rSum = recursive(root.right,sum);
        int lSum = recursive(root.left, sum + rSum +root.data);
        int temp = root.data;
		root.data = rSum + sum;
		return temp + lSum + rSum;	
	}
	
	
	public void inorder(Node root)
	{
		if (root == null)
			return;
		
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);			
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    Node root = new Node(11);
        root.left = new Node(2);
        root.left.right = new Node(7);
        root.left.left = new Node(1);
        
        root.right = new Node(29);
        root.right.right = new Node(40);
        root.right.left = new Node(15);
        root.right.right.left = new Node(35);
        Node root1 = new TransforBSTtoGreatSumTree().doWork(root);
        new TransforBSTtoGreatSumTree().inorder(root1);
	}

}
