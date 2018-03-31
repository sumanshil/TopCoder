package com.geeksforgeeks.dynamicprogramming;

public class LongestIncreasingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr=  { 10, 22, 9, 33, 21, 50, 41, 60, 80 } ;
		int[] lis = new int[arr.length];
		lis[0] = 1;
		
		for(int i = 1 ; i <arr.length;i++){
			for(int j = 0; j < i;j++){
				if (arr[j] < arr[i]){
					int temp = lis[j]+1;
					if (lis[j] < temp){
						lis[j] = temp;
					}
				}
			}
		}
		
		

	}

}
