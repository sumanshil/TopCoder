package com.topcoder.problems.dp;

import java.util.LinkedList;

/**
 * Created by sshil on 9/29/16.
 */
public class SubtreeSum {
    long mod = 1000000007L;
    int bits = 32;
    public int getSum(int[] p, int[] x) {
        long res = 0;
        long cnt = 0;
        int n = p.length;
        LinkedList<Integer> [] children = new LinkedList[n+1];
        for(int i = 0; i <= n; i++) children[i] = new LinkedList<Integer>();
        for(int i = 0; i < n; i++) children[p[i]].add(i+1);
        long[][][] count = new long[n+1][2][bits];
        for(int k = n; k >= 0; k--) {
            for(int l = 0; l < bits; l++) {
                int map = x[k] & (1<<l);
                if(map>0) count[k][1][l] = 1;
                else count[k][0][l] = 1;
                for(Integer child : children[k]) {
                    int childi = child;
                    long a = count[k][1][l];
                    long b = count[k][0][l];
                    long c = count[childi][1][l];
                    long d = count[childi][0][l];
                    count[k][1][l] = (((a) * (d+1+c)) % mod + (b * c) % mod) % mod;
                    count[k][0][l] = (((b) * (d+1)) % mod) % mod;
                }
                //System.out.println(k + " " + l + " " + count[k][1][l]);
                cnt += count[k][1][l]+count[k][0][l];
                res  = (res + ((1L<<l) * count[k][1][l]) % mod)%mod;
            }
        }
        System.out.println(cnt);
        return (int)res;
    }
    public static void main(String[] args) {
        int[] p = {0};
        int[] x = {1, 2};
        int result = new SubtreeSum().getSum(p, x);
        System.out.println(result);
    }
}
