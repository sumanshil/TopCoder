package com.leetcode;
//https://leetcode.com/problems/fraction-to-recurring-decimal/
public class FractionToDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        float result = (float)numerator / (float) denominator;
        String res = Float.toString(result);

        String[] array1 = res.split("\\.");

        StringBuilder resultBuff = new StringBuilder();

        resultBuff.append(Integer.parseInt(array1[0]));

        if (Long.parseLong(array1[1]) > 0) {
            resultBuff.append(".");

            String s1 = array1[1];

            StringBuilder currentBuffer = new StringBuilder();

            int count = 0;
            char prevChar = ' ';

            for ( int i = 0 ; i < s1.length() ; i++) {
                char c1 = s1.charAt(i);
                if (c1 == prevChar && prevChar != ' ') {
                    count++;
                } else if (prevChar != ' '){
                    if (count > 1) {
                        count = 1;
                        currentBuffer.append("(").append(prevChar).append(")");
                    } else {
                        currentBuffer.append(prevChar);
                    }
                } else if (prevChar ==  ' ') {
                    count++;
                }
                prevChar = s1.charAt(i);
            }
            if (count > 1) {
                currentBuffer.append("(").append(prevChar).append(")");
            } else if (count == 1) {
                currentBuffer.append(prevChar);
            }
            resultBuff.append(currentBuffer.toString());
        }
        return resultBuff.toString();
    }

    public static void main(String[] args) {
        int numnerator = 2;
        int denominator = 3;
        String res = new FractionToDecimal().fractionToDecimal(numnerator, denominator);
        System.out.println(res);
    }
}
