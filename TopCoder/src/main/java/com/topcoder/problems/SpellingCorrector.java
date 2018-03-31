package com.topcoder.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellingCorrector {
    private static Map<String, Integer> dictionary= new HashMap<String, Integer>();    
    /**
     * @param args
     */
    public static void main(String[] args) {
        dictionary.put("spelling", 1);
        dictionary.put("ashish", 1);
        dictionary.put("param", 1);
         
        String input = "spiling";
        String correct = correct(input);
        System.out.println(correct);

    }

    private static String correct(String word) {
        if (dictionary.containsKey(word))
            return word;
        
        List<String> edits = edits(word);
        Map<Integer, String> candidates = new HashMap<Integer, String>();
        for(String edit : edits){
            if (dictionary.containsKey(edit)){
                candidates.put(dictionary.get(edit), edit);
            }
        }
        
        if (candidates.size() > 0){
            return candidates.get(Collections.max(candidates.keySet()));
        }
        
        for(String edit : edits){
            List<String> newEdits = edits(edit);
            for(String ne : newEdits){
                if (dictionary.containsKey(ne)){
                    candidates.put(dictionary.get(ne), ne);
                }
            }
        }
        
        if (candidates.size() > 0){
            return candidates.get(Collections.max(candidates.keySet()));
        } else {
            return word;
        }
    }

    private static List<String> edits(String word) {
        List<String> retVal = new ArrayList<String>();
        // delete characters in each position
        String str;
        for(int i = 0 ; i < word.length(); i++){
            str = word.substring(0, i)+word.substring(i+1);
            retVal.add(str);
        }
        // swap adjacent characters        
        for(int i = 0 ; i < word.length()-1; i++){
            str = word.substring(0, i)+ word.charAt(i+1)+word.charAt(i)+word.substring(i+2);
            retVal.add(str);
        }
        // replace (change one letter to another)
        for(int i = 0 ; i < word.length() ; i++){
            for(char c = 'a'; c <= 'z'; c++){
                str = word.substring(0, i)+c+word.substring(i+1);
                retVal.add(str);
            }
        }
        // insert (add a letter)
        // note here i is less than and EQUAL to
        for(int i = 0 ; i <= word.length() ;i++){
            for(char c ='a' ; c <='z' ; c++){
                str = word.substring(0,i)+c +word.substring(i);
                retVal.add(str);
            }
        }
        return retVal;
    }
}
