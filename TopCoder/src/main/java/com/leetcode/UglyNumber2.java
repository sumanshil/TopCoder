package com.leetcode;

//https://leetcode.com/problems/ugly-number-ii/discuss/404305/java-2ms-faster-DP-sol
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] merged = new int[n];
        merged[0] = 1;

        int p2 = 0, p3 = 0 , p5 = 0;

        for (int i = 1 ; i < n ; i++) {
            merged[i] = Math.min(merged[p2] * 2, Math.min(merged[p3] * 3, merged[p5] *5));

            if (merged[i] == merged[p2] * 2) {
                p2++;
            }

            if (merged[i] == merged[p3] * 3) {
                p3++;
            }

            if (merged[i] == merged[p5] * 5) {
                p5++;
            }
        }
        return merged[merged.length - 1];
    }

    public static void main(String[] args) {
        int n = 10;
        int res = new UglyNumber2().nthUglyNumber(n);
        System.out.println(res);
    }
}
