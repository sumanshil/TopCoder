package com.topcoder.problems.round173;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 7/9/17.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=2002&rd=4670
public class WordForm {

    private static List<String> vowels = new ArrayList<>();
    static {
        vowels.add("A");
        vowels.add("E");
        vowels.add("I");
        vowels.add("O");
        vowels.add("U");
    }

    private static final String VOWEL = "V";
    private static final String CONSONANT = "C";

    public 	String getSequence(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        String prevChar = "";
        for ( int i = 0 ; i < word.length() ; i++) {
            String str = word.charAt(i)+"";
            if (vowels.contains(str.toUpperCase())&& !prevChar.equals(VOWEL)){
                stringBuilder.append(VOWEL);
                prevChar = VOWEL;
            } else if ("Y".equalsIgnoreCase(str)){
                if (isYVowel(i, word, prevChar) && !prevChar.equals(VOWEL) ){
                    stringBuilder.append(VOWEL);
                    prevChar = VOWEL;
                } else if (!prevChar.equals(CONSONANT)){
                    stringBuilder.append(CONSONANT);
                    prevChar = CONSONANT;
                }
            } else if(!vowels.contains(str.toUpperCase()) && !prevChar.equals(CONSONANT)) {
                prevChar = CONSONANT;
                stringBuilder.append(CONSONANT);
            }
        }
        return stringBuilder.toString();
    }

    private boolean isYVowel(int i, String word, String prevChar) {
        if (i != 0) {
            String prev = word.charAt(i-1)+"";
            if (!vowels.contains(prev)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "yC";
        String res = new WordForm().getSequence(str);
        System.out.println(res);
    }
}
