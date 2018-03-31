package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/backtracking-to-find-all-subsets/
public class BackTrackingToFindAllSubsets {

    public void find (int arr[]) {
        taken = new boolean[arr.length];
        for (int i = 1 ; i <= arr.length ; i++) {
            int result[] = new int[i];
            recursive(result, arr, 0, 0);
        }
    }

    private boolean taken[] = null;
    private void recursive(int[] result, int[] arr, int resultIndex, int arrIndex) {
        if (resultIndex == result.length){
            printArr(result);
            return;
        }
        if (arrIndex == arr.length){
            return;
        }

        for ( int i = arrIndex ; i < arr.length ; i++){
            if (!taken[i]) {
                taken[i] = true;
                result[resultIndex] = arr[i];
                recursive(result, arr, resultIndex + 1, i + 1);
                taken[i] = false;
            }
        }
    }

    private void printArr(int[] result) {
        Arrays.stream(result).forEach(System.out::print);
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        new BackTrackingToFindAllSubsets().find(arr);
    }

}
