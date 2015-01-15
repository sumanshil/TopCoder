package com.geeksforgeeks.tree;

public class FindMaxRepeating {
	// Returns maximum repeating element in arr[0..n-1].
	// The array elements are in range from 0 to k-1
	public static void findMaxRepeating(int[] arr, int n, int k){
		for(int i = 0 ; i< k ; i++){
			int element = arr[i];
			if(element > k){
				element = element %k;
			}
			arr[element] =arr[element]+k; 
		}
		
		int max= Integer.MIN_VALUE;
		int maxIndex = 0;
		for(int i = 0 ; i < k ;i++){
			if (arr[i] > max){
				max = arr[i];
				maxIndex = i;
			}
		}
		int factor = max - (max%k);
		
		for(int j = 0 ; j < arr.length; j++){
			if ((arr[j]-(arr[j]%k)) == factor){
				System.out.println("max element"+j);
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int arr[] = {2, 3, 3, 5, 3, 4, 1, 7};
		int arr[] = {3,4,1,2,5,6,4,3};
	    findMaxRepeating(arr, arr.length, 8);	

	}

}
