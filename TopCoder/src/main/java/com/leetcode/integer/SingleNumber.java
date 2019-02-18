package com.leetcode.integer;

//https://leetcode.com/problems/single-number/
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int number = 0;
        for ( int i = 0 ; i < nums.length ; i++) {
            number = number ^ nums[i];
        }
        return number;
    }

    public static void main(String[] args) {
        int arr[] = {2, 2, 1};
        int result = new SingleNumber().singleNumber(arr);
        System.out.println(result);
    }
}
