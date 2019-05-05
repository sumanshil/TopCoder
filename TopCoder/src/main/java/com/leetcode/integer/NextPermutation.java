package com.leetcode.integer;

import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int indexToReverse = -1;
        for ( int i = 0; i < nums.length - 1 ; i++) {
            if (nums[i] < nums[i+1]) {
                indexToReverse = i;
            }
        }

        if (indexToReverse == -1) {
            Arrays.sort(nums);
            return;
        }

        int nextGreaterElementIndex = -1;
        for ( int i = nums.length - 1; i > indexToReverse ; i--) {
            if (nextGreaterElementIndex == -1 && nums[i] > nums[indexToReverse]) {
                nextGreaterElementIndex = i;
            } else if (nums[i] > nums[indexToReverse] && nums[i] < nums[nextGreaterElementIndex] ) {
                nextGreaterElementIndex = i;
            }
        }

        if (nextGreaterElementIndex != -1) {
            int temp = nums[nextGreaterElementIndex];
            nums[nextGreaterElementIndex] = nums[indexToReverse];
            nums[indexToReverse] = temp;

            Arrays.sort(nums, indexToReverse + 1, nums.length );
        }

    }

    public static void main(String[] args) {
        int[] number = {1,3,2};
        new NextPermutation().nextPermutation(number);
        for (int i : number) {
            System.out.println(i);
        }
    }
}
