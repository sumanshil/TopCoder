package com.geeksforgeeks.recursion;

public class PrintAllCombinations {

	private static int[] arr = {1, 2, 3, 4};
	
	public static void print(int current, int next){
		if (next == arr.length-1){
			return;
		}
		print(current, ++next);
		System.out.println("{"+arr[current]+","+arr[next]+"}");
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0 ; i < arr.length-1 ; i++){
			print(i, i);
		}

	}

}
