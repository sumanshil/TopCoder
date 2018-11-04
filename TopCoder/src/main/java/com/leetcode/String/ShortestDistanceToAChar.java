package com.leetcode.String;

//https://leetcode.com/problems/shortest-distance-to-a-character/discuss/125788/C++JavaPython-2-Pass-with-Explanation
public class ShortestDistanceToAChar {

    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        int pos = -n;

        for (int i = 0 ; i < S.length() ; i++) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = i - pos;
        }

        for ( int i = S.length() - 1 ; i >= 0 ; i--) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = Math.min(res[i], Math.abs(pos - i));
        }
        return res;
    }

    public static void main(String[] args) {
        String S = "loveleetcode";
        char C = 'e';
        int[] res = new ShortestDistanceToAChar().shortestToChar(S, C);
        for ( int i = 0 ; i < res.length ; i++) {
            System.out.println(res[i]);
        }
    }

}
