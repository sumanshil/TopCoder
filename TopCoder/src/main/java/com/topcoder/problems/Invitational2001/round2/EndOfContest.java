package com.topcoder.problems.Invitational2001.round2;
//http://community.topcoder.com/stat?c=problem_statement&pm=178&rd=51
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class EndOfContest {

	static class IndexSorter implements Comparator<Contestant>
	{
		public int compare(Contestant o1, Contestant o2)
		{
			if ( o1.getOriginalIndex() < o2.getOriginalIndex() )
			{
				return -1;
			}
			else if ( o1.getOriginalIndex() > o2.getOriginalIndex() )
			{
				return 1;
			}
			return 0;
		}

		public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T> Comparator<T> nullsFirst(Comparator<? super T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Contestant> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Contestant> thenComparing(
				Comparator<? super Contestant> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<Contestant> thenComparing(
				Function<? super Contestant, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<Contestant> thenComparing(
				Function<? super Contestant, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Contestant> thenComparingDouble(
				ToDoubleFunction<? super Contestant> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Contestant> thenComparingInt(
				ToIntFunction<? super Contestant> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Contestant> thenComparingLong(
				ToLongFunction<? super Contestant> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	static class Contestant implements Comparable<Contestant>
	{
		private String name;
		private double earning;
		private int originalIndex;
		private int money;
		private int newIndex;
		
		public Contestant(String name, double earning, int originalIndex)
		{
			this.name = name;
			this.earning = earning;
			this.originalIndex = originalIndex;			
		}
		
		public int getMoney() 
		{
			return money;
		}


		public void setMoney(int money)
		{
			this.money = money;
		}
		
		public String getName()
		{
			return name;
		}


		public void setName(String name)
		{
			this.name = name;
		}


		public double getEarning()
		{
			return earning;
		}


		public void setEarning(double earning)
		{
			this.earning = earning;
		}


		public int getOriginalIndex()
		{
			return originalIndex;
		}


		public void setOriginalIndex(int originalIndex)
		{
			this.originalIndex = originalIndex;
		}


		public int compareTo(Contestant other)
		{
            if (other != null)
            {
            	if (this.earning > other.earning)
            	{
            		return -1;
            	}
            	else if (this.earning < other.earning)
            	{
            		return 1;
            	}
            	else
            	{
            		// earning is same. compare original index
            		if (this.originalIndex > other.originalIndex)
            		{
            			return 1;
            		}
            		else
            		{
            		    return -1;	
            		}
            	}
            }
			return 0;
		}
		
		
		public int getNewIndex()
		{
			return newIndex;
		}

		public void setNewIndex(int newIndex)
		{
			this.newIndex = newIndex;
		}

		public String toString()
		{
		    return this.name+": "+newIndex+", "+money;	
		}
	}
	
	public String[] calcEarnings(String[] scores, int[] money)
	{
		int index = 0;
		List<Contestant> list = new ArrayList<Contestant>();
		for(String str : scores)
		{
			String[] arr  = str.split(":");
			String name = arr[0];
			double earning = Double.parseDouble(arr[1].trim());
			list.add(new Contestant(name, earning, index));
			index++;
			
		}
		Collections.sort(list);
		
	    int startIndex = 0;
	    int count = 0;
	    double lastEarning = Double.MAX_VALUE;
	    index = 0;
        Contestant[] arr = (Contestant[])list.toArray(new Contestant[0]);
        count = 1;
        startIndex = 0;
        for(int i = 0 ; i < arr.length ; i++)
        {
        	 if (arr[i].getEarning() <=0)
        	 {
        		 break;
        	 }
             if (i+1 < arr.length)
             {
            	 if (arr[i].getEarning() == arr[i+1].getEarning())
            	 {
            		 count++;
            		 continue;
            	 }
            	 else if (arr[i].getEarning() != arr[i+1].getEarning())
            	 {
            	     int totalMoney = getTotalMoney(startIndex, count, money);
            		 int average = totalMoney/count;
 	                 for(int j = startIndex ; j < startIndex+count; j++)
 	                 {
 	                	list.get(j).setMoney(average);
 	                 }
 	                
 	                 startIndex = startIndex+count; 	              
 	                 count = 1;
            	 }            	 
             }
             else if (i == arr.length-1)
             {
            	 if (count == 1)
            	 {
            		 int totalMoney = getTotalMoney(startIndex, count, money);
            		 list.get(i).setMoney(totalMoney);
            	 }
            	 else
            	 {
            	     int totalMoney = getTotalMoney(startIndex, count, money);
            		 int average = totalMoney/count;
 	                 for(int j = startIndex ; j < startIndex+count; j++)
 	                 {
 	                	list.get(j).setMoney(average);
 	                 }            		 
            	 }
             }
        }
	    index = 1;
	    count = 1;
	    lastEarning = Double.MAX_VALUE;
	    for(Contestant contestant : list)
	    {
	        
	        if (lastEarning == Double.MAX_VALUE)
	        {
	        	contestant.setNewIndex(index);
	        	lastEarning = contestant.getEarning();
	        }
	        else if (lastEarning == contestant.getEarning())
	        {
	        	count++;
	        	contestant.setNewIndex(index);
	        }
	        else
	        {
	        	index= index+count;	        	
	        	contestant.setNewIndex(index);
	        	count = 1;
	        	lastEarning = contestant.getEarning();
	        }
	        
	    }
	    Collections.sort(list, new IndexSorter());
	    String[] strArr = new String[list.size()];
	    for(int i = 0 ; i < list.size() ; i++)
	    {
	    	strArr[i] = list.get(i).toString();
	    }
		return strArr;
	}
	
	private int getTotalMoney(int index, int count, int[] money) 
	{
		int sum = 0;
		while(index < money.length && count > 0)
		{
			sum+=money[index];
			index++;
			count--;
		}
		return sum;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String[] scores = {"buddy: 100.00",
//			    "carl: 0.00",
//		   };
//		int[] money = {10,20,30};
		
//	    String[] scores = new String[]
//		       {"ads: 550.34",
//			    "talub: 2102.98",
//			    "romana: 1123.21",
//			    "mike: -1000.00",
//			    "td: 1123.21",
//			    "dok: 1123.21",
//			    "dwarfsleepy: 812.12"};
//		int[] money = new int[] {300,
//			    		   150,
//			    		   75};
		
		String[] scores = {"a: 100.00",
			    "b: 100.00",
			    "c: 100.00",
			    "d: 100.00",
			    "e: 100.00",
			    "f: 100.00",
			    "g: 100.00"};
		
		int[] money = {1000,500};
		String[] result = new EndOfContest().calcEarnings(scores, money);
		
		for(String r : result)
		{
			System.out.println(r);
		}

	}

}
