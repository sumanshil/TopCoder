package com.dp;

/**
 * Created by sshil on 10/4/16.
 */
//http://www.practice.geeksforgeeks.org/problem-page.php?pid=700264
public class KPalindrom {

    public void find(String str, int k) {
        int minChanges = findRecursive(str, k, 0, str.length()-1);
        System.out.println(minChanges <= k);
    }

    private int findRecursive(String str,
                              int k,
                              int startIndex,
                              int endIndex) {
        if (startIndex +1 == endIndex){
            if (str.charAt(startIndex) == str.charAt(endIndex)){
                return 0;
            }
            return 1;
        }
        if (startIndex >= endIndex){
            return 0;
        }

        if (str.charAt(startIndex) == str.charAt(endIndex)){
            return findRecursive(str, k, startIndex+1, endIndex-1);
        } else {
            int rRemove = findRecursive(str, k, startIndex+1, endIndex)+1;
            int lRemove = findRecursive(str, k, startIndex, endIndex-1)+1;
            int bothRemove = findRecursive(str, k, startIndex+1, endIndex-1)+2;
            return Math.min(rRemove, Math.min(lRemove, bothRemove));
        }
    }


    private void dp(String str, int k){
        int[][] matrix = new int[str.length()][str.length()];
        for ( int i = 0 ; i < str.length() ; i++){
            for ( int j = 0 ; j < str.length() ; j++){
                if (i == j){
                    matrix[i][j] = 0;
                } else if ((i+1) ==j){
                    if (str.charAt(i) == str.charAt(j)){
                        matrix[i][j] = 0;
                    } else {
                        matrix[i][j] = 1;
                    }
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for ( int len = 3 ; len <= str.length() ; len++ ){
            for ( int i = 0 ; i + len <= str.length() ; i++){
                int j = i + (len-1);
                if (str.charAt(i) == str.charAt(j)) {
                    matrix[i][j] = matrix[i+1][j-1];
                } else {
                    int a = matrix[i+1][j]+1;
                    int b = matrix[i][j-1]+1;
                    int c = matrix[i+1][j-1]+2;
                    matrix[i][j] = Math.min(a,Math.min(b,c));
                }
            }
        }
        int result = matrix[0][str.length()-1];
        System.out.println(result <=k);
    }


    public static void main(String[] args) {
        String str = "acdcb";
        int count = 1;
        new KPalindrom().dp(str, count);
    }
}
