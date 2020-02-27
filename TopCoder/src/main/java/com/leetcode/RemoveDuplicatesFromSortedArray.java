package com.leetcode;

//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int posIndex1 = 0;
        int posIndex2 = 0;
        int count = 1;
        int prev = Integer.MIN_VALUE;
        while (posIndex2 < nums.length) {
            if (count <= 2 && prev == nums[posIndex2]) {
                nums[posIndex1] = nums[posIndex2];
                count++;
                prev = nums[posIndex2++];
                posIndex1++;
            } else if (count <= 2 && prev != nums[posIndex2]) {
                count = 1;
                nums[posIndex1] = nums[posIndex2];
                count++;
                prev = nums[posIndex2++];
                posIndex1++;
            } else {
                while (prev != Integer.MIN_VALUE && posIndex2 < nums.length && nums[posIndex2] == prev) {
                    prev = nums[posIndex2];
                    posIndex2++;
                }
                count = 1;
            }
        }
        return posIndex1;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int result = new RemoveDuplicatesFromSortedArray().removeDuplicates(nums);
        System.out.println(result);
    }
}
