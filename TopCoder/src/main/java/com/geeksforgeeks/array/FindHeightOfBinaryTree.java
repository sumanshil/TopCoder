package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/
public class FindHeightOfBinaryTree
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
	
	Map<Integer, Integer[]> arrIndexToArrValues = new HashMap<Integer, Integer[]>();
	
	public int calculateHeight(int[] arr) throws Exception
	{
		Node root  = createTree(arr);
		int height = getHeight(root); 
		return height;
	}
	
	private int getHeight(Node root)
	{
		if (root == null)
		    return 0;
		
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		return (leftHeight > rightHeight )? leftHeight+1 : rightHeight+1;
	}

	private Node createTree(int[] arr) throws Exception
	{
		populateMap(arr);
		Integer rootIndex = findRootIndex(arr);
		if (rootIndex == -1)
		{
			throw new Exception("Root index can not be found");
		}
		Node node = createTreeRecursive(rootIndex);
		return node;
	}
	
	private Node createTreeRecursive(Integer rootIndex)
	{
		if (rootIndex == null)
		{
			return null;
		}

		if (rootIndex == -1)
		{
			return null;
		}
		Node root = new Node(rootIndex);
		Integer[] values = arrIndexToArrValues.get(rootIndex);
		if (values != null)
		{
			root.left  = createTreeRecursive(values[0]);
			root.right = createTreeRecursive(values[1]);
		}
		else
		{
			root.left = createTreeRecursive(null);
		}
		return root;
	}

	private int findRootIndex(int[] arr)
	{
		for(int arrIndex = 0 ; arrIndex < arr.length ; arrIndex++)
		{
			if (arr[arrIndex] == -1)
			{
				return arrIndex;
			}
		}
		return -1;
	}

	// key is the array index
	// values are the array values
	private void populateMap(int[] arr)
	{
		for(int arrIndex = 0 ; arrIndex < arr.length ; arrIndex++)
		{
			int key = arr[arrIndex];
			Integer[] values = arrIndexToArrValues.get(key);
			if (values == null)
			{
				values = new Integer[2];
				values[0] = new Integer(-1);
				values[1] = new Integer(-1);
			}
			if (values[0] == -1)
			{
				values[0] = new Integer(arrIndex);
			}
			else if (values[1] == -1)
			{
				values[1] = new Integer(arrIndex);
			}
			arrIndexToArrValues.put(key, values);
		}		
	}
	
	public int calculateHeight1(int[] arr)
	{		
		int[] depth = new int[arr.length];
		setDepthArrayInitiatialValues(depth);		
		for(int index = 0 ; index < arr.length ; index++)
		{
			int parentIndex = arr[index];
			int childIndex = index;
			while(true)
			{
				if (parentIndex == -1)
				{
					break;
				}
				int parentDepth = depth[parentIndex];
				int newDepth = depth[childIndex]+1;
				if (parentDepth <= newDepth)
				{
					depth[parentIndex] = newDepth;
					// we are going one level up in tree
					childIndex = parentIndex;
					parentIndex = arr[parentIndex];					
				}
				else
				{
					break;
				}
			}
		}
		
		int rootHeight = getRootHeight(arr,
				                       depth);
		
		return rootHeight;
	}
	
	private int getRootHeight(int[] arr, int[] depth)
	{
		for(int index = 0 ; index < arr.length ; index++)
		{
			if (arr[index] == -1)
			{
				return depth[index];
			}
		}
		return 0;
	}

	// set depth array initial values to 1
	private void setDepthArrayInitiatialValues(int[] depth)
	{
		for(int i = 0 ; i < depth.length ; i++)
		{
			depth[i] = 1;
		}		
	}
	
	private void fillDepth(int[] parent,
			              int childIndex,
			              int[] depth)
	{
		if (depth[childIndex] > 0)
			return;
		
		if (parent[childIndex] == -1)
		{
			depth[childIndex] = 1;
			return;
		}
			
		
		int parentIndex = parent[childIndex];
		if (parent[childIndex] != -1)
		{
			fillDepth(parent,
					  parentIndex,
					  depth);
		}
		
		depth[childIndex] = depth[parentIndex]+1;
	}
	
	
	public int calculateHeight2(int[] arr)
	{
		int[] depth = new int[arr.length];
		
		for(int i = 0 ; i < arr.length ; i++)
		{
			fillDepth(arr,
					  i,
					  depth);
		}
		
		int maxDepth = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < depth.length ; i++)
		{
			if (depth[i] > maxDepth)
			{
				maxDepth = depth[i];
			}
		}
		return maxDepth;
	}
	
	public static void main(String[] args) throws Exception
	{		
        int[] arr = {-1, 0, 0, 1, 1, 3, 5};
	//	int[] arr = {1, 5, 5, 2, 2, -1, 3};
        int result = new FindHeightOfBinaryTree().calculateHeight2(arr);
        System.out.println(result);
	}

}
