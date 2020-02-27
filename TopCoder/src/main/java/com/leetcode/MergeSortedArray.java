package com.leetcode;

//https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) {
            return;
        }
        if (m == 0) {
            int firstPointer = 0;
            int insertIndex = 0;
            while (firstPointer < n) {
                nums1[insertIndex++] = nums2[firstPointer++];
            }
            return;
        }
        int insertIndex = nums1.length - 1;
        int i = m-1;
        while ( i >= 0) {
            nums1[insertIndex--] = nums1[i--];
        }
        int firstPointer = 0;
        int secondPointer = nums1.length - m;
        insertIndex = 0;

        while (firstPointer < n && secondPointer < nums1.length) {
            if (nums1[secondPointer] < nums2[firstPointer]) {
                nums1[insertIndex++] = nums1[secondPointer++];
            } else {
                nums1[insertIndex++] = nums2[firstPointer++];
            }
        }

        while (firstPointer < n) {
            nums1[insertIndex++] = nums2[firstPointer++];
        }

        while (secondPointer < nums1.length) {
            nums1[insertIndex++] = nums1[secondPointer++];
        }
    }

    public static void main(String[] args) {
//        int[] arr1 = {4, 0, 0, 0, 0, 0};
//        int[] arr2 = {1, 2, 3, 5, 6};
//        int[] arr1 = {2, 5, 6, 0, 0, 0};
//        int[] arr2 = {1, 2, 3};
          int[] arr1 = {1, 2, 4, 5, 6, 0};
          int[] arr2 = {3};


        new MergeSortedArray().merge(arr1, 5, arr2, 1);

        for (int i : arr1) {
            System.out.println(i);
        }
    }
}
