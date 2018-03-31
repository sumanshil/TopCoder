package com.geeksforgeeks.strings;
//http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
public class KMPAlgorithm {

    public void KMPSearch(String text, String pattern){
        int m = pattern.length();
        int n = text.length();
        
        int j = 0;
        int[] lps = computeLPSArray(pattern, m );
        int i = 0; 
        while(i<n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            
            if (j == m){
                System.out.println("Match found at "+i);
                j = lps[j-1];
            } else {
                if (j != 0){
                    j = lps[j-1];
                } else{
                    i++;
                }
            }
        }
    }
    private int[] computeLPSArray(String pattern, int m) {
        
        int[] lps = new int[pattern.length()];
        int i = 0;
        int len = 0;
        
        lps[0] = 0;
        i = 1;
        
        while(i < m){
            if (pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            } else {//(pattern.charAt(i) == pattern.charAt(len))
                if (len != 0){
                    // This is tricky. Consider the example AAACAAAA and i = 7.
                    len = lps[len-1];          
                    // Also, note that we do not increment i here
                } else {// if (len == 0)
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
        //String pattern = "AAACAAAA";
        //String pattern ="ABABAC";
        new KMPAlgorithm().KMPSearch(text, pattern);

    }

}
