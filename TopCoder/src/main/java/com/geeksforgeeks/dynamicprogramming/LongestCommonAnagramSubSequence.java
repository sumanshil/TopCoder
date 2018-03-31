package com.geeksforgeeks.dynamicprogramming;

public class LongestCommonAnagramSubSequence {

    public void find (String str1, String str2) {
        int arr1[] = new int[26];
        int arr2[] = new int[26];
        for (int i = 0 ; i < str1.length() ; i++) {
            int index = str1.charAt(i) - 'a';
            arr1[index]++;
        }

        for (int i = 0 ; i < str2.length() ; i++) {
            int index = str2.charAt(i) - 'a';
            arr2[index]++;
        }

        int result = 0;
        for (int i = 0 ; i < arr1.length ; i++) {
            result += Math.min(arr1[i], arr2[i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        String str1 = "abdacp";
        String str2 = "ckamb";
        new LongestCommonAnagramSubSequence().find(str1, str2);
    }

}
