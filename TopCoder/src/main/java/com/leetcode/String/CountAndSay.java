package com.leetcode.String;

public class CountAndSay {
    public String countAndSay(int n) {
        String s1 = "1";
        for (int i = 2; i <=n ; i++) {
            char c1 = s1.charAt(0);
            int count = 1;
            StringBuffer stringBuffer = new StringBuffer();
            for ( int j = 1 ; j < s1.length() ; j++) {
                if (s1.charAt(j) == c1) {
                    count++;
                } else {
                    stringBuffer.append(count+(c1+""));
                    c1 = s1.charAt(j);
                    count = 1;
                }
            }
            stringBuffer.append(count+(c1+""));
            s1 = stringBuffer.toString();
        }
        return s1;
    }

    public static void main(String[] args) {
        int n = 5;
        String res = new CountAndSay().countAndSay(n);
        System.out.println(res);
    }
}
