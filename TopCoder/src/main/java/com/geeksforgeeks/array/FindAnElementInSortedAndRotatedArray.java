package com.geeksforgeeks.array;

//http://www.geeksforgeeks.org/given-a-sorted-and-rotated-array-find-if-there-is-a-pair-with-a-given-sum/
public class FindAnElementInSortedAndRotatedArray {

    public void find(int arr[], int sum) {
        int pivotIndex = findPivot(arr, 0, arr.length-1);
        System.out.println(pivotIndex);
        int high = pivotIndex;
        int low = pivotIndex + 1 < arr.length ? pivotIndex+1  : 0;

        while (low != high) {
            if (arr[low] + arr[high] == sum){
                System.out.println("Result found at "+ arr[low] +" " + arr[high]);
                return;
            }

            if (arr[low] + arr[high] > sum){
                high--;
                if (high < 0){
                    high = arr.length-1;
                }
            } else {
                low ++;
                if (low >= arr.length) {
                    low = 0;
                }
            }
        }
    }

    private int findPivot(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high)/2;
        if (mid -1 >= 0 && mid +1 < arr.length) {
            if (arr[mid-1] < arr[mid] && arr[mid] > arr[mid+1]){
                return mid;
            }
        } else if (mid - 1 < 0 && mid + 1 < arr.length){
            if (arr[mid] > arr[mid+1]){
                return mid;
            }
        } else if (mid +1 >= arr.length){
            if (arr[mid] > arr[mid-1]) {
                return arr[mid];
            }
        }

        if (arr[mid] < arr[low]) {
            return findPivot(arr, low , mid);
        }
        return findPivot(arr, mid, high );
    }

    public static void main(String[] args) {
        int arr[] = {11, 15, 26, 38, 9, 10};
        new FindAnElementInSortedAndRotatedArray().find(arr, 45);
    }
}
