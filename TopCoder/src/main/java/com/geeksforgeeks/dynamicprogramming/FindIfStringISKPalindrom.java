package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/19/2016.
 */
//http://www.geeksforgeeks.org/find-if-string-is-k-palindrome-or-not/
public class FindIfStringISKPalindrom {

    public void find(String str, int count){
       // int result = recursive(str, 0, str.length()-1);
        int result = dp(str);
        System.out.println(result <= count);
    }

    private int recursive(String str, int start, int end) {
        if (start >= end){
            return 0;
        }
        // length 2
        if (start + 1 == end){
            int l = start;
            int h = end;
            if (str.charAt(l) == str.charAt(h)){
                return 0;
            } else {
                return 1;
            }
        }


        if (str.charAt(start) == str.charAt(end)) {
            return recursive(str,start+1, end-1);
        } else {
            return Math.min(recursive(str, start+1, end)+1, recursive(str, start, end-1)+1);
        }
    }

    public int dp(String str){
        int[][] matrix = new int[str.length()][str.length()];
        for ( int length = 2 ; length <= str.length() ; length++){
            for ( int i = 0 ; i < str.length() ; i++){
                int j = i + (length-1);
                if ( j < str.length() ) {
                    if (length == 2){
                        if (str.charAt(i) == str.charAt(j)){
                            matrix[i][j] = 0;
                        } else {
                             matrix[i][j] = 1;
                        }
                    } else {
                        if (str.charAt(i) == str.charAt(j)){
                            matrix[i][j] = matrix[i+1][j-1];
                        } else {
                            matrix[i][j] = Math.min(matrix[i+1][j], matrix[i][j-1])+1;
                        }
                    }
                }
            }
        }
        return matrix[0][str.length()-1];
    }


    public static void main(String[] args) {
        new FindIfStringISKPalindrom().find("acdcb",2);
    }
}
