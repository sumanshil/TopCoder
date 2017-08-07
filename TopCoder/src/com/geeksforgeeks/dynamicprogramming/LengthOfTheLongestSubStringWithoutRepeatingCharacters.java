package com.geeksforgeeks.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
public class LengthOfTheLongestSubStringWithoutRepeatingCharacters {

    public void find (String str) {
        Map<String, Integer> map = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = startIndex;
        while (endIndex < str.length()){
            String s = str.charAt(endIndex)+"";
            if (map.containsKey(s)){
                int index = map.get(s);
                if (endIndex - (startIndex) > maxLength){
                    maxLength = (endIndex - (startIndex));
                }
                map.clear();
                startIndex = index + 1;
                map.put(s, endIndex);
            } else {
                map.put(s, endIndex);
            }
            endIndex++;
        }
        if (endIndex - startIndex > maxLength){
            maxLength = endIndex- startIndex;
        }
        System.out.println(maxLength);
    }
    public static void main(String[] args) {
        String str = "ABDEFGABEF";
        new LengthOfTheLongestSubStringWithoutRepeatingCharacters().find(str);
    }
}
