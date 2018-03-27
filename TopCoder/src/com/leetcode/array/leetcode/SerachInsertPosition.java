package com.leetcode.array.leetcode;

//https://leetcode.com/problems/search-insert-position/description/
public class SerachInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length-1]) {
            return nums.length;
        }
        return recursive(nums, 0, nums.length-1, target);
    }

    private int recursive(int nums[], int low, int high, int target) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low)/2;
        if (nums[mid] == target) {
            return mid;
        }

        if (mid + 1 < nums.length && target > nums[mid] && target < nums[mid+1] ) {
            return mid+1;
        }

        if (nums[mid] < target) {
            return recursive(nums, mid, high, target);
        }
        return recursive(nums, low,mid, target);
    }

    public static void main(String[] args) {
        int arr[] = {1,3,5,6};
        int target = 7;
        int result = new SerachInsertPosition().searchInsert(arr, target);
        System.out.println(result);
    }
}
