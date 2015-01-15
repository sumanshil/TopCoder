package com.topcoder.problems.round39;

import java.util.HashMap;
import java.util.Map;

//http://community.topcoder.com/stat?c=problem_statement&pm=221&rd=4010

public class TimeDelay
{

	class Node
	{
		int index;
		int max;
		String name;
		public Node(int index)
		{
			this.index = index;
		}
		
	}
	
	Map<Integer, Node > map  = new HashMap<Integer, Node>();
	public int maxDelay(String[] nodes)
	{
		int index = 0;
		for(String str : nodes)
		{
            Node n = new Node(index);
            String[] arr = str.split(":");
            String name = arr[0];
            n.name = name;
            if (arr.length > 1)
            {            	
            	//int inputIndex = Integer.parseInt(arr[1]);
            	String input = arr[1];
            	String[] inputArr = input.split(",");
            	int max = Integer.MIN_VALUE;
            	for(String inputVal : inputArr)
            	{
            		int val = Integer.parseInt(inputVal);
            	    Node n1 = map.get(val);
            	    if (max < n1.max )
            	    {
            	    	max = n1.max;
            	    }
            	}
            	if ("x".equals(name))
            	    n.max = max;
            	else
            		n.max = max+1;
            	
            }
            else
            {
            	n.max = 0;
            }
            
		    map.put(index++, n); 			    
		}
		
		int max = Integer.MIN_VALUE;
		for(Map.Entry<Integer, Node> entry : map.entrySet())
		{
			
			if ("x".equals(entry.getValue().name))
			{
				if (entry.getValue().max > max)
				{
					max = entry.getValue().max;
				}
			}
		}
		System.out.println(max);
		return max;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    //String[] arr = {"i", "x:0"};
		//String[] arr = {"i", "n:0", "x:1"};
		//String[] arr = {"i", "i", "i", "n:0", "a:1,2,3", "x:4"};
		//String[] arr = {"i", "i", "i", "n:0", "a:1,2,3", "x:4", "x:0", "x:3"};
		//String[] arr = {"i", "x:0", "x:1"};
		//String[] arr = {"i", "i", "a:0,1", "x:2"}		;
		//String[] arr = {"i", "x:0", "n:1", "x:2", "n:3", "x:4", "n:5", "x:6"};
		//String[] arr = {"i", "n:0", "n:1", "n:2", "n:3", "n:4", "n:5", "n:6", "n:7", "n:8", "n:9", "n:10", "n:11", "x:12"};
		//String[] arr = {"i", "i", "n:0", "n:1", "a:0,3", "a:1,2", "o:4,5", "x:6"};
		//String[] arr = {"i", "i", "n:0", "n:1", "a:0,3", "a:1,2", "o:4,5", "a:0,1", "x:7", "x:6", "n:6", "x:10"};
		//String[] arr = {"i", "n:0", "x:1", "a:0,1,2", "x:3"};
		//String[] arr = {"i", "n:0", "i", "o:2,1", "a:3,1", "x:4", "x:3"}	;
		//String[] arr = {"i", "i", "a:0,1", "a:0,1", "a:2,3", "a:2,3", "a:4,5", "a:4,5", "a:6,7", "a:6,7", "a:8,9", "a:8,9", "a:10,11", "a:10,11", "a:12,13", "a:12,13", "a:14,15", "a:14,15", "a:16,17", "a:16,17", "a:18,19", "a:18,19", "a:20,21", "a:20,21", "a:22,23", "a:22,23", "a:24,25", "a:24,25", "a:26,27", "a:26,27", "a:28,29", "a:28,29", "a:30,31", "a:30,31", "a:32,33", "a:32,33", "a:34,35", "a:34,35", "a:36,37", "a:36,37", "a:38,39", "a:38,39", "a:40,41", "a:40,41", "a:42,43", "a:42,43", "a:44,45", "a:44,45", "a:46,47", "x:48"};
		//String[] arr = {"i", "n:0", "n:1", "n:2", "n:3", "n:4", "n:5", "n:6", "n:7", "n:8", "n:9", "n:10", "n:11", "a:12,11", "x:13"};
		//String[] arr = {"i", "i", "i", "a:0,1,2", "a:0,1,2", "a:0,1,2", "a:0,1,2", "a:0,1,2", "o:0,1,2", "n:1", "n:2", "n:3", "n:9", "a:0,1,2,10", "a:10,11,12,9", "a:10,11,12,13", "x:10", "x:1", "x:0", "x:12"};
		//String[] arr = {"i", "x:0", "n:0", "n:2", "n:3", "a:2,3"};
		//String[] arr = {"i", "x:0", "n:1", "x:2", "n:3", "x:4", "x:5"};
		//String[] arr = 	{"i", "i", "a:0,1", "x:2", "n:3", "n:4", "x:5"};
		//String[] arr = {"i", "i", "i", "i", "i", "n:2", "a:1,3", "o:3,4,1", "x:3", "a:1,2"};
	    //int result = new TimeDelay().maxDelay(arr);
		//String[] arr = {"i", "n:0", "x:1"};
		//String[] arr = {"i","n:0","a:0,1","x:2"};
		String[] arr = {"i","i","i","n:0","a:1,2,3","x:4","x:0","x:3"};
	   // new TimeDelay().getTruthTable(arr);
	}

}
