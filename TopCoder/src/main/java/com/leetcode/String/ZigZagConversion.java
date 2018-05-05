package com.leetcode.String;

//https://leetcode.com/problems/zigzag-conversion/description/
public class ZigZagConversion {

    public String zigzagprint (String str, int rowNumber) {
        if (str.length() <= rowNumber) {
            return str;
        }
        int startIndex = 0;
        int originalGap = 2 * rowNumber - 2;
        if (originalGap == 0) {
            return str;
        }
        int nextIndex = startIndex + originalGap;
        int currentOddGap;
        int currentEvenGap;
        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0 ; i < rowNumber ; i++) {
            if ( i == 0 || i == rowNumber -1) {
                for (int j = startIndex; j < str.length(); j = j + originalGap) {
                    stringBuilder.append(str.charAt(j));
                }
            } else {
                int currentIndex = startIndex;
                int nextOriginalNext = startIndex + originalGap;
                int iterationNum = 0;
                currentEvenGap = (nextIndex - startIndex);
                currentOddGap = (nextOriginalNext - nextIndex);
                while (currentIndex < str.length()) {
                    stringBuilder.append(str.charAt(currentIndex));
                    if (iterationNum % 2 == 0) {
                        currentIndex += currentEvenGap;
                    } else {
                        currentIndex += currentOddGap;
                    }
                    iterationNum++;
                }
            }
            startIndex++;
            nextIndex--;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String input = "AB";
        int rowNumber = 1;
        String result = new ZigZagConversion().zigzagprint(input, rowNumber);
        System.out.println(result);
    }
}
