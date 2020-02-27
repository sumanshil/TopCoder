package com.leetcode;

//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
public class SerachInASortedRotatedArray {

    public int search(int[] nums, int target) {
        int found = getPivotIndex(nums,0, nums.length-1, target);
        return found;
    }

    private int getPivotIndex(int[] nums, int low, int high, int target) {
        if (low > high) {
            return -1;
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] <= nums[high]) {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                break;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1};
        int result = new SerachInASortedRotatedArray().search(arr, 1);
        System.out.println(result);
    }
}
