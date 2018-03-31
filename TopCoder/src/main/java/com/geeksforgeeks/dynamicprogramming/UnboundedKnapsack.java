package com.geeksforgeeks.dynamicprogramming;

public class UnboundedKnapsack {

	public void find(int[] val, int[] weight, int targetWeight) {
		int result = findRecursive(val, weight, targetWeight, 0 , 0,  0);
		System.out.println(result);
	}
	
    private int findRecursive(int[] val,
    		                  int[] weight,
    		                  int targetWeight,
    		                  int currentWeight,
    		                  int currentVal,
    		                  int currentIndex) {
    	if(currentWeight == targetWeight) {
    		return currentVal;
    	}
    	
    	if (currentWeight > targetWeight) {
    		return Integer.MIN_VALUE;
    	}
    	
    	if ( currentIndex >= val.length) {
    		if( currentWeight == targetWeight) {
    			return currentVal;
    		} else {
    			return Integer.MIN_VALUE;
    		}
    	}
    	
    	int with = findRecursive(val,
                weight,
                targetWeight,
                currentWeight+weight[currentIndex],
                currentVal+val[currentIndex],
                currentIndex);
    	
    	int without = findRecursive(val,
                weight,
                targetWeight,
                currentWeight,
                currentVal,
                currentIndex+1);
    	return Math.max(with, without);
	}

	public static void main(String[] args){
        //int[] val = {1, 30};
        //int[] weight = {1, 50};
        //int targetWeight = 100;
		int[] val = {10, 40, 50, 70};
		int[] weight = {1, 3, 4, 5};
		int targetWeight = 8;
        new UnboundedKnapsack().find(val, weight, targetWeight);
    }
}
