package com.geeksforgeeks.tree;

import java.util.Iterator;
import java.util.Stack;

public class PrintAllRootToLeafIterative
{
	static class Node
	{
		int data;
		Node left;
		Node right;
		boolean isLeftCompleted;
		boolean isRightCompleted;
		Node(int data)
		{
			this.data = data;
		}
	}

//	public void print1(Node root)
//	{
//		Stack<Node> stack = new Stack<Node>();
//		stack.push(root);
//		while(!stack.isEmpty())
//		{
//			Node node = stack.peek();
//			boolean isRightTree = true;
//			while(node.left != null)
//			{
//				node = node.left;
//				stack.push(node);
//				isRightTree = false;
//			}
//			
//			Node currentTop = stack.peek();
//			if (!isRightTree &&
//				currentTop.right != null)
//			{
//				stack.push(currentTop.right);
//				isRightTree = true;
//			}
//			else
//			{
//				printStack(stack);
//				while(!stack.isEmpty()&&
//					  currentTop != null &&	
//					  currentTop.right == null)
//				{
//					stack.pop();
//					currentTop = stack.peek();
//					isRightTree = true;
//				}
//				if (!isRightTree &&
//					currentTop!= null &&
//					currentTop.right != null)
//				{
//					stack.push(currentTop.right);
//				}
//			}						
//		}
//		
//	}
	

	public void print(Node root)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty())
		{
			Node node = stack.peek();
			if (!node.isLeftCompleted
			    && node.left != null)
			{
				stack.push(node.left);
				node.isLeftCompleted = true;
			}
			else if (!node.isRightCompleted
				    && node.right != null)
			{
				stack.push(node.right);
				node.isRightCompleted = true;				
			}
			// leaf node
			else if (node.left == null
					 && node.right == null
					 && !node.isLeftCompleted)
			{
				node.isLeftCompleted = true;
				node.isRightCompleted = true;
				printStack(stack);
			}
			else
			{
			    stack.pop();
			}
	    }
	}
	
	
	private void printStack(Stack<Node> stack)
	{
		Iterator<Node> iterator = stack.iterator();
		while(iterator.hasNext())
		{
			Node node = iterator.next();
			System.out.print(node.data + "->");
		}
		System.out.println();
	}


	public static void main(String[] args)
	{
		Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);
        root.left.left.right = new Node(11);
        root.left.left.left.left = new Node(5);
        root.left.left.left.right = new Node(10);

        root.right = new Node(6);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);
        root.right.right.right.right = new Node(9);
        new PrintAllRootToLeafIterative().print(root);
	}

}
