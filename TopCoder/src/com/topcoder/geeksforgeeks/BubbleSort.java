package com.topcoder.geeksforgeeks;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {9,8,7,5,4,3,2,1};
		
		int outerLoop = arr.length;
		while(outerLoop >=0){
			int innerLoop = 0;
			while(innerLoop < outerLoop-1){
				if (arr[innerLoop] > arr[innerLoop+1]){
					int temp = arr[innerLoop];
					arr[innerLoop] = arr[innerLoop+1];
					arr[innerLoop+1] = temp;
				}
				innerLoop++;
			}
			outerLoop--;
		}
		
		for(int i : arr){
			System.out.print(i +" ");
		}
	}

}
