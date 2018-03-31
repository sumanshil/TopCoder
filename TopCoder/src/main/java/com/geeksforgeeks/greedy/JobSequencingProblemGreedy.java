package com.geeksforgeeks.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobSequencingProblemGreedy
{
    static class Job implements Comparable<Job>
    {
    	String str;
    	int deadLine;
    	int profit;
    	
    	public Job(String str,
    			   int deadLine,
    			   int profit)
    	{
    		this.str = str;
    		this.deadLine = deadLine;
    		this.profit = profit;
    	}

		public int compareTo(Job o)
		{
			return (this.profit > o.profit ? -1 : 
				   (this.profit == o.profit ? 0 : 1) );
			
		}
    }
    
    public List<String> findOptimalSequence(Job[] list)
    {
    	Arrays.sort(list);
    	int maxDeadLine = Integer.MIN_VALUE;
    	
    	for(Job j : list)
    	{
    		if (j.deadLine > maxDeadLine)
    		{
    			maxDeadLine = j.deadLine;
    		}
    	}
    	
    	boolean[] isTaken = new boolean[maxDeadLine];
    	List<String> retVal = new ArrayList<String>();
    	for(Job j : list)
    	{
    		for(int i = 0 ; i < j.deadLine ; i++)
    		{
    			if (!isTaken[i])
    			{
    				isTaken[i] = true;
    				retVal.add(j.str);
    				break;
    			}
    		}
    	}
    	
    	return retVal;
    }
    
    
	public static void main(String[] args)
	{
       Job[] jobs = new Job[4];
       jobs[0] = new Job("a",2, 100);
       jobs[1] = new Job("b",1, 19);
       jobs[2] = new Job("c",2, 27);
       jobs[3] = new Job("d",1, 25);
       jobs[3] = new Job("e",3, 15);
       List<String> result = new JobSequencingProblemGreedy()
                 .findOptimalSequence(jobs);
       for(String str : result)
       {
    	   System.out.println(str);
       }
	}

}
