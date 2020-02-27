package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/encode-and-decode-strings/
public class EncodeAndDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuffer stringBuffer = new StringBuffer();

        for (String str : strs) {
            stringBuffer.append('$');
            stringBuffer.append(str.length());
            stringBuffer.append('$');
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new LinkedList<>();

        for (int i = 0 ; i < s.length() ; ) {
            if (s.charAt(i) == '$') {
                StringBuilder stringBuffer = new StringBuilder();
                i++;
                while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    stringBuffer.append(s.charAt(i));
                    i++;
                }
                int length = Integer.parseInt(stringBuffer.toString());
                i++;
                if (length == 0) {
                    result.add("");
                } else {
                    String subStr = s.substring(i, i + length);
                    i = i + length;
                    result.add(subStr);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        strings.add("");
        strings.add("");
        String str = new EncodeAndDecodeStrings().encode(strings);

        List<String> list = new EncodeAndDecodeStrings().decode(str);
        System.out.println(list);

    }
}
