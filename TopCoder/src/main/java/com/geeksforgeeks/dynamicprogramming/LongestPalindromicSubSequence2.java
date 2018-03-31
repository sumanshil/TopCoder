package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
public class LongestPalindromicSubSequence2 {

    public void find(String str){
        boolean isPalidrom[][] = new boolean[str.length()][str.length()];
        for ( int i = 0 ; i < str.length() ; i++){
            isPalidrom[i][i] = true;
        }

        for (int i = 0 ; i < str.length()-1; i++) {
            if (str.charAt(i) == str.charAt(i+1)) {
                isPalidrom[i][i+1] = true;
            }
        }

        int maxLength = Integer.MIN_VALUE;
        for ( int length = 3 ; length <= str.length() ; length++) {
            for ( int i = 0 ; i < str.length() - length + 1 ; i++) {
                int start = i;
                int end = i + length -1;
                if (isPalidrom[start+1][end-1] && str.charAt(start) == str.charAt(end)) {
                    maxLength = Math.max(maxLength, end-start + 1);
                    isPalidrom[start][end] = true;
                } else {
                    isPalidrom[start][end] = false;
                }
            }
        }
        System.out.println(maxLength);
    }


    public static void main(String[] args) {
        String str = "forgeeksskeegrof";
        new LongestPalindromicSubSequence2().find(str);
    }

}
