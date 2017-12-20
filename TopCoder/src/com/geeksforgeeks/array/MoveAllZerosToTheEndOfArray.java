package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/move-zeroes-end-array-set-2-using-single-traversal/
public class MoveAllZerosToTheEndOfArray {

    public void move (int arr[]) {
        int zeroElementIndex = 0;
        int nonZeroElementIndex = 0;
        int index = 0;
        while (arr[index++] != 0 ){

        }
        index = index -1 ;
        zeroElementIndex = index;
        nonZeroElementIndex = index + 1;
        while (nonZeroElementIndex < arr.length) {
            while (nonZeroElementIndex < arr.length && arr[nonZeroElementIndex] == 0 ) {
                nonZeroElementIndex ++;
            }
            if (zeroElementIndex < arr.length && nonZeroElementIndex < arr.length
                && arr[zeroElementIndex] == 0 && arr[nonZeroElementIndex] != 0) {
                swap(arr, zeroElementIndex, nonZeroElementIndex);
            }
            while (zeroElementIndex < arr.length && arr[zeroElementIndex] != 0){
                zeroElementIndex++;
            }
         //   nonZeroElementIndex = zeroElementIndex + 1;
        }
    }

    private void swap(int[] arr, int zeroElementIndex, int nonZeroElementIndex) {
        int temp = arr[zeroElementIndex];
        arr[zeroElementIndex] = arr[nonZeroElementIndex];
        arr[nonZeroElementIndex] = temp;
    }


    public static void main(String[] args) {
        int arr[] = {1, 2, 0, 0, 0, 3, 6};
        new MoveAllZerosToTheEndOfArray().move(arr);
        Arrays.stream(arr).mapToObj(e -> e).map(e -> e+" ").forEach(System.out::print);
    }

}
