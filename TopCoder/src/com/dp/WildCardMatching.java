package com.dp;

/**
 * Created by sshil on 10/4/16.
 */
//http://www.practice.geeksforgeeks.org/problem-page.php?pid=700287
public class WildCardMatching {

    public void isMatch(String input, String pattern) {
        boolean result = findRecursive(input, pattern, input.length()-1, pattern.length()-1);
        System.out.println(result);
    }

    private boolean findRecursive(String input, String pattern, int inputIndex, int patternIndex) {
        if (inputIndex < 0 && patternIndex < 0){
            return true;
        }
        if (inputIndex < 0 && patternIndex >= 0 && pattern.charAt(patternIndex) != '*'){
            return false;
        }
        /*
        if ((inputIndex < 0 && patternIndex >= 0) ||(inputIndex >= 0 && patternIndex < 0)){

            return false;
        }
        */
        if ((patternIndex >= 0 && inputIndex >= 0) && pattern.charAt(patternIndex) == '*') {
            return findRecursive(input, pattern, inputIndex, patternIndex-1)
                    ||
                    findRecursive(input, pattern, inputIndex-1, patternIndex);
        } else if ((patternIndex>= 0 && inputIndex >=0) && ((pattern.charAt(patternIndex) == '?'
                || (pattern.charAt(patternIndex) == input.charAt(inputIndex))))) {
            return findRecursive(input, pattern, inputIndex-1, patternIndex-1);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String input = "baaabab";
        String pattern = "a*ab";
        new WildCardMatching().isMatch(input, pattern);
    }
}
