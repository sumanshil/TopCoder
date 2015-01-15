package com.geeksforgeeks.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
public class PrintNodesInTopViewBinaryTree 
{
	static class Node
	{
		public Node(int i) 
		{
		    this.data = i;	
		}
		int data;
		Node left;
		Node right;
	}

	static class QueueNode
	{
		public QueueNode(Node data, int verticalLevel) 
		{
		    this.data = data;
		    this.level = verticalLevel;		
		}
		Node data;
		int level;
	}
	Map<Integer, Node> map = new HashMap<Integer, Node>();
	
	public void print(Node root)
	{
		recursive(root, 0);
		int min = Integer.MAX_VALUE;
		
		for(Map.Entry<Integer, Node> entry : map.entrySet())
		{
			if (entry.getKey() < min)
			{
				min = entry.getKey();
			}
		}
		
		int diff = Math.abs(min);
		
		int[] res = new int[map.entrySet().size()];
		for(Map.Entry<Integer, Node> entry : map.entrySet())
		{
			res[entry.getKey()+diff] = entry.getValue().data;
		}
		
   		for(int i = 0 ; i < res.length ; i++)
   		{
   			System.out.println(res[i]);
   		}
		
	}
	
	private void recursive(Node root, 
			               int verticalLevel) 
	{
		if (root == null)
		{
			return;
		}
		
		recursive(root.left, verticalLevel-1);
		recursive(root.right, verticalLevel+1);
		
		map.put(verticalLevel, root);
	}

	public void printDfs(Node root)
	{
		printLevelOrder(root,
				          0);
	}

	private int minLeftDepthLevel = Integer.MAX_VALUE;
	private int maxRightDepthLevel = Integer.MIN_VALUE;
	private void printLevelOrder(Node root,
			                       int currentVerticalDepth)
	{
        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.add(new QueueNode(root, 0));
        int leftMostLevel = 0;
        int rightMostLevel = 0; 
        while(!queue.isEmpty())
        {
        	QueueNode node = queue.remove();
        	if (node.level < leftMostLevel)
        	{
        		System.out.println(node.data.data);
        		leftMostLevel = node.level;
        	}
        	else if (node.level > rightMostLevel)
        	{
        		System.out.println(node.data.data);
        		rightMostLevel = node.level;
        	}
        	else if (node.level == leftMostLevel
        			&& node.level == rightMostLevel)
        	{
        		System.out.println(node.data.data);
        	}
        	if (isNotNull(node.data.left))
        	{
        		queue.add(new QueueNode(node.data.left, node.level-1));
        	}
        	
        	if (isNotNull(node.data.right))
        	{
        		queue.add(new QueueNode(node.data.right, node.level+1));
        	}
        }
        
	}

	private boolean isNotNull(Node node)
	{		
		return node != null;
	}

	public static void main(String[] args) 
	{
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		
		root.left.right = new Node(4);
		root.left.right.right = new Node(5);
		root.left.right.right.right = new Node(6);
		new PrintNodesInTopViewBinaryTree().printDfs(root);

	}

}
