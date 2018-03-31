package com.topcoder.problems.round20;

import java.util.ArrayList;
import java.util.List;
//http://community.topcoder.com/stat?c=problem_statement&pm=135&rd=3020
public class Reps {

	List<Float> list1 = new ArrayList<Float>();
	List<Float> diffs = new ArrayList<Float>();
	List<Integer> repsCount = new ArrayList<Integer>();
	static List<Integer> result = new ArrayList<Integer>();
	float minDiff = Float.MAX_VALUE;
	public int[] representatives(int[] counts, int numRepresentatives){
	    float sum = 0;
	    for(int i : counts){
	    	sum+=i;
	    }
	    int index = 0;
	    for(int i : counts){
	    	list1.add(index++,((float)numRepresentatives)*((float)i/(float)sum));
	    }
	    
	    utility(numRepresentatives, 0, numRepresentatives, counts.length);
	    Integer[] intarr = (Integer[])result.toArray(new Integer[0]);
	    int[]     arr    = new int[intarr.length];
	    for(int i = 0 ; i < intarr.length ; i++)
	    {
	    	arr[i] = intarr[i];
	    }
	    return arr;
	}
	private void utility(int totalReps, int index, int numRepresentatives, int length) {
		if (index >= length){
			float totalSum = 0;
			for(float f : diffs){
				totalSum += f;
			}
			if (totalSum < minDiff){
				result = new ArrayList<Integer>();
				for(int i = 0 ; i < repsCount.size() ; i++){
					result.add(i, repsCount.get(i));
				}
				minDiff = totalSum;
			}
			//repsCount  = new ArrayList<Integer>();
			//diffs = new ArrayList<Float>();
			return;
		}
		if (index < length -1 ){
			for(int j = numRepresentatives ; j >= 0 ; j--){
				float value = list1.get(index);
				float diff = Math.abs(value-j);
				diffs.add(index, diff);
				repsCount.add(index,j);
				utility(totalReps, index+1, numRepresentatives-j, length);
				diffs.remove(index);
				repsCount.remove(index);
			}
		} else if (index == length -1){
			int j = numRepresentatives;
			float value = list1.get(index);
			float diff = Math.abs(value-j);
			diffs.add(index, diff);
			repsCount.add(index,j);
			utility(totalReps, index+1, numRepresentatives-j, length);			
			diffs.remove(index);
			repsCount.remove(index);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int[] arr =   	 	{3, 6, 9};
        int num =  5;
        new Reps().representatives(arr, num);
        for(int i : result){
        	System.out.println(i);
        }
	}

}
