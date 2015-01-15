package com.topcoder.geeksforgeeks;

public class CheckForPair {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {-8, 1, 4, 6, 10, 45};
		int sum = 16;
        for(int i = 0 ; i < arr.length; i++){
        	int l = i;
        	int r = arr.length-1;
        	while(l < r){
        		if (arr[l]+ arr[r] == sum){
        			System.out.println("Sum found at indexes "+l +" "+r);
        			l++;
        			r--;
        		}else if (arr[l]+ arr[r] < sum){
        			l++;
        		}else if (arr[l]+ arr[r] > sum){
        			r--;
        		}
        	}
        	
        }
	}

}
