package com.geeksforgeeks.strings;

public class KMPAlgorithm2 {

    
    public void find(String sentence, String pattern){        
        int[] lps = computelps(pattern);
        int i = 0;
        int len = 0;
        while (i < sentence.length()){
            if (sentence.charAt(i) == pattern.charAt(len)){
                i++;
                len++;
            } else {
                if (len != 0)
                    len = lps[len-1];
                else 
                    i++;
            }
            if (len == pattern.length()){
                System.out.println("Pattern found at "+((i)-pattern.length()));
                len = 0;
            }
        }
        
    }
    private int[] computelps(String pattern) {
        int[] lps = new int[pattern.length()];
        lps[0] = 0;
        int i = 1;
        int len = 0;
        while(true){
            if (pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;                
                i++;
            } else{
                if (len != 0){
                    len = lps[len-1];
                } else {
                    i++;
                }
            }
            if (i == pattern.length()){
                break;
            }
        }
        return lps;        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "ABABDABACDABABCABAB";
        String pattern = "ABAB";
        new KMPAlgorithm2().find(str, pattern);
    }

}
