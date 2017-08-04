package com.leetcode.array;

//https://leetcode.com/problems/median-of-two-sorted-arrays/tabs/description
public class MedianOfTwoSortedArrays {

    public void findMedian(int[] arr1, int[] arr2) {
        int lower;
        int upper;

        if ( (arr1.length + arr2.length) % 2 == 0){
            lower = (arr1.length + arr2.length) / 2 ;
            upper = (arr1.length + arr2.length) / 2 - 1;
        } else {
            lower = upper = (arr1.length + arr2.length) / 2 ;
        }
        int index1 = 0;
        int index2 = 0;
        int currentCount = 0;
        double result;
        while (true){
            if (lower == upper && lower == currentCount){
                result = arr1[index1] < arr2[index2] ? arr1[index1] : arr2[index2];
                break;
            }
            if ( lower != upper && (currentCount == lower-1)){
                result = (arr1[index1] + arr2[index2])/2;
                break;
            }
            if (arr1[index1] < arr2[index2]){
                index1++;
                currentCount++;
            } else if (arr2[index2] < arr1[index1]){
                index2++;
                currentCount++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr1 = {900};
        int[] arr2 = {5, 8 , 10 , 20};
        new MedianOfTwoSortedArrays().findMedian(arr1, arr2);
    }
}
