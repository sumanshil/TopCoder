package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/maximum-product-of-word-lengths/
public class MaximumProduct {
    public int maxProduct(String[] words) {
        int max = Integer.MIN_VALUE;
        int[] mask = new int[words.length];

        for (int i = 0 ; i < words.length ; i++) {
            char[] arr = words[i].toCharArray();

            for (int j = 0 ; j < arr.length ; j++ ) {
                mask[i] = mask[i] | ( 1 << (arr[j] - 'a'));
            }
        }

        for (int i = 0 ; i < words.length ; i++) {
            for (int j = i + 1 ; j < words.length ; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    /**
    public int maxProduct(String[] words) {
        int max = Integer.MIN_VALUE;

        for (int i = 0 ; i < words.length - 1; i++) {
            String word1 = words[i];
            Set<String> set = new HashSet<>();
            for (int k = 0 ; k < word1.length() ; k++) {
                set.add(word1.charAt(k)+"");
            }
            for (int j = i + 1; j < words.length - 1; j++) {
                String word2= words[j];
                boolean hasCommonChars = false;

                for (int l = 0 ; l < word2.length() ; l++) {
                    String s = word2.charAt(l) +"";
                    if (set.contains(s)) {
                        hasCommonChars = true;
                        break;
                    }
                }
                if (!hasCommonChars) {
                    if (word1.length() * word2.length() > max) {
                        max = Math.max(word1.length() * word2.length(), max);
                    }
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
     **/

    public static void main(String[] args) {
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        int result = new MaximumProduct().maxProduct(words);
        System.out.println(result);
    }

}
