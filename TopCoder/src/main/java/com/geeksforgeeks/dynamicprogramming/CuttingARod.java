package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/28/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
public class CuttingARod {

	public void maxPrice(int[] arr){
		int[] maxPriceArr = new int[arr.length];
		for ( int i = 0 ; i < arr.length ; i++){
			maxPriceArr[i] = arr[i];
		}
		for ( int i = 2 ; i <= arr.length ; i++) {
			for ( int j = 1 ; j < i ; j++ ) {
				int remainingLength = i-j;
				if (arr[i-1] < (maxPriceArr[j-1]+ maxPriceArr[remainingLength-1])){
					maxPriceArr[i-1] = maxPriceArr[j-1]+ maxPriceArr[remainingLength-1];
				}
			}
		}
		System.out.println(maxPriceArr[maxPriceArr.length-1]);
	}
	public static void main(String[] args) {
		int[] arr = {3,5,8,9,10,17,17,20};
		new CuttingARod().maxPrice(arr);
	}
}
