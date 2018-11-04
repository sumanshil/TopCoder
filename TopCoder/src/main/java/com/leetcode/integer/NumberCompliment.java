package com.leetcode.integer;

//https://leetcode.com/problems/number-complement/description/
public class NumberCompliment {

    public int findComplement(int num) {
        System.out.println(Integer.MAX_VALUE);
        if (num == Integer.MAX_VALUE) {

            return 0;
        }
        int count = 0;
        int number = num;
        while (number > 0 ) {
            count++;
            number = number >> 1;
        }
        int mask = (int)Math.pow(2, count) - 1;
        return mask ^ num;
    }


    public static void main(String[] args) {
        int n = 1;
        int res = new NumberCompliment().findComplement(n);
        System.out.println(res);
    }


}
