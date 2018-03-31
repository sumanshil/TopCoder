package com.geeksforgeeks.array;

//https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositive {

    public void find (int arr[]) {
        int positionToInsert = 0;
        int indexOfZeroOrNegative = 0;
        while (indexOfZeroOrNegative < arr.length) {
            if (arr[indexOfZeroOrNegative] <= 0 && indexOfZeroOrNegative != positionToInsert) {
                swap(arr, indexOfZeroOrNegative, positionToInsert);
                indexOfZeroOrNegative++;
                positionToInsert++;
            }
        }

        for (int j = positionToInsert + 1; j < arr.length ; j++) {
            if (arr[j]-1 < arr.length) {
                arr[arr[j] -1 ] = -Math.abs(arr[arr[j]]-1);
            }
        }
    }


    public static void main(String[] args) {

    }
}
