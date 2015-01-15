package com.geeksforgeeks.strings;

public class PatternSearching {

    public int search(String text, String pattern){
        for(int i = 0 ; i < text.length()-pattern.length() ; i++){
            boolean matchFound= true;
            for(int j = 0 ; j < pattern.length(); j++){
                if (pattern.charAt(j) != text.charAt(i+j)){
                    matchFound = false;
                    break;
                }
            }
            if (matchFound){
                System.out.println("Pattern found at "+i);                
            }
        }
        return -1;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String text = "THIS IS A TEST TEXT";
        //String pattern =  "TEST";
        String text = "AABAACAADAABAAABAA";
        String pattern ="AABA";
        System.out.println(new PatternSearching().search(text, pattern));

    }

}
