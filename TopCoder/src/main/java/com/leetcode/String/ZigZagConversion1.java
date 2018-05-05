package com.leetcode.String;

import java.util.Arrays;
import java.util.stream.Collectors;

//https://leetcode.com/problems/zigzag-conversion/description/
public class ZigZagConversion1 {

    public void find (String str, int row) {
        String[] arr = new String[row];
        for ( int i =0 ; i < row ; i++) {
            arr[i] = "";
        }

        int arrIndex = 0;
        int lastSeenIndex = 0;
        for ( int i = 0 ; i < str.length() ; i++) {
            arr[arrIndex] += str.charAt(i)+"";
            if (arrIndex == 0){
                lastSeenIndex = 0;
            } else if (arrIndex == row-1) {
                lastSeenIndex = row-1;
            }
            if (lastSeenIndex == 0) {
                arrIndex++;
                lastSeenIndex = 0;
            } else if (lastSeenIndex == row-1) {
                arrIndex--;
                lastSeenIndex = row-1;
            }
        }
        String retVal = Arrays.stream(arr).collect(Collectors.joining());
        System.out.println(retVal);
    }

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        new ZigZagConversion1().find(str, 3);
    }
}
