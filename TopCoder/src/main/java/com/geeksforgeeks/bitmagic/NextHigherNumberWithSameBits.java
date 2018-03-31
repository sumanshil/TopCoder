package com.geeksforgeeks.bitmagic;
//http://www.geeksforgeeks.org/next-higher-number-with-same-number-of-set-bits/
//x = 156
//
//10
//
//x = 10011100
//
//(2)
//
//10011100
//00011100 - right most string of 1's in x
//00000011 - right shifted pattern except left most bit ------> [A]
//00010000 - isolated left most bit of right most 1's pattern
//00100000 - shiftleft-ed the isolated bit by one position ------> [B]
//10000000 - left part of x, excluding right most 1's pattern ------> [C]
//10100000 - add B and C (OR operation) ------> [D]
//10100011 - add A and D which is required number 163


public class NextHigherNumberWithSameBits {

    public int getNextHigherNumber(int n){
        int rightMostSetBit = n & -n;
        int nextHigherOne = n + rightMostSetBit;
        int rightOnesPattern = n ^ nextHigherOne;
        rightOnesPattern = rightOnesPattern/rightMostSetBit;
        rightOnesPattern = rightOnesPattern >> 2;
        return nextHigherOne | rightOnesPattern;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 12;
        System.out.println(Integer.toBinaryString(n));
        int x = new NextHigherNumberWithSameBits().getNextHigherNumber(n);
        System.out.println(Integer.toBinaryString(x));
    }

}
