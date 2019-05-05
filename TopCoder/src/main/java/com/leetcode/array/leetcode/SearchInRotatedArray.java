package com.leetcode.array.leetcode;

//https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedArray {

    
    public int search(int[] nums, int target) {

        int result = searchRecursive(nums, 0, nums.length - 1, target);
        return result;
    }

    private int searchRecursive(int[] nums, int low, int high, int target) {
        if (low > high) {
            return  -1;
        }
        if (low == high) {
            if (nums[low] == target) {
                return low;
            }
            return -1;
        }


        if (low + 1 == high) {
            if (nums[low] == target) {
                return low;
            } else if (nums[high] == target) {
                return high;
            }
        }

        int mid = (low + high)/2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[low] >= nums[mid] && target <= nums[mid] && target <= nums[low]) {
            // pivot is in left
            return searchRecursive(nums, low, mid, target);
        } else if (nums[low] >= nums[mid] && target >= nums[mid] && target >= nums[low]) {
            // pivot is in left
            return searchRecursive(nums, low, mid, target);
        } else if (nums[low] < nums[mid] && target <= nums[mid] && target >= nums[low]) {
            return searchRecursive(nums, low, mid, target);
        } else {
            return searchRecursive(nums, mid + 1, high, target);
        }

    }


    public static void main(String[] args) {
        int nums[] = {5, 1, 3};
        int target = 3;
        int result = new SearchInRotatedArray().search(nums, target);
        System.out.println(result);
    }
}
