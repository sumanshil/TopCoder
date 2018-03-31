package com.topcoder.problems.round153;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1770&rd=4570
public class GasStation 
{
	
	
	static class Station implements Comparable<Station>
	{
		double distance;
		int cost;
        boolean visited;		
		public Station(int distance,
				       int cost)
		{
			this.distance = distance;
			this.cost  = cost;
		}

		public int compareTo(Station otherStation)
		{
		    if (this.distance < otherStation.distance)
		    {
		    	return -1;
		    }
		    else if (this.distance > otherStation.distance)
		    {
		    	return 1;
		    }
			return 0;
		}
		
	}
	
	public double tripCost(int[] dist,
			               int[] price,
			               int mpg,
			               int tankSize,
			               int tripLength)
	{
		// for each station . check how much petrol is left
		// check if there are any station which has less price
		// check if we can reach that station with current petrol\
		// buy whatever is needed to reach that
		// or that is also not sufficient get the next station which is cheaper
		// buy petrol for that also
        List<Station> listByDistance = new LinkedList<Station>();
        listByDistance.add(new Station(0, Integer.MAX_VALUE));
		for(int i = 0 ; i <dist.length ; i++ )
		{
			listByDistance.add(new Station(dist[i], price[i]));			
		}
		Collections.sort(listByDistance);
				
		if (tankSize * mpg > tripLength)
		{
			return 0;
		}
				
			
		
		
		return 0;
		
		
	}


	public double tripCost1(int[] dist,
            int[] price,
            int mpg,
            int tankSize,
            int tripLength)
	{
		double[] best = new double[tripLength];
		
		for(int i = 0 ; i < best.length ; i++)
		{
			best[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0 ; i < mpg*tankSize ; i++)
		{
			best[i]= 0;
		}
		
		for(int i= 0 ; i< dist.length ; i++)
		{
			for(int j = 0 ; j < mpg*tankSize ; j++)
			{
				if ( dist[i]+j < tripLength)
				{
					best[dist[i]+j] = Math.min(best[dist[i]+j], (double)price[i]/(double)mpg);					
				}
				
			}
		}
		
		double sum = 0;
		
		for(int i = 0 ; i < tripLength ; i++)
		{
			sum += best[i];
		}			
		return sum;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//        int[] distances = {100,100};
//        int[] prices = 	{1000,1500};
//        int   mpg       = 20;
//        int tankSize    = 10;
//        int tripLength  = 300;

          int[] distances = {300,450,525};
	      int[] prices = 	{1659,1529,1439};
	      int   mpg       = 20;
	      int tankSize    = 20;
	      int tripLength  = 600;

//        int[] distances = {300,125,450,525};
//        int[] prices = 	{1659,1729,1439,1529};
//        int   mpg       = 20;
//        int tankSize    = 20;
//        int tripLength  = 600;

//        int[] distances = {200};
//        int[] prices = 	{1000};
//        int   mpg       = 20;
//        int tankSize    = 20;
//        int tripLength  = 400;

//        int[] distances = {100,400};
//        int[] prices = 	{1549,1649};
//        int   mpg       = 25;
//        int tankSize    = 16;
//        int tripLength  = 600;

		
        double result = new GasStation().tripCost1(distances,
        		                  prices,
        		                  mpg,
        		                  tankSize,
        		                  tripLength);
        System.out.println(result);
	}

}
