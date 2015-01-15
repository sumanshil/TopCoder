package com.geeksforgeeks.bitmagic;

public class CheckIfTwoNumbersHaveOppositeSign {

    public boolean isSame(int a, int b){
        int x = a & (1<<31);
        int y = b & (1<<31);
        return ((x ^ y) == 0);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       System.out.println(new CheckIfTwoNumbersHaveOppositeSign().isSame(-23,34));

    }

}
