package com.leetcode;

//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null ||nums.length == 0) {
            return 0;
        }
        int startIndex = 1;
        int targetIndex = 1;

        int numberToSearch = nums[0];

        while (startIndex < nums.length) {
            if (nums[startIndex] == numberToSearch) {
                startIndex++;
                continue;
            } else {
                nums[targetIndex] = nums[startIndex];
                targetIndex++;
                numberToSearch = nums[startIndex];
                startIndex++;
            }
        }
        return targetIndex ;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int res = new RemoveDuplicates().removeDuplicates(nums);
        System.out.println(res);
    }
}
