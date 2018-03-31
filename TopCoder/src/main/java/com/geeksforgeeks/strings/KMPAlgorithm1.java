package com.geeksforgeeks.strings;

public class KMPAlgorithm1 {

    public void kmpSearch(String text, String pattern){
        
        int[] lps = computeLPSArray(pattern);
        int i = 0;
        int j = 0;
        while(i<text.length()){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            } 
            
            if (j == pattern.length()){
              System.out.println("Pattern match found at "+(i-(pattern.length())));
              j = lps[j-1];
            } else if (text.charAt(i) != pattern.charAt(j)){
                if (j != 0){
                    j = lps[j-1];
                } else{
                    i++;
                }
            }
        }
        
        
    }
    private int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        lps[0] = 0;
        for(int i = 1 ; i< pattern.length() ;){
            if (pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            } else{
                if (len != 0){
                    len = lps[len-1];
                } else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        new KMPAlgorithm1().kmpSearch(text, pattern);

    }

}
