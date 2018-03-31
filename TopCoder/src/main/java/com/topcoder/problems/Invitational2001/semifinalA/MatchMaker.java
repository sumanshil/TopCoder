package com.topcoder.problems.Invitational2001.semifinalA;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//http://community.topcoder.com/stat?c=problem_statement&pm=210&rd=53
public class MatchMaker {
	static class Mate implements Comparable<Mate>
	{
		String name;
		String gender;
		String desiredMate;
		int matches;
		int index;
		List<String> matchList = new LinkedList<String>();
		
		
		public Mate(String name,
				    String gender,
				    String desired,
				    int index,
				    List<String> list) 
		{
			this.name = name;
			this.gender = gender;
			this.desiredMate = desired;
			this.index = index;
			this.matchList = list;
		}


		public int compareTo(Mate mate) 
		{
			
			if (this.matches > mate.matches)
			{
				return -1;
			}
			else if (this.matches < mate.matches)
			{
				return 1;
			}
			else
			{
				// if they are same
				if (this.index > mate.index)
				{
					return 1;
				}
				else 
				{
					return 0;
				}
			}
		}
		
	}
	
	public String[] getBestMatches(String[] members, String currentUser, int sf)
	{
		Map<String, List<Mate>> map = new HashMap<String, List<Mate>>();
		Mate candidate=null;
		int candidateIndex = 0;
		for(String member : members)
		{
			String[] arr = member.split("\\s+");

			{				
				String name = arr[0];
				String gender = arr[1];
			    String desired = arr[2];
			    int length = arr.length;			    
				
			    List<String> list = new LinkedList<String>();
			    for(int j = 3 ; j < length; j++)
			    {
			    	list.add(arr[j]);
			    }
			    Mate newMate = new Mate(name, gender, desired, candidateIndex++ , list);
			    if (map.get(gender) == null)
			    {
			    	List<Mate> list1 = new LinkedList<Mate>();
			    	list1.add(newMate);
			    	map.put(gender, list1);
			    }
			    else
			    {
			    	List<Mate> result = map.get(gender);
			    	result.add(newMate);
			    }
			    
			    if (name.equals(currentUser))
			    {
			    	candidate = newMate;
			    }
			}
		}
		Set<Mate> set = new TreeSet<Mate>();
		if (candidate == null)
			return null;
		List<Mate> candidates = map.get(candidate.desiredMate);	
		for(Mate mate : candidates)
		{
			int count = 0;
			
			for(int i = 0 ; i < mate.matchList.size() ; i++)
			{
				if (candidate.matchList.get(i).equals(mate.matchList.get(i)))
				{
					count++;
				}
			}
			mate.matches = count;
			if (count >= sf && !currentUser.equals(mate.name))
			    set.add(mate);			
		}
		String[] arr = new String[set.size()];
		int index = 0;
		for(Mate mate : set)
		{
			arr[index++] = mate.name;
		}
		return arr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] arr = {"BETTY F M A A C C",
				 "TOM M F A D C A",
				 "SUE F M D D D D",
				 "ELLEN F M A A C A",
				 "JOE M F A A C A",
				 "ED M F A D D A",
				 "SALLY F M C D A B",
				 "MARGE F M A A C C"};
		String currentUser = "MARGE";
		int sf = 4;
		String[] res = new MatchMaker().getBestMatches(new String[]	{"SUE F M D A C B", "JOE M F D C C A", "GEORGE M F D A C B", "BOB M F D A C B", "ED M F D C B A"}, "SUE", 1	);
		for(String result : res)
		{
			System.out.println(result);
		}
		
		Lock lock = new ReentrantLock();
	}

}
;