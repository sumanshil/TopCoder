package com.leetcode;

//https://leetcode.com/problems/range-sum-query-immutable/
public class NumArray {

    int[] sum = null;
    public NumArray(int[] nums) {
        sum = new int[nums.length];

        sum[0] = nums[0];

        for (int i = 1 ; i < nums.length ; i++) {
            sum[i] = sum[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (sum != null) {
            if (i > 0) {
                return sum[j] - sum[i - 1];
            } else {
                return sum[j];
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] arr = {-2, 0, 3, -5, 2, -1};
        int res = new NumArray(arr).sumRange(0, 5);
        System.out.println(res);
    }
}
