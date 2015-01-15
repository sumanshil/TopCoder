package com.geeksforgeeks.bitmagic;

public class SwapAllOddBitsAndEvenBits {

    public int swapBits(int x){
        int a = x & 0xAAAAAAAA;
        int b = x & 0x55555555;
        int result = (a >> 1)|(b <<1); 
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //int x = 0xAAAAAAAA;
        int x = 23;
        int result = new SwapAllOddBitsAndEvenBits().swapBits(23);
        System.out.println(result);

    }

}
