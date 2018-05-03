package com.leetcode.array.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/plus-one/description/
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int newArr[] = new int[digits.length];
        int remainder = 0;
        for ( int i = digits.length - 1; i >= 0 ; i-- ) {
            int number = digits[i];
            int newNumber;
            if ( i == digits.length -1) {
                newNumber = number + 1;
            } else {
                newNumber = number + remainder;
            }
            int result = newNumber % 10;
            remainder = newNumber / 10;
            newArr[i] = result;
        }
        if (remainder > 0) {
            int result[] = Arrays.copyOf(newArr, newArr.length + 1);
            result[0] = remainder;
            return result;
        }
        return newArr;
    }

    public static void main(String[] args) {
        int arr[] = {6,1,4,5,3,9,0,1,9,5,1,8,6,7,0,5,5,4,3};
        int result[] = new PlusOne().plusOne(arr);
        Arrays.stream(result).forEach(System.out::println);
    }
}
