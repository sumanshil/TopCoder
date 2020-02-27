package com.leetcode.tree;

public class SearchInSortedArrayDuplicate {

    public boolean search(int[] nums, int target) {
        int res = find(0, nums.length-1, nums, target);
        return res != -1;
    }


    int find(int start, int end, int[] arr, int element) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return arr[start] == element ? start : -1;
        }
        int mid = (start) + (end-start)/2;

        if (arr[mid] == element) {
            return mid;
        }

        if (arr[mid] < arr[end]) {
            if (arr[mid] <= element && element <= arr[end]) {
                return find(mid+1, end, arr, element);
            } else {
                return find(start, mid - 1, arr, element);
            }
        } else if (arr[mid] > arr[end]) {
            if (element > arr[end] && element < arr[mid] ) {
                return find(start, mid - 1, arr, element);
            } else {
                return find(mid + 1, end, arr, element);
            }
        } else {
            int index = find(start, mid-1, arr, element);
            if (index == -1) {
                index = find(mid+1, end, arr, element);
            }
            return index;
        }
    }
}
