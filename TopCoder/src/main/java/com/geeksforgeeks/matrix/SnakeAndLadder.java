package com.geeksforgeeks.matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//http://www.geeksforgeeks.org/snake-ladder-problem-2/
public class SnakeAndLadder
{
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static
	{
        map.put(3,22);
        map.put(5,8);
        map.put(11,26);
        map.put(17,4);
        map.put(19, 7);
        map.put(20, 29);
        map.put(27, 1);
	}
	
	static final int FINISH_POINT = 30;
	static final int START_POINT  = 1;
	
	static class LadderNode
	{
		int currentPoint;
		int noOfThrow;
		
		public LadderNode(int currentPoint,
				          int noOfThrow)
		{
			this.currentPoint = currentPoint;
			this.noOfThrow    = noOfThrow;
		}		
	}
	
	public int findMin()
	{
		LadderNode start = new LadderNode(1, 0);
		Queue<LadderNode> queue = new LinkedList<LadderNode>();
		queue.add(start);
		boolean[] visited = new boolean[50];
		visited[1] = true;
		while(!queue.isEmpty())
		{
			LadderNode currentNode = queue.remove();
			if (currentNode.currentPoint == FINISH_POINT)
			{
				return currentNode.noOfThrow;
			}
			visited[currentNode.currentPoint] = true;
			for(int i = 1 ; i <=6 ; i++)
			{
				int newPos = currentNode.currentPoint+i;
				if (visited[newPos])
				{
					continue;
				}
				//check we have reached a snake or ladder
				if (map.containsKey(newPos))
				{
					newPos = map.get(newPos);
				}
				queue.add(new LadderNode(newPos, currentNode.noOfThrow+1));
			}
			
		}
		// should not reach here
		return -1;
	}
	
	public static void main(String[] args)
	{
		int result = new SnakeAndLadder().findMin();
		System.out.println(result);
	}
}
