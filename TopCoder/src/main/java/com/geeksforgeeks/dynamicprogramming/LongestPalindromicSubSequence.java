package com.geeksforgeeks.dynamicprogramming;

public class LongestPalindromicSubSequence {

    
    public int getLongestPalindromicSubSeq(String str){
        return getLongestPalindromicSubSeqDynamic(str, 0, str.length()-1);
    }
    private int getLongestPalindromicSubSeqRecursive(String str, int i,  int j) {
        if (i == j){
            return 1;
        }
        if (((j-i)+1) == 2){
            if (str.charAt(i) == str.charAt(j)){
                return 2;
            }
        }
        
        if (str.charAt(i) == str.charAt(j)){
            return getLongestPalindromicSubSeqRecursive(str, i+1, j-1)+2;            
        } else {
            int k = getLongestPalindromicSubSeqRecursive(str,i+1,j);
            int l = getLongestPalindromicSubSeqRecursive(str,i,j-1);
            if (k>l){
                return k;
            } else {
                return l;
            }
        }
    }
    
    public int getLongestPalindromicSubSeqDynamic(String str, int i , int j){
        int[][] table = new int[str.length()][str.length()];
        int n = str.length();
        for(int k = 0 ; k < str.length() ; k++){
            table[k][k] = 1;
        }
        
       // Build the table. Note that the lower diagonal values of table are
       // useless and not filled in the process. The values are filled in a
       // manner similar to Matrix Chain Multiplication DP solution (See
       // http://www.geeksforgeeks.org/archives/15553). cl is length of
       // substring
        
        for(int L = 2 ; L <=n ; L++ ){
            for (int m = 0 ; m < n-L+1; m++){
                int p = m +L-1;
                if (p == (m+1)){
                    if (str.charAt(p) == str.charAt(m)){
                        table[m][p] = 2;
                    } else{
                        table[m][p] = 0;
                    }
                } else if (str.charAt(m) == str.charAt(p)){
                    table[m][p] = 2+ table[m+1][p-1];
                } else {
                    table[m][p] = Math.max(table[m+1][p], table[m][p-1]);
                }
            }
        }
        
        return table[0][n-1];
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String str ="abac";
        String str = "GEEKSFORGEEKS";
        int result = new LongestPalindromicSubSequence().getLongestPalindromicSubSeq(str);
        System.out.println(result);

    }

}
