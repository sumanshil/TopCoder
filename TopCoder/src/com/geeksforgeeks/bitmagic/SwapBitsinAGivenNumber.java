package com.geeksforgeeks.bitmagic;

public class SwapBitsinAGivenNumber {

    public int swap(int x, int p1, int p2, int n){
        int a = (x >> p1)&((1<<n) - 1);
        int b = (x >> p2)&((1<<n) - 1);
        int c = a ^ b;
        int d = (c << p1) | (c << p2);
        int result = d ^ x;
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(47));
        int number = new SwapBitsinAGivenNumber().swap(47, 1, 5, 3);
        System.out.println(Integer.toBinaryString(number));
    }

}
