package com.leetcode;

//https://leetcode.com/problems/reverse-vowels-of-a-string/
public class ReverseVowel {


    public String reverseVowels(String s) {
        int lastIndex = s.length()-1;
        int firstIndex = 0;
        char[] arr = s.toCharArray();
        while (firstIndex < lastIndex) {
            while (lastIndex >= 0 && !isVowel(arr, lastIndex)) {
                lastIndex --;
            }
            while (firstIndex < s.length() && !isVowel(arr, firstIndex)) {
                firstIndex ++;
            }

            if (firstIndex < lastIndex) {
                char c = arr[firstIndex];
                arr[firstIndex] = arr[lastIndex];
                arr[lastIndex] = c;
            }
            firstIndex ++;
            lastIndex--;
        }
        return new String(arr);
    }

    private boolean isVowel(char[] s, int firstIndex) {
        return s[firstIndex] == 'a'
               || s[firstIndex] == 'e'
               || s[firstIndex] == 'i'
               || s[firstIndex] == 'o'
               || s[firstIndex] == 'u'
                || s[firstIndex] == 'A'
                || s[firstIndex] == 'E'
                || s[firstIndex] == 'I'
                || s[firstIndex] == 'O'
                || s[firstIndex] == 'U';
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String s1 = new ReverseVowel().reverseVowels(s);
        System.out.println(s1);
    }
}
