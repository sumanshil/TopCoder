package com.geeksforgeeks.bitmagic;

public class ReveseBits {

    public int reversebits(int n){
        int reverseSum = 0;
        for(int i = 0 ; i < 32; i++){
            if ((n & (1 <<i)) == 1){
                n |= (1 << i);
            }
        }
        return reverseSum;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        

    }

}
