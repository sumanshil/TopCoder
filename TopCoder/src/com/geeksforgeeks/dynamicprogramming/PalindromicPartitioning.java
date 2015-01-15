package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
public class PalindromicPartitioning {

    public int countPalindrom(String str){
        int result = countPalindromRecursive(str, 0, str.length());
        return result;
    }
    
    private int countPalindromRecursive(String str, int i, int j) {
        System.out.println("Considering "+str.subSequence(i, j));
        if (i == j)
            return 0;
        if (isPalindrom(str,i,j-1))
            return 0;
        int result  = (j-i);

        for(int k = i+1 ; k < j ;k++){
            int firstPart = countPalindromRecursive(str, i, k);
            int secondPart = countPalindromRecursive(str, k, j);
            result = Math.min(result, (firstPart+secondPart+1));
        }
       return result;
    }
    private boolean isPalindrom(String str, int i, int j) {
        if (i>j)
            return true;
        
        char c= str.charAt(i);
        char d = str.charAt(j);
        boolean result = isPalindrom(str, i+1, j-1);
        return result && (c==d);
    }
    
    public int countPalindromDynamic(String str){
        int n = str.length();
        
        /* Create two arrays to build the solution in bottom up manner
        C[i][j] = Minimum number of cuts needed for palindrome partitioning
                  of substring str[i..j]
        P[i][j] = true if substring str[i..j] is palindrome, else false
        Note that C[i][j] is 0 if P[i][j] is true */        
        int[][] c = new int[n][n];
        boolean[][] p = new boolean[n][n];
        
        for(int i = 0 ; i < n ;i++){
            p[i][i] = true;
            c[i][i] = 0;
        }
        
        /* L is substring length. Build the solution in bottom up manner by
        considering all substrings of length starting from 2 to n.
        The loop structure is same as Matrx Chain Multiplication problem (
        See http://www.geeksforgeeks.org/archives/15553 )*/        
        for(int L = 2 ; L<=n; L++){
            // For substring of length L, set different possible starting indexes
            for(int i = 0 ; i < (n-L+1) ; i++){
                int j = (i+(L-1)); //set Ending index
                
                if(L==2){
                    p[i][j] = (str.charAt(i) == str.charAt(j));
                } else{
                    p[i][j] = (str.charAt(i) == str.charAt(j)) && p[i+1][j-1];
                }
                
                if(p[i][j]){
                    c[i][j] = 0;
                } else{
                    c[i][j] = Integer.MAX_VALUE; 
                    for(int k = i ; k<j ;k++){
                        c[i][j] = Math.min(c[i][j], c[i][k]+1+c[k+1][j]);
                    }
                }
            }
        }
        return c[0][n-1];
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "aabcbaaabacbc";
        //String str = "cbcaba";
       // boolean result = new PalindromicPartitioning().isPalindrom(str, 0, str.length()-1);
        int result = new PalindromicPartitioning().countPalindromDynamic(str);
        System.out.println(result);
    }

}
