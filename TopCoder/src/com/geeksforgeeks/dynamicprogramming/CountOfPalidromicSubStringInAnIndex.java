package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/8/17.
 */
//http://www.geeksforgeeks.org/count-of-palindromic-substrings-in-an-index-range/
public class CountOfPalidromicSubStringInAnIndex {
    public void find(String str){
        int stringLength = str.length();
        boolean[][] isPalidrom = new boolean[stringLength][stringLength];
        int[][] palidromCount = new int[stringLength][stringLength];
        for ( int i = 0; i < stringLength ; i++){
            isPalidrom[i][i] = true;
            palidromCount[i][i] = 1;
        }
        for ( int i = 0 ; i < stringLength; i++) {
            for (int j = i ; j < stringLength; j++){
                if ( i == j){
                    isPalidrom[i][j] = true;
                    palidromCount[i][j] = 1;
                    continue;
                }
                if ( i == (j-1)){
                    if (str.charAt(i) == str.charAt(j)){
                        isPalidrom[i][j] = true;
                        palidromCount[i][j] = 3;
                    } else {
                        isPalidrom[i][j] = false;
                        palidromCount[i][j] = 2;
                    }
                    continue;
                }

                if (str.charAt(i) == str.charAt(j)){
                    isPalidrom[i][j] = isPalidrom[i+1][j-1];
                    palidromCount[i][j] = palidromCount[i][j-1]
                            + palidromCount[i+1][j];

                } else {
                    isPalidrom[i][j] = false;
                    palidromCount[i][j] = palidromCount[i][j-1]
                            + palidromCount[i+1][j]-1;
                }
            }
        }
        System.out.println(isPalidrom[0][stringLength-1]);
    }

    public static void main(String[] args) {
       // String str = "xyaabax";
        String str = "aba";
        new CountOfPalidromicSubStringInAnIndex().find(str);
    }
}
