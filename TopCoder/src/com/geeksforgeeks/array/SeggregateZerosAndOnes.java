package com.geeksforgeeks.array;

public class SeggregateZerosAndOnes {
    static int[] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int start = 0;
		int end = arr.length-1;
		
		while(start < end){
			while(arr[start] == 0){
				start++;
			}
			
			while(arr[end] == 1){
				end--;
			}
			if (start < end){
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
			}
		}

		for(int i : arr){
			System.out.print(i);
		}
	}

}
