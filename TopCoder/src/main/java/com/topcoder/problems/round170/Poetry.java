package com.topcoder.problems.round170;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 6/7/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1835&rd=4655
public class Poetry {
    private Map<String, Character> subStringToCodeMap = new HashMap<>();
    private char lastChar = ' ';
    private StringBuffer result = null;

    public String rhymeScheme(String[] poem) {
        result = new StringBuffer(poem.length);
        for (String str : poem) {
            if (str.trim().length() == 0){
                result.append(" ");
            } else {
                String[] arr = str.split("\\s+");
                String lastWord = arr[arr.length-1];
                String subStr = findEndingSubString(lastWord);
                execute(subStr);
            }
        }
        return result.toString();
    }

    private void execute(String subStr) {
        String allLowerCase = subStr.toLowerCase();
        if (subStringToCodeMap.containsKey(allLowerCase)){
            char code = subStringToCodeMap.get(allLowerCase);
            result.append(code);
        } else{
            if (lastChar == ' '){
                lastChar = 'a';
                subStringToCodeMap.put(subStr, 'a');
            } else if (lastChar == 'z'){
                lastChar = 'A';
                subStringToCodeMap.put(subStr, 'A');

            } else if (lastChar == 'Z'){
                lastChar = 'a';
                subStringToCodeMap.put(subStr, 'a');
            } else {
                int result = lastChar + 1;
                subStringToCodeMap.put(subStr, (char)result);
                lastChar =  (char)result;
            }
            result.append(lastChar);
        }

    }

    private String findEndingSubString(String lastWord) {
        int lastStartVowelIndex = 0;
        char c = lastWord.charAt(0);
        if (isVowel(c, 0, lastWord)){
            lastStartVowelIndex = 0;
        }

        for ( int i = 1 ; i < lastWord.length() ; i++){
            if (!isVowel(lastWord.charAt(i-1), i-1, lastWord) && isVowel(lastWord.charAt(i), i, lastWord)){
                lastStartVowelIndex = i;
            }
        }
        return lastWord.substring(lastStartVowelIndex);
    }

    private boolean isVowel(char c, int index, String word) {
        return  c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
               || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
               || (c == 'y' && index != 0 && index != word.length()-1);
    }

    public static void main(String[] args) {
        String[] input = {" solution went sour", "too bad yyour"};
        String result = new Poetry().rhymeScheme(input);
        System.out.println(result);
    }
}
