package com.leetcode.array.leetcode;

//https://leetcode.com/problems/1-bit-and-2-bit-characters/description/
public class OneBitAndTwoBit {

    public boolean isOneBitCharacter(int[] bits) {
        for ( int i = 0 ; i < bits.length ;) {
            if (i == bits.length -1) {
                return true;
            }
            if (bits[i] == 0) {
                i++;
            }
            if (bits[i] == 1) {
                i = i+2;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 0};
        boolean result = new OneBitAndTwoBit().isOneBitCharacter(arr);
        System.out.println(result);
    }

}
