package com.geeksforgeeks.strings;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by sshil on 8/9/2016.
 */
//http://www.geeksforgeeks.org/print-words-matching-pattern-camelcase-notation-dictonary/
public class PrintAllWordsMatchingCamelCaseDisctionary {

    private String pattern = null;
    public void print(String[] word, String pattern){
        this.pattern = pattern;
        List<String> list = Arrays.stream(word)
                                  .filter(e -> this.check(e))
                                  .collect(toList());
        list.stream().forEach(System.out::println);
    }

    public boolean check(String str){
        int patternIndex = 0;
        int strIndex = 0;
        char prevChar = ' ';
        while(patternIndex < pattern.length() && strIndex < str.length()) {
            if (isCamelCase(prevChar) && isCamelCase(str.charAt(strIndex))){
                return false;
            } else if (pattern.charAt(patternIndex) == str.charAt(strIndex)){
                prevChar = str.charAt(strIndex);
                strIndex++;
                patternIndex++;
            } else if (isCamelCase(str.charAt(strIndex))){
                return false;
            } else {
                prevChar = str.charAt(strIndex);
                strIndex++;
            }
        }
        if (patternIndex < pattern.length()){
            return false;
        }
        return true;
    }

    private boolean isCamelCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static void main(String[] args) {
        String[] str = {"WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks"};
        String pattern = "GFG";
        new PrintAllWordsMatchingCamelCaseDisctionary().print(str, pattern);
    }
}
