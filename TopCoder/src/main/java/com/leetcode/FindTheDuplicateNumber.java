package com.leetcode;

//https://leetcode.com/problems/find-the-duplicate-number/
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        long result = 0;
        for (int num : nums) {
            long value = 1 << num;
            long newMask = result & value;
            if (newMask != 0) {
                return num;
            }
            result = result | value;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {13,46,8,11,20,17,40,13,13,13,14,1,13,36,48,41,13,13,13,13,45,13,28,42,13,10,15,22,13,13,13,13,23,9,6,13,47,49,16,13,13,39,35,13,32,29,13,25,30,13};
        int res = new FindTheDuplicateNumber().findDuplicate(arr);
        System.out.println(res);
    }
}
