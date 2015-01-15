package com.topcoder.problems.round153;
//http://community.topcoder.com/stat?c=problem_statement&pm=1772&rd=4570
public class Inventory
{
	public int monthlyOrder(int[] sales, int[] daysAvailable)
	{
		double totalSales = 0;
		double totalMonths  = 0;
		
		for(int i = 0 ; i < sales.length ; i++)
		{
			if (daysAvailable[i] > 0)
			{
				totalSales += ((double)sales[i]/(double)daysAvailable[i])*30;
				totalMonths += 1;
			}
		}
		int result = (int)Math.ceil((totalSales/totalMonths));		
		return result;
	}
	
	public static void main(String[] args)
	{
		int[] sales = {1115,7264,3206,6868,7301};
		int[] daysAvailable = {1,3,9,4,18};
		int result = new Inventory().monthlyOrder(sales, daysAvailable);
		System.out.println(result);
	}
}
