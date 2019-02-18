package com.leetcode.integer;

//https://leetcode.com/problems/binary-gap/
public class BinaryGap {
    public int binaryGap(int N) {
        int lastSetBitIndex = -1;
        int currentIndex = 0;
        int maxDistance = 0;
        while (N > 0) {
            int lastBit = N & 1;
            if (lastBit == 1 && lastSetBitIndex != -1) {
                maxDistance = Math.max(maxDistance, (currentIndex - lastSetBitIndex));
            }
            if (lastBit == 1) {
                lastSetBitIndex = currentIndex;
            }
            currentIndex++;
            N = N >> 1;
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int N = 8;
        int res = new BinaryGap().binaryGap(N);
        System.out.println(res);
    }
}
