package com.geeksforgeeks.dynamicprogramming;

public class LongestCommonSubSequence {

    protected int[][] lcs ;
    public int lcs(String str1, String str2, int x, int y){
        if (x == 0 || y== 0){
            return 0;
        }
        
        if (str1.charAt(x)  == str2.charAt(y)){
            return lcs(str1, str2, x-1, y-1)+1;
        } else {
            return Math.max(lcs(str1, str2, x, y-1), lcs(str1, str2, x-1, y));
        }
    }
    
    
    public int lcsDynamic(String x, String y){
        lcs = new int[x.length()+1][y.length()+1];
        lcs[0][0] = 0;
        System.out.print("    ");
        for(int i = 0 ; i < y.length() ; i++){
        	System.out.print(y.charAt(i));
        	System.out.print(" ");
        }
        System.out.println();
        for(int i = 1 ; i <=x.length(); i++){
            for(int j =1 ; j <=y.length() ; j++){
                if (x.charAt(i-1) == y.charAt(j-1)){
                    lcs[i][j]= lcs[i-1][j-1]+1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j-1], lcs[i-1][j]);
                }
               // System.out.print(lcs[i][j]+" " );                
            }
            //System.out.println();
        }

        for(int i = 0 ; i <= x.length() ; i++){
        	if (i == 0)
        		System.out.print("  ");
        	else if (i <= x.length())
        		System.out.print(x.charAt(i-1)+" ");
        	for(int j = 0 ; j <= y.length() ; j++){
        		System.out.print(lcs[i][j]+" ");
        	}
        	System.out.println();
        }
        
        
        return lcs[x.length()][y.length()];
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String original = "AGGTAB";
        String changed = "GXTXAYB";
        //int result= new LongestCommonSubSequence().lcs(x,y,x.length()-1,y.length()-1);
        int result = new LongestCommonSubSequence().lcsDynamic(original, changed);
        System.out.println(result);
    }

}
