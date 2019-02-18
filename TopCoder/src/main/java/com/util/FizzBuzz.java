package com.util;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/fizz-buzz/
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> retVal = new LinkedList<>();
        for (int i = 1 ; i <= n ; i++) {
           if ( i % 3 == 0 && i % 5 == 0) {
               retVal.add("FizzBuzz");
           } else if (i % 3 == 0) {
               retVal.add("Fizz");
           } else if (i % 5 == 0) {
               retVal.add("Buzz");
           } else {
               retVal.add(i+"");
           }
        }
        return retVal;
    }

    public static void main(String[] args) {
        int n = 15;
        List<String> res = new FizzBuzz().fizzBuzz(n);
    }
}
