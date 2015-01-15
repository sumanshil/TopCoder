package com.topcoder.problems.round145;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

//http://community.topcoder.com/stat?c=problem_statement&pm=1677&rd=4530
public class Bonuses {
	
	public class Employee
	{
		public Employee(int index, int bonus, int point) 
		{
			this.originalIndex = index;
			this.bonus         = bonus;
			this.point         = point;
		}
		int originalIndex;
		int bonus;
		int point;		
	}
	
	static private class IndexComparator implements Comparator<Employee>
	{
		public int compare(Employee o1, Employee o2)
		{
            if (o1.originalIndex < o2.originalIndex)
            {
            	return -1;
            }
            else
            {
            	return 1;
            }			
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

		public Comparator<Employee> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparing(
				Comparator<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<Employee> thenComparing(
				Function<? super Employee, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<Employee> thenComparing(
				Function<? super Employee, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingDouble(
				ToDoubleFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingInt(
				ToIntFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingLong(
				ToLongFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}		
	}
	
	static private class PointComparator implements Comparator<Employee>
	{

		public int compare(Employee o1, Employee o2)
		{
			if (o1.point > o2.point)
			{
				return -1;
			}
			else if(o1.point < o2.point)
			{
				return 1;
			}
			else
			{
				if (o1.originalIndex < o2.originalIndex)
				{
					return -1;
				}
				else
				{
					return 1;
				}
				
			}
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

		public Comparator<Employee> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparing(
				Comparator<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<Employee> thenComparing(
				Function<? super Employee, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<Employee> thenComparing(
				Function<? super Employee, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingDouble(
				ToDoubleFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingInt(
				ToIntFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Employee> thenComparingLong(
				ToLongFunction<? super Employee> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
		
    public int[] getDivision(int[] points)
    {
    	Employee[] employees = new Employee[points.length];
    	int sum = 0;
        for(int i = 0 ; i < points.length ; i++)
        {
        	sum += points[i];
        }
        
        
    	int totalBonus = 0;
    	for(int j = 0 ; j < points.length ; j++)
    	{
    		int bonus = (int)Math.floor((points[j]*100)/sum);
    		employees[j] = new Employee(j, bonus, points[j]);
    		totalBonus += bonus;
    	}
    	Arrays.sort(employees, new PointComparator());
        totalBonus = 100-totalBonus;
        
        while(totalBonus > 0)
        {
        	if ( totalBonus < points.length)
        	{
        		for(int j = 0 ; j < totalBonus; j++)
        		{
        			employees[j].bonus = employees[j].bonus+1;
        		}
        		totalBonus = 0;
        	}
        	else
        	{
        		for(int j = 0 ; j < points.length ; j++)
        		{
        			employees[j].bonus = employees[j].bonus+1;
        		}
        		totalBonus -= points.length;
        	}
        }
        Arrays.sort(employees, new IndexComparator());
        int[] result = new int[points.length];
        for(int i = 0 ; i < result.length ; i++)
        {
        	result[i] = employees[i].bonus;
        }
        return result;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        int[] arr = {1,2,3,4,5};    
        int[] result = new Bonuses().getDivision(arr);
        for(int i = 0 ; i < result.length ; i++)
        {
        	System.out.println(result[i]);
        }
	}

}
