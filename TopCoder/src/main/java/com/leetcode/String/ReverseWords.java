package com.leetcode.String;

import java.util.Stack;

//https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
public class ReverseWords {

    public String reverseWords(String s) {
        Stack<String> stringStack = new Stack<>();
        StringBuffer stringBuffer = new StringBuffer();
        for ( int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == ' ') {
                while (!stringStack.empty()) {
                    stringBuffer.append(stringStack.pop());
                }
                stringBuffer.append(' ');
            } else {
                stringStack.push(s.charAt(i) + "");
            }
        }
        while (!stringStack.empty()) {
            stringBuffer.append(stringStack.pop());
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        String res = new ReverseWords().reverseWords(str);
        System.out.println(res);
    }
}
