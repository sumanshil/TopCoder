package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MergeOverlappingIntervals {

	static class Interval{
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public Interval(){
			
		}
		int start;
		int end;
	}
	
	private static Interval[] intervals = new Interval[4];
	static{
		intervals[0] = new Interval();
		intervals[0].start = 1;
		intervals[0].end = 3;
		
		intervals[1] = new Interval();
		intervals[1].start = 2;
		intervals[1].end = 4;
		
		intervals[2] = new Interval();
		intervals[2].start = 5;
		intervals[2].end = 7;
		
		intervals[3] = new Interval();
		intervals[3].start = 6;
		intervals[3].end = 8;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Interval> s = new Stack<Interval>();
		List<Interval> result = new ArrayList<Interval>();
		for(int i = 0 ; i < intervals.length; i++){
			if (!s.isEmpty()){
				Interval int1 = s.peek();
				if (intervals[i].start < int1.end){
					s.push(intervals[i]);
				} else {
					Interval int2 = s.pop();
					while(s.size() > 1){
						s.pop();
					}
					Interval int3 = s.pop();
					result.add(new Interval(int3.start,int2.end));
					s.push(intervals[i]);
				}
			} else {
				s.push(intervals[i]);
			}
		}
		if (!s.isEmpty()){
			Interval int2 = s.pop();
			while(s.size() > 1){
				s.pop();
			}
			Interval int3 = s.pop();
			result.add(new Interval(int3.start,int2.end));
		}
		for(Interval interval : result){
			System.out.println("{"+interval.start+","+interval.end+"}");
		}
	}

}
