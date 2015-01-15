package com.geeksforgeeks.bitmagic;

public class AddTwoNumbers {

    public int sum(int x, int y){
        while(y > 0){
            int carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }
        return x;
        
    }

    public static void main(String[] args){
        int result = new AddTwoNumbers().sum(8, 9);
        System.out.println(result);
    }
}
