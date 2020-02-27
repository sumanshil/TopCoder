package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/flip-game/
public class FlipGame {

    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new LinkedList<>();

        for (int i = 0 ; i <= s.length()-2 ; i++) {
            if (s.charAt(i) != '+' || s.charAt(i+1) != '+') {
                continue;
            }
            StringBuffer stringBuffer = new StringBuffer();
            if (i-1 >= 0) {
                stringBuffer.append(s.substring(0, i));
            }
            stringBuffer.append("--");
            if ( i + 2 < s.length()) {
                stringBuffer.append(s.substring(i+2));
            }
            result.add(stringBuffer.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "+";
        List<String> result = new FlipGame().generatePossibleNextMoves(str);
        System.out.println(result);
    }
}
