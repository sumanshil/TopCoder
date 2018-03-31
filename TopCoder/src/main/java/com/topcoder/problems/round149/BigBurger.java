package com.topcoder.problems.round149;

public class BigBurger {
    public int maxWait(int[] arrival, int[] service)
    {
    	int maxWait = 0;
    	int lastServiceTime = arrival[0]+service[0];
    	
    	for(int i = 1 ;  i < arrival.length ; i++)
    	{
    		int arrivalTime = arrival[i];
    		if (arrivalTime < lastServiceTime)
    		{
    			int wait = lastServiceTime-arrivalTime;
    			maxWait  = Math.max(wait, maxWait);
    			lastServiceTime = lastServiceTime + service[i];
    		}
    		else
    		{
    			lastServiceTime = arrival[i]+service[i];
    		}
    		
    	}
    	return maxWait;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        int result = new BigBurger().maxWait(new int[]{2,10,12}, new int[]{15,1,15});
        System.out.println(result);
	}

}
