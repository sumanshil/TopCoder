package com.topcoder.problems.round159;

//http://community.topcoder.com/stat?c=problem_statement&pm=1784&rd=4600
public class ThePriceIsRight
{
    public int[] howManyReveals(int[] prices)
    {
    	int[] maxArray = new int[prices.length];
    	int[] maxFrequency = new int[prices.length];
    	for(int i =0 ; i < prices.length ; i++)
    	{
    		maxArray[i] = 1;
    		maxFrequency[i] = 1;
    	}
    	int best = 1;
    	for(int i = 1 ; i < prices.length ; i++)
    	{
    		for(int j = 0 ; j < i ; j++)
    		{
    			if (prices[j] < prices[i])
    			{
    				if (maxArray[j]+1 > maxArray[i])
    				{
    					maxArray[i] = maxArray[j]+1;
    					maxFrequency[i] = maxFrequency[j];
    					best = Math.max(best,maxArray[i]);
    				}
    				else if (maxArray[j]+1 == maxArray[i])
    				{
    					maxFrequency[i] = maxFrequency[i] +maxFrequency[j];
    				}
    			}
    		}
    	}
    		
    	int maxElementFrequency = 0;
    	
    	for(int i = 0 ; i < maxArray.length ; i++)
    	{
    		if (maxArray[i] == best)
    		{
    			maxElementFrequency += maxFrequency[i];
    		}
    	}
    	int[] result = new int[2];
    	result[0] = best;
    	result[1] = maxElementFrequency;
    	return result;
    }
    
    public static void main(String[] args)
	{
    	int[] prices = {882758, 805748, 31612, 928187, 337135, 343821, 886260, 817902, 883689, 100896, 459199, 507098, 142520, 657587, 125599, 197312, 963317, 87565, 487819, 770332, 648263, 671497, 222362, 617367, 879535, 386129, 674427, 795742, 496226, 76255, 341426, 311972, 78951, 132359, 965988, 635747, 895169, 442989, 819252, 386246};
		int[] result = new ThePriceIsRight().howManyReveals(prices);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
