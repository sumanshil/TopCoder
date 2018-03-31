package com.topcoder.problems.round13;

import java.util.concurrent.CountDownLatch;

//http://community.topcoder.com/stat?c=problem_solution&cr=112939&rd=3012&pm=100
public class GoodWord {
    private String vowels = "aeiouAEIOU";
    private int count;
    private boolean result=true;
    public boolean isValid(String s){
       isValidUtil(s,0);
       return result;
    }
    private char isValidUtil(String s, int index) {
        if (index == s.length()-1){
            if (isConsonant(s.charAt(index))){
                count++;
            }
            return s.charAt(index);
        }
        
        char c = s.charAt(index);
        char prev = isValidUtil(s, index+1);
        if (isConsonant(c) && !isConsonant(prev)){
            if (count != 0){
                result = false;
            } else {
                count++;
            }
        } else if (!isConsonant(c)){
            count--;
        } else if (isConsonant(c)){
            count++;
        }
        return c;
    }
    private boolean isConsonant(char prev) {        
        return vowels.indexOf(prev)>=0 ? false : true;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String s = "Aeioubxxxyaceeiddd";
        boolean result = new GoodWord().isValid(s);
        System.out.println(result);
    }

}
