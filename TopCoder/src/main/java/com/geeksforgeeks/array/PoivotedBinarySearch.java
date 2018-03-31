package com.geeksforgeeks.array;

public class PoivotedBinarySearch {

	static int[] arr = {3, 4, 5, 1, 2};
	
//	public static int pivotedBinarySearch(int low, int high, int element){
//		if (findPivot(low, high) == -1)
//			return binarySearch(low, high, element);
//		
//		int pivot = findPivot(low, high);
//		if (element == arr[pivot]){
//			return pivot;
//		}
//		
//		
//	}
	
	public static int findPivot(int low, int high){
		if (low > high)
			return -1;
		int mid = (low + high)/2;
		
		if (mid < high && arr[mid] > arr[mid+1]){
			return mid;
		} else if (arr[low] >= arr[mid]){
			return findPivot(low, mid-1);
		} else {
			return findPivot(mid+1, high);
		}
	}
	
	public static int binarySearch(int low, int high,int element){
		if (low > high)
			return -1;
		int mid = (low+high)/2;
		
		if (element == arr[mid]){
			return mid;
		}
		if (element < arr[mid]){
			return binarySearch(low, mid-1, element);			
		} else {
			return binarySearch(mid+1, high, element);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
