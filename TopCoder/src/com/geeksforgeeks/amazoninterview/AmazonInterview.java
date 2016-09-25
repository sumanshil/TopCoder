package com.geeksforgeeks.amazoninterview;

import java.util.concurrent.CountDownLatch;

//4. Given two strings STR1 and STR2 .we need to inOrder longest substring in STR1 whose all characters are taken
//from string STR2(was asked to write code for it in optimal time)
public class AmazonInterview {

    public int findMaxSubString(String str, String pattern){
        int[] patternArr = new int[256];
        for(int i = 0 ; i < pattern.length() ; i++){
            patternArr[pattern.charAt(i)] = 1;
        }
        int[] strArr = new int[str.length()];
        for(int i = 0 ; i < str.length() ; i++){
            char c = str.charAt(i);
            if (patternArr[c] == 1){
                strArr[i] =1 ;
            }
        }
        
        for(int i = 0 ; i < strArr.length ; i++){
            System.out.print(strArr[i]);
        }
        System.out.println();
        int maxSubStringCount = Integer.MIN_VALUE;
        int currentCount = 0;
        for(int i = 0 ; i < strArr.length ; i++){
            if (strArr[i] == 1){
                currentCount++;
            } else {
                if (currentCount > maxSubStringCount){
                    maxSubStringCount = currentCount;
                    currentCount = 0;
                }
            }
            
        }
        return maxSubStringCount;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result =  new AmazonInterview().findMaxSubString("abcdefacbccbagfacbacer", "abc");
        System.out.println(result);

    }

}
