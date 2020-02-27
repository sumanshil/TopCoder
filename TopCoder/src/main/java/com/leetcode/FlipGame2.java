package com.leetcode;

//https://leetcode.com/problems/flip-game-ii/
public class FlipGame2 {

    public boolean canWin(String s) {

        for (int i = 0 ; i < s.length() - 2 ; i++) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                String oppString = s.substring(0,i)+"--"+s.substring(i+2);
                if (!canWin(oppString)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "+++++";
        boolean res = new FlipGame2().canWin(s);
        System.out.println(res);
    }
}
