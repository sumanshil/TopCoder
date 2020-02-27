package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/generalized-abbreviation/
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {
        return helper(word);
    }

    private List<String> helper(String word) {
        if (word.length() == 0) {
            return new LinkedList<>(Arrays.asList(""));
        }
        if (word.length() == 1) {
            List<String> list = new LinkedList<>();
            list.add("1");
            list.add(word.charAt(0)+"");
            return list;
        }

        String s1 = word.charAt(0)+"";
        List<String> result = helper(word.substring(1));

        List<String> retVal = new LinkedList<>();


        for (String s2 : result) {
            if (isDigit(s2.charAt(0))) {
                int i = 0;
                StringBuffer stringBuffer = new StringBuffer();
                while (i < s2.length()) {
                    if (isDigit(s2.charAt(i))) {
                        stringBuffer.append(s2.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                int i1 = Integer.parseInt(stringBuffer.toString());
                retVal.add((i1+1) + s2.substring(i));
            } else {
                retVal.add("1" + s2);
            }
        }
        for (String s2 : result) {
            retVal.add(s1 + s2);
        }

        return retVal;
    }

    private boolean isDigit(char c) {
        return  c >= '0' && c <= '9';
    }
    public static void main(String[] args) {
        String word = "interaction";
        List<String> result = new GeneralizedAbbreviation().generateAbbreviations(word);
        System.out.println(result);
    }
}
