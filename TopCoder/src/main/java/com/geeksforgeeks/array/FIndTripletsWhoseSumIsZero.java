package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
public class FIndTripletsWhoseSumIsZero {

	public void find (int[] arr){
		Arrays.sort(arr);
		for ( int i = 0 ; i < arr.length-2 ; i++) {
			int j = i + 1;
			int l = arr.length-1;
			while (j < l){
				int sum = arr[j] + arr[l];
				int diff = sum + arr[i];
				if (diff == 0){
					System.out.println("Triplet found "+ arr[i]+" : "+arr[j] +" : "+arr[l]);
					break;
				} else if (diff > 0) {
					l--;
				} else if ( diff < 0) {
					j++;
				}
			}
		}
	}
	
	public static void main(String[] args){
		// -3 -1 0 1 2
		int[] arr = {0, -1, 2, -3, 1};
		new FIndTripletsWhoseSumIsZero().find(arr);
	}
}
