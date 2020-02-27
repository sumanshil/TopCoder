package com.leetcode;

//https://leetcode.com/problems/number-of-1-bits/
public class NumberOfOneBits {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0 ; i < 32 ; i++) {
            int temp = n & 1;
            if (temp == 1) {
                count++;
            }
            n = n >> 1;
            if (n == 0) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 5;
        int res = new NumberOfOneBits().hammingWeight(n);
        System.out.println(res);
    }
}
