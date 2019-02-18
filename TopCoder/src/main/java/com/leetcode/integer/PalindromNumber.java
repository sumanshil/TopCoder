package com.leetcode.integer;

public class PalindromNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int originalNumber = x;
        int reversedNumber = 0;

        while ( x > 0) {
            reversedNumber = reversedNumber*10 + x%10;
            x = x/10;
        }
        return reversedNumber == originalNumber;
    }

    public static void main(String[] args) {
        int number = 121;
        boolean res = new PalindromNumber().isPalindrome(number);
        System.out.println(res);
    }
}
