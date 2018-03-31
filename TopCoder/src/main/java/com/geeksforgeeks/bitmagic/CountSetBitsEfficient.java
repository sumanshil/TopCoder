package com.geeksforgeeks.bitmagic;

public class CountSetBitsEfficient {

    public int countSetBits(int n){
        int count = 0;
        int mask = 3;
        while(n > 0 ){
            if ((n & mask) >0 ){
                if ((n & mask) == 3){
                    count+=2;
                } else if (((n & mask) == 1 )||((n & mask) == 2 )){
                    count+=1;
                }
            }
            n = n >> 2;
        }
        return count;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = new CountSetBitsEfficient().countSetBits(16);
        System.out.println(result);

    }

}
