package com.leetcode.array.leetcode;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotataedSortedArray {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums[start] < nums[end]) {
            return nums[start];
        }

        while (start < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid];
            }

            if (nums[mid-1] > nums[mid]) {
                return nums[mid-1];
            }

            if (nums[mid] > nums[0]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    /*
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int result = recursive(0, nums.length -1 , nums);
        return result;
    }

    private int recursive(int low, int max, int[] nums) {
        if (low > max) {
            return -1;
        }
        if (low == max) {
            return nums[low];
        }
        int mid = low + (max - low) / 2;
        if (mid - 1 >= 0 && mid +1 < nums.length && nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
            return nums[mid];
        } else if ( mid - 1 < 0 && mid +1 < nums.length && nums[mid] < nums[mid + 1]) {
            return nums[mid];
        } else if (mid + 1 >= nums.length && mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
            return nums[mid];
        }

        if (nums[mid] > nums[max]) {
            return recursive(mid+1, max, nums);
        } else {
            return recursive(low, mid-1, nums);
        }
    }
     */

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        FindMinimumInRotataedSortedArray min = new FindMinimumInRotataedSortedArray();
        int res = min.findMin(arr);
        System.out.println(res);
    }
}
