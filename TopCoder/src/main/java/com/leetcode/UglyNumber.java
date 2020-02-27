package com.leetcode;

//https://leetcode.com/problems/ugly-number/
public class UglyNumber {
    int[] nums = {2, 3, 5, 7};
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num > 1) {
            boolean found = false;
            for (int i = 0 ; i < nums.length; i++) {
                if (num % nums[i] == 0) {
                    if (nums[i] == 7) {
                        return false;
                    }
                    found = true;
                    num = num / nums[i];
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean res = new UglyNumber().isUgly(Integer.MIN_VALUE);
        System.out.println(res);
    }
}
