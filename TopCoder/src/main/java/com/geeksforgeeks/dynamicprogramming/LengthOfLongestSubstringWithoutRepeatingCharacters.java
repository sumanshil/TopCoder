package com.geeksforgeeks.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 7/8/2016.
 */
//http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
public class LengthOfLongestSubstringWithoutRepeatingCharacters {

    public void find(String string){
        Set<String> set = new HashSet<>();
        int maxLength = Integer.MIN_VALUE;
        int currentCount = 0;
        for (  int i = 0 ; i < string.length() ; i++ ){
            String s = string.charAt(i)+"";
            if (!set.contains(s)) {
                currentCount++;
                set.add(s);
            } else {
                if (currentCount > maxLength){
                    maxLength = currentCount;
                }
                currentCount = 1;
                set.clear();
                set.add(s);
            }
        }
        System.out.println(maxLength);
    }

    public static void main(String[] args) {
        new LengthOfLongestSubstringWithoutRepeatingCharacters().find("GEEKSFORGEEKS");
    }

}
