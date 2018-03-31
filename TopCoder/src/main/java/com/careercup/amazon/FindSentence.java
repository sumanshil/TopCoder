package com.careercup.amazon;

import java.util.HashMap;
import java.util.Map;

public class FindSentence {
    private static Map<String, Integer> dictionary;
    static {
        dictionary = new HashMap<String, Integer>();
        dictionary.put("a", 1);
        dictionary.put("service", 1);
        dictionary.put("system", 1);
        dictionary.put("out", 1);
        dictionary.put("put", 1);
        dictionary.put("output", 1);
        dictionary.put("outputs", 1);
        dictionary.put("is", 1);
        dictionary.put("invalid", 1);
    }
    
    public String getSentence(String str, int index){
        if (str == null || index>= str.length()){
            return null;
        }
        if (dictionary.containsKey(str)){
            return str;
        }
        index++;
        String firstPart = str.substring(0, index);
        String secondPart = str.substring(index);
        if (dictionary.containsKey(firstPart)){
            return firstPart+" "+getSentence(secondPart, 0);
        } else {
            return getSentence(str, index);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String str = "aservicesystem";
        String result = new FindSentence().getSentence(str, 0);
        System.out.println(result);

    }

}
