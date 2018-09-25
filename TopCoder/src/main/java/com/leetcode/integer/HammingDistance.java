package com.leetcode.integer;

//https://leetcode.com/problems/hamming-distance/description/
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int res = x ^ y;

        int result = 0;

        while (res > 0) {
            int masked = res & 1;
            if (masked > 0) {
                result++;
            }
            res = res >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        int res = new HammingDistance().hammingDistance(x, y);
        System.out.println(res);
    }
}
