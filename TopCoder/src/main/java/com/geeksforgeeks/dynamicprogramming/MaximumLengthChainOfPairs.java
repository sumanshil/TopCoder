package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 2/4/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
public class MaximumLengthChainOfPairs {

	static class Pair{
		private int lower;
        private	int higher;
		public Pair(int lower, int higher){
			this.lower = lower;
			this.higher = higher;
		}

		public String toString(){
			return this.lower +" "+this.higher;
		}
	}


	public void findMax(List<Pair> list) {
		int[] maxLength = new int[list.size()];
		maxLength[0] = 1;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0 ; i < list.size() ; i++) {
			List<Integer> list1 = new ArrayList<>();
			list1.add(i);
			map.put(i, list1);
		}
		for ( int i = 1 ; i < list.size() ; i++){
			maxLength[i] = Integer.MIN_VALUE;
		}
		for ( int i = 1 ; i < list.size() ; i++ ) {
			for ( int j = 0 ; j < i ; j++) {
				if (list.get(j).higher < list.get(i).lower){
					if (maxLength[j]+1 > maxLength[i]) {
						maxLength[i] = maxLength[j]+1;
						List<Integer> list2 = new ArrayList<>();
						list2.add(i);
						list2.addAll(map.get(j));
						map.put(i, list2);
					}
				}
			}
		}

		int maxCount = Integer.MIN_VALUE;
		int maxIndex = 0;
		for ( int i = 0 ; i < list.size() ; i++ ) {
			if (maxLength[i] > maxCount) {
				maxCount = maxLength[i];
				maxIndex = i;
			}
		}

		List<Integer> list2 = map.get(maxIndex);
		for (Integer integer : list2){
			System.out.print(list.get(integer)+" ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		List<Pair> list = new ArrayList<>();
		list.add(new Pair(5, 24));
		list.add(new Pair(39, 60));
		list.add(new Pair(15, 28));
		list.add(new Pair(27, 40));
		list.add(new Pair(50, 90));
		new MaximumLengthChainOfPairs().findMax(list);
	}
}
