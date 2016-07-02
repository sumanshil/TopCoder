package com.geeksforgeeks.strings;

/**
 * Created by sshil on 6/13/2016.
 */
//http://www.geeksforgeeks.org/wildcard-pattern-matching/
public class WildCardPatternMatching {

    public void matches(String text, String pattern){
        boolean result = recursiveUtil(text, pattern);
        System.out.println(result);
    }

    private void dynamic(String text, String pattern){
        boolean[][] lookup = new boolean[text.length()+1][pattern.length()+1];
        lookup[0][0] = true;

        // '*' matches empty string
        for ( int j = 1 ; j < pattern.length() ; j++ ) {
            if (pattern.charAt(j-1) == '*'){
                lookup[0][j] = lookup[0][j-1];
            }
        }

        for ( int i = 1 ; i < text.length() ; i++) {
            for ( int j = 1 ; j < pattern.length() ; j++) {
                if (pattern.charAt(j-1) == '*') {
                    lookup[i][j] = lookup[i][j-1] || lookup[i-1][j];
                } else if (pattern.charAt(j-1) == '?' || (pattern.charAt(j-1) == text.charAt(i-1))) {
                    lookup[i][j] = lookup[i-1][j-1];
                } else {
                    lookup[i][j] = false;
                }
            }
        }
        System.out.println(lookup[lookup.length][lookup[0].length]);
    }


    private boolean recursiveUtil(String text, String pattern) {
        if ( (text.length() == 0 && pattern.length() != 0)
            || (text.length() != 0 && pattern.length() == 0)){
            return false;
        }
        if (text.length() == 0 && pattern.length() == 0){
            return true;
        }
        if (pattern.charAt(0) == '*' && pattern.length() > text.length()){
            return recursiveUtil(text, pattern.substring(1));
        } else  if (pattern.charAt(0) == '*' && pattern.length() < text.length()){
            return recursiveUtil(text.substring(1), pattern);
        } else  if (pattern.charAt(0) == '*' && pattern.length() == text.length()){
            return recursiveUtil(text.substring(1), pattern.substring(1));
        } else if (pattern.charAt(0) == text.charAt(0)){
            return recursiveUtil(text.substring(1), pattern.substring(1));
        } else if (pattern.charAt(0) == '?'){
            return recursiveUtil(text.substring(1), pattern.substring(1));
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        String text = "baaabab";
        String pattern = "a*a";
        new WildCardPatternMatching().matches(text, pattern);
    }
}
