package com.topcoder.problems.Invitational2001.finalcomp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class UserListSorter
{

	static class Time implements Comparable<Time>
	{
		int hour;
		int min;
		public Time(String time)
		{
			String[] timeArr = time.split(":");
			this.hour = Integer.parseInt(timeArr[0]);
			this.min  = Integer.parseInt(timeArr[1]);
		}
		public int compareTo(Time o)
		{
			if (this.hour < o.hour)
			{
				return -1;
			}
			else if (this.hour > o.hour)
			{
				return 1;
			}
			else
			{
				if (this.min > o.min)
				{
					return -1;
				}
				else if (this.min > o.min)
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
		}
		
		public String toString()
		{
			return this.hour+":"+this.min;
		}
	}
	
	static class UserInfo	
	{
		String handle;
		int    rating;
		Time   time;
		
		public UserInfo(String handle,
				        int    rating,
				        String time)
		{
			this.handle = handle;
			this.rating = rating;
			this.time   = new Time(time);
		}
		
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append(this.handle+"\n");
			sb.append(this.rating+"\n");
			sb.append(this.time+"\n");
			return sb.toString();
		}
	}
	
	static abstract class TopComparator implements Comparator<UserInfo>
	{
		int order;
		public TopComparator(int order)
		{
			this.order = order;
		}
		
	}
	
	static class HandleComparator extends TopComparator
	{

		public HandleComparator(int order)
		{
			super(order);			
		}

		public int compare(UserInfo o1, UserInfo o2) 
		{
			if (o1.handle.equals(o2.handle))
			{
				return 0;
			}
			return order == 1 ? (o1.handle.compareTo(o2.handle))
					          : (o2.handle.compareTo(o1.handle));
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

		public Comparator<UserInfo> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparing(
				Comparator<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingDouble(
				ToDoubleFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingInt(
				ToIntFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingLong(
				ToLongFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	static class RatingComparator extends TopComparator
	{

		public RatingComparator(int order)
		{
			super(order);
		}

		public int compare(UserInfo o1, UserInfo o2) {
			if (o1.rating == o2.rating)
				return 0;
			return order == 1 ? (o1.rating < o2.rating ? -1 : 1) :(o1.rating < o2.rating ? 1 : -1);
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

		public Comparator<UserInfo> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparing(
				Comparator<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingDouble(
				ToDoubleFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingInt(
				ToIntFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingLong(
				ToLongFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	static class TimeComparator extends TopComparator
	{

		public TimeComparator(int order)
		{
			super(order);
		}

		public int compare(UserInfo o1, UserInfo o2) {
			if (o1.time.compareTo(o2.time) == 0)
				return 0;
			return order == 1 ? (o1.time.compareTo(o2.time)) :(o2.time.compareTo(o1.time));
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

		public Comparator<UserInfo> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparing(
				Comparator<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<UserInfo> thenComparing(
				Function<? super UserInfo, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingDouble(
				ToDoubleFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingInt(
				ToIntFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<UserInfo> thenComparingLong(
				ToLongFunction<? super UserInfo> arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public String[] sortUsersBy	(String[] userInfo,
			                     int sortBy,
			                     int order)
	{
		List<UserInfo> list = new ArrayList<UserInfo>();
		for(String info : userInfo)
		{
			String[] arr = info.split(" ");
			list.add(new UserInfo(arr[0],Integer.parseInt(arr[1]),arr[2]));
		}
		if (sortBy == 1)
		{
			Comparator<UserInfo> comparator = new HandleComparator(order);
			Collections.sort(list, comparator);
		}
		else if (sortBy == 2)
		{
			Comparator<UserInfo> comparator = new RatingComparator(order);
			Collections.sort(list, comparator);			
		}
		else 
		{
			Comparator<UserInfo> comparator = new TimeComparator(order);
			Collections.sort(list, comparator);			
		}
		List<String> retVal = new ArrayList<String>();
		for(UserInfo user : list)
		{
		    retVal.add(user.handle);
		}
		return (String[])retVal.toArray(new String[0]);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    String[] result = new UserListSorter().sortUsersBy(new String[]	{"sdfas 34 01:03", "asllsdf 23 03:28", "owkkdsf 13 04:12", "aslwl 1 03:11", "lajwpod 3 16:23", "lalilsd 342 12:59"}, 3, 0);
	    for(int i =0 ; i < result.length ; i++)
	    {
	    	System.out.println(result[i]);
	    }

	}

}
