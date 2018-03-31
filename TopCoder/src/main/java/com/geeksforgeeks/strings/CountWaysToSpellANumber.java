package com.geeksforgeeks.strings;


/**
 * Created by sshil on 10/24/16.
 */
//http://www.geeksforgeeks.org/count-ways-spell-number-repeated-digits/
public class CountWaysToSpellANumber {

    public void count(String str ) {
        int result = 1;
        char prevChar = '$';
        int currentCount = 0;

        for ( int i = 0 ; i < str.length() ; i++) {
            char currentChar = str.charAt(i);
            if (prevChar != '$' && prevChar != currentChar) {
                result *= Math.pow(2, (currentCount-1));
                currentCount = 1;
            } else {
                currentCount++;
            }
            prevChar = currentChar;
        }
        result *= Math.pow(2, (currentCount-1));
        System.out.println(result);
    }
    public static void main(String[] args) {
        String str = "11111";
        new CountWaysToSpellANumber().count(str);
    }
}
