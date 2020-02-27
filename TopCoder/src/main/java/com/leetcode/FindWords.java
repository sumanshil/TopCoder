package com.leetcode;

public class FindWords {

    public boolean findWord(String source, String target) {
        return recursive(source, target);
    }

    private boolean recursive(String source, String target) {
        if (source.length() > target.length()) {
            return false;
        }

        char c = source.charAt(0);

        for (int i = 0 ; i < target.length() ; i++) {
            if (c == target.charAt(i)) {
                if (source.length() > 1) {
                    return recursive(source.substring(1), target.substring(1));
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String source = "abc";
        String target = "zzzaaazzzbeeec";

        boolean res = new FindWords().findWord(source, target);
        System.out.println(res);
    }
}
