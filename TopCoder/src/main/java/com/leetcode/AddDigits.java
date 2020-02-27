package com.leetcode;

//https://leetcode.com/problems/add-digits/
public class AddDigits {

    public int addDigits(int num) {
        while (num > 9) {

            int temp = num;
            int temp1 = 0;
            while (temp != 0) {
                int remainder = temp % 10;
                int num1 = temp / 10;
                temp1 += remainder;
                temp = num1;
            }
            num = temp1;
        }
        return num;
    }

    public static void main(String[] args) {
        int n = 38;
        int res = new AddDigits().addDigits(n);
        System.out.println(res);
    }
}
