package com.leetcode.array.leetcode;

//https://leetcode.com/problems/max-consecutive-ones/description/
public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxNumber = Integer.MIN_VALUE;
        int currentNumber = 0;
        for (int i = 0 ; i < nums.length ; i++) {
            if (nums[i] == 1) {
                currentNumber++;
            } else {
                maxNumber = Math.max(currentNumber, maxNumber);
                currentNumber = 0;
            }
        }
        maxNumber = Math.max(currentNumber, maxNumber);
        return maxNumber;
    }

    public static void main(String[] args) {
        int arr[] = {1,1,0,1,1,1};
        int result = new MaxConsecutiveOnes().findMaxConsecutiveOnes(arr);
        System.out.println(result);
    }
}
