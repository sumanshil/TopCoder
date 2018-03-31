package com.geeksforgeeks.array;

public class FindingMinimumInRotatedArray {

    public int findMin(int[] arr, int low, int high){
        if (low > high)
            return arr[0];
        if (low == high)
            return arr[low];
        
        int mid = (low+ high)/2;
        if (mid > 0 && arr[mid]< arr[mid-1] && arr[mid] < arr[mid+1])
            return arr[mid];
        
        if (arr[low] <= arr[mid] && arr[mid]  <= arr[high]){
            return findMin(arr, low, mid);
        } else if (arr[low] > arr[mid] && arr[mid] < arr[high]){
            return findMin(arr, low, mid);
        }  else {//{(arr[mid]> arr[low] && arr[mid]> arr[high] && arr[low]> arr[high])
            return findMin(arr, mid+1, high);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
      //  int[] arr = {5, 6, 1, 2, 3, 4};
      //  int[] arr = {1, 2, 3, 4};
      //  int[] arr = {1};
      //  int[] arr =  {1, 2};
      //  int[] arr = {2, 1};
      //  int[] arr =  {5, 6, 7, 1, 2, 3, 4};
      //  int[] arr = {1, 2, 3, 4, 5, 6, 7};
      //  int[] arr = {2, 3, 4, 5, 6, 7, 8, 1};
        int[] arr = {3, 4, 5, 1, 2};
        int result = new FindingMinimumInRotatedArray().findMin(arr, 0, arr.length-1);        
        System.out.println(result);
    }

}
