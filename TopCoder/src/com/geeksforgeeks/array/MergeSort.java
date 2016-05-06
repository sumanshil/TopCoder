package com.geeksforgeeks.array;

/**
 * Created by sshil on 2/27/2016.
 */
public class MergeSort {
	class Result{
		private int[] sortedArray;
		private int numberOfInversions;

		public int[] getSortedArray() {
			return sortedArray;
		}

		public void setSortedArray(int[] sortedArray) {
			this.sortedArray = sortedArray;
		}

		public int getNumberOfInversions() {
			return numberOfInversions;
		}

		public void setNumberOfInversions(int numberOfInversions) {
			this.numberOfInversions = numberOfInversions;
		}

		public Result(int[] arr, int result){
			this.sortedArray = arr;
			this.numberOfInversions = result;
		}
	}
    public void sort(int[] arr){
		Result result = mergeRecursive(arr, 0, arr.length-1);
	}

	private Result mergeRecursive(int[] arr, int start, int end) {
		if (start == end){
			int[] newArr = new int[]{arr[start]};
			return new Result(newArr, 0);
		}
		int mid = (start + end)/2;
		Result leftResult = mergeRecursive(arr, start, mid);
		Result rightResult = mergeRecursive(arr, mid+1, end);

		Result mergeResult = merge(leftResult, rightResult);
		return null;
	}

	private Result merge(Result leftResult, Result rightResult) {
		int[] newArr = new int[leftResult.sortedArray.length+ rightResult.sortedArray.length];

		return null;

	}


	public static void main(String[] args) {

	}
}
