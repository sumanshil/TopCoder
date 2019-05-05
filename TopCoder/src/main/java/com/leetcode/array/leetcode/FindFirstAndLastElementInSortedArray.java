package com.leetcode.array.leetcode;

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstAndLastElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstIndex = findFirstIndex(nums, target, 0, nums.length-1);
        if (firstIndex == -1) {
            return new int[]{-1, -1};
        }
        int secondndex = findSecondIndex(nums, target, firstIndex, nums.length-1);

        return new int[]{firstIndex, secondndex};
    }

    private int findSecondIndex(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low)/2;

        if (nums[mid] == target && (mid + 1 >= nums.length || nums[mid+1] != target)) {
            return mid;
        } else {
            if (nums[mid] <= target) {
                return findSecondIndex(nums, target, mid + 1, high);
            } else {
                return findSecondIndex(nums, target, low, mid - 1);
            }
        }

    }

    private int findFirstIndex(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low)/2;

        if (nums[mid] == target && (mid - 1 < 0 || nums[mid-1] != target)) {
            return mid;
        } else {
            if (nums[mid] < target) {
                return findFirstIndex(nums, target, mid+1, high);
            } else {
                return findFirstIndex(nums, target, low, mid-1);
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {2,2};
        int[] result = new FindFirstAndLastElementInSortedArray().searchRange(arr, 2);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
