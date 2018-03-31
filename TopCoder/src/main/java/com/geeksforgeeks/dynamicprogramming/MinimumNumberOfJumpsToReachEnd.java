package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/17/2016.
 */
//http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
public class MinimumNumberOfJumpsToReachEnd {

	public int minJumps(int[] arr){
		int result = minJumpsDynamic(arr);
		return result;
	}

	private int minJumpsRecursive(int[] arr, int index) {
		if (index >= arr.length-1){
			return 0;
		}
		int minNumberOfJumps = Integer.MAX_VALUE;
		for ( int i = index+1 ; i <= (index+arr[index]) ; i++) {
			int jumps = minJumpsRecursive(arr, i);
			if (jumps+1 < minNumberOfJumps){
				minNumberOfJumps = jumps+1;
			}
		}
		return minNumberOfJumps;
	}

	private int minJumpsDynamic(int[] arr) {
		int[] minJumpArray = new int[arr.length];
		minJumpArray[minJumpArray.length-1] = 0;

		for ( int i = minJumpArray.length-2 ; i>=0 ; i--){
			int maxIndexToJump = i + arr[i];
			if (maxIndexToJump >= arr.length-1){
				minJumpArray[i] = 1;
			} else {
				int minJump = Integer.MAX_VALUE;
				for ( int j = i+1 ; j <= maxIndexToJump ; j++) {
					if (minJumpArray[j]+1 < minJump){
						minJump = minJumpArray[j]+1;
					}
				}
				minJumpArray[i] = minJump;
			}
		}
		return minJumpArray[0];
	}
	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

		int result = new MinimumNumberOfJumpsToReachEnd().minJumps(arr);
		System.out.println(result);
	}
}
