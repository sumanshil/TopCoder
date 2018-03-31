package com.geeksforgeeks.array;

public class FindMinDistanceBetweenTwoNumbers {
    private static int[] arr =  {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
    
    private static void minDistance(int x, int y){
    	int sum = x+y;
    	int prev = -1 ;
    	for(int i = 0 ; i < arr.length ; i++){
    		if (arr[i] == x || arr[i] ==y){
    			prev = i;
    			break;
    		}
    	}
    	int minDistance = Integer.MAX_VALUE;
    	for(int j = 0; j < arr.length; j++){
    		if (arr[j] != arr[prev] && (arr[j] == x  || arr[j] == y)){
    			if ((j - prev) < minDistance){
    				minDistance = (j - prev);
    				
    			}else{
    				prev = j;
    			}
    		}
    	}
    	
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
         new FindMinDistanceBetweenTwoNumbers().minDistance(5, 8);		
	}

}
