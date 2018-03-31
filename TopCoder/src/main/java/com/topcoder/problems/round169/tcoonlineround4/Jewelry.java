package com.topcoder.problems.round169.tcoonlineround4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sshil on 12/30/2015.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1166&rd=4705
public class Jewelry {
	class SumIndexes{
		private Set<Integer> set;
		public SumIndexes(Set<Integer> list){
			this.set = list;
		}

		public boolean exists(int key){
			return set.contains(key);
		}

		public int getIndexCount(){
			return this.set.size();
		}
	}

	private long result = 0;
	public 	long howMany(int[] values) {
		for (int count = 1 ; count < values.length ;count++) {
			List<Integer> list = new ArrayList<>();
			countRecursive(count, 0, list, values);
		}
		return result;
	}

	private Map<Integer, List<SumIndexes>> sumMap = new HashMap<>();

	private void countRecursive(int count, int index, List<Integer> list, int[] values) {
		if (list.size() == count) {
			//for (Integer integer : list){
			//	sum += values[integer];
			//}
			int sum = list.stream().mapToInt(e->values[e]).sum();
			List<SumIndexes> sumIndexes = sumMap.get(sum);
			compareWithOtherPairs(values, sum, list, sumIndexes);
			return;
		}

		for ( int i = index ; i < values.length; i++){
			list.add(i);
			countRecursive(count, i+1, list, values);
			list.remove(list.size()-1);
		}
	}

	private void compareWithOtherPairs(int[] values, int sum, List<Integer> set, List<SumIndexes> sumIndexList) {
		long count = 0;
		if (sumIndexList == null) {
			SumIndexes newSumIndexes = new SumIndexes(new HashSet(set));
			List<SumIndexes> list = new ArrayList<>();
			list.add(newSumIndexes);
			sumMap.put(sum, list);
			return;
		}
		for (SumIndexes sumIndexes : sumIndexList){
			// we may check
			boolean found = true;
			for (Integer currentIndex : set){
				if ( sumIndexes.exists(currentIndex)){
					found = false;
					break;
				}
			}
			if (found) {
				List<Integer> bobsList = null;
				List<Integer> franksList = null;
				if (sumIndexes.set.size() < set.size()){
					bobsList = set;
					franksList = new ArrayList<>(sumIndexes.set);
				} else if (sumIndexes.set.size() > set.size()){
					bobsList = new ArrayList<>(sumIndexes.set);
					franksList = set;
				} else {
					bobsList = new ArrayList<>(sumIndexes.set);
					franksList = set;
				}
				if (bobsList != null && franksList != null) {
					if (!checkIfFranksListElementGreaterThanBobsElement(values, bobsList, franksList)) {
						found = false;
					}
				}

			}
			if (found){
				System.out.println("======================");
				for (Integer i : sumIndexes.set){
					System.out.print(values[i] +"("+i+") ");
				}
				System.out.print(" -> ");
				for (Integer i : set){
					System.out.print(values[i] +"("+i+") ");
				}
				System.out.println();
				count++;
				if (sumIndexes.set.size() == set.size()){
					count++;
				}
			}

		}
		Set newSet = new HashSet<>(set);
		sumIndexList.add(new SumIndexes(newSet));
		result+=count;
	}

	private boolean checkIfFranksListElementGreaterThanBobsElement(int[] values,
																   List<Integer> bobsList,
																   List<Integer> franksList) {
		for (Integer bob : bobsList){
			for (Integer frank : franksList){
				if (values[bob] > values[frank]){
					return  false;
				}
			}
		}
		return  true;
	}


	public static void main(String[] args) {
		int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
		long result = new Jewelry().howMany(input);
		System.out.println(result);
	}
}
