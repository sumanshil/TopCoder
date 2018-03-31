package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/
public class FindHeightFromParentTree
{

	public int findDepth(int[] parent)
	{
		int[] depth = new int[parent.length];
		for (int i = 0 ; i < parent.length ; i++)
		{
			// parent node. hence depth 1
			if (parent[i] == -1)
			{
				depth[i] = 1;
				break;
			}
		}
		
		for(int i = 0 ; i < parent.length ; i++)
		{
			findDepthRecursive(i,
					           depth,
			                   parent);			
		}
		
		int maxDepth = Integer.MIN_VALUE;
		for(int d : depth)
		{
			if (d > maxDepth)
			{
				maxDepth = d;
			}
		}
		
		return maxDepth;
	}
	
	
	private void findDepthRecursive(int currentIndex,
			                        int[] depth,
			                        int[] parent)
	{
		if (depth[currentIndex] > 0)
		{
			return ;
		}
		
		findDepthRecursive(parent[currentIndex],
				           depth,
				           parent);
		depth[currentIndex] = depth[parent[currentIndex]]+1;		
	}


	public static void main(String[] args)
	{
		int[] parent =  {-1, 0, 0, 1, 1, 3, 5};
		int result = new FindHeightFromParentTree().findDepth(parent);
		System.out.println(result);

	}

}
