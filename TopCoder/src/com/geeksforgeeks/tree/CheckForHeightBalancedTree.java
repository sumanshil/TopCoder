package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
public class CheckForHeightBalancedTree {
    static class Node
    {
    	int data;
    	Node left;
    	Node right;
    	int max;
    	int min;
    	boolean isHeightBalanced;
    	public Node(int data)
    	{
    		this.data = data;
    		isHeightBalanced = true;
    	}
    	
    	
    	public Node()
    	{
    		
    	}
    }
	
	public boolean checkTree(Node root)
	{
		Node root1 = checkTreeUtil(root);
		return root1.isHeightBalanced;
	}
	
	
	private Node checkTreeUtil(Node root)
	{
		if (root == null)
		{
			Node dummy = new Node();
			dummy.isHeightBalanced = true;
			dummy.max = 0;
			dummy.min = 0;
			return dummy;
		}
		if (root.left == null && root.right == null)
		{
	        root.min = 1;
	        root.max = 1;
	        return root;
		}
		
		Node x = checkTreeUtil(root.left);
		Node y = checkTreeUtil(root.right);
		
		if ( (x!= null && !x.isHeightBalanced ) || (y!=null&&!y.isHeightBalanced))
		{
			root.isHeightBalanced = false;
			return root;
		}
		else
		{
			int max1 = x.max;
			int min1 = x.min;
			
			int max2 = y.max;
			int min2 = y.min;
			
			int max = Math.max(max1, max2)+1;
			int min = Math.min(min1, min2)+1;
			
			root.max = max;
			root.min = min;
			
			if ( max <= 2*min)
			{
				
				root.isHeightBalanced = true;
				return root;
			}
			root.isHeightBalanced = false;
			return root;
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		 12                                        40
//	      \                                     /    \ 
//	       14                                 10      100    
//	         \                                        /  \
//	          16                                     60   150    
        Node root = new Node(12);
        root.right = new Node(14);
        root.right.right = new Node(16);
        
//        Node root = new Node(40);
//        root.left = new Node(10);
//        root.right = new Node(100);
//        root.right.right = new Node(150);
//        root.right.left = new Node(60);
                        
        boolean result = new CheckForHeightBalancedTree().checkTree(root);
        System.out.println(result);
	}

}
