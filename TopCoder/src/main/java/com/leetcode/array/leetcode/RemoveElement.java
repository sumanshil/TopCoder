package com.leetcode.array.leetcode;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int j = nums.length - 1;

        int i = j - 1;

        while (i >= 0) {
            if (nums[i] == val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
            i--;
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5};
        int res = new RemoveElement().removeElement(nums, 4);
        System.out.println(res);
    }
}
