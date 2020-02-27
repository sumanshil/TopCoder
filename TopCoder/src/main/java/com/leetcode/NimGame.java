package com.leetcode;

public class NimGame {

    public boolean canWinNim(int n) {
        boolean result = recursive(n , true);
        return result;
    }

    private boolean recursive(int n, boolean picks) {
        if (n < 0) {
            return false;
        }
        if (n == 0 && !picks) {
            return true;
        }
        if (n == 0) {
            return true;
        }

        if (!picks && (n == 1 || n == 2||  n == 3)) {
            return false;
        } else if (picks && (n == 1 || n == 2 || n == 3) ) {
            return true;
        }
        for (int i = 1 ; i <= 3 ; i++) {
            if (recursive(n - i, !picks)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 8;
        boolean result = new NimGame().canWinNim(n);
        System.out.println(result);
    }
}
