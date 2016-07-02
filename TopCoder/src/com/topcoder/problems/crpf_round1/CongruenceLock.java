package com.topcoder.problems.crpf_round1;

import java.util.Arrays;

/**
 * Created by sshil on 6/15/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1936&rd=4656
public class CongruenceLock {
    /*
    private int minPush = Integer.MAX_VALUE;
    public int minPushes(int current, int target, int[] buttons) {
        findRecursive(current, target, buttons, 0);
        return minPush;
    }

    private void findRecursive(int current, int target, int[] buttons, int currentPushCount) {
        if (current == target) {
            if (currentPushCount < minPush){
                minPush = currentPushCount;
            }
            return;
        }
        if (currentPushCount > 10){
            return;
        }
        for ( int i = 0 ; i < buttons.length ; i++){
            findRecursive((current+buttons[i])%100000, target, buttons, currentPushCount+1);
        }
    }

    */

    public  int minPushes(int current, int target, int[] buttons){
        int[] dp = new int[100000];
        Arrays.fill(dp, -1);
        dp[current] = 0;
        for ( int i = 0 ; i < buttons.length ; i++) {
            for ( int j = 0 ; j < dp.length ; j++) {
                int x = j;
                if (dp[x] == -1) continue;
                int next = (x+buttons[i])%100000;
                while (dp[next] == -1 || dp[next] > dp[x]+1) {
                    dp[next] = dp[x]+1;
                    x = next;
                    next = (next+buttons[i])%100000;
                }
            }
        }
        return dp[target];
    }

    public int minPushes1(int current, int target, int[] buttons){
        int[] min = new int[100000];
        Arrays.fill(min, Integer.MAX_VALUE);
        int[] q = new int[100000];
        min[current] = 0;
        int get = 0;
        int put = 0;
        q[put++] = current;
        if (current == target){
            return 0;
        }

        while(get < put){
            current = q[get++];
            int m = min[current];
            for ( int i = 0 ; i < buttons.length ; i++) {
                int next = (current + buttons[i]) % 100000;
                if (min[next] == Integer.MAX_VALUE){
                    min[next] = m+1;
                    if (next == target) return min[next];
                    q[put++] = next;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int current = 36557;
        int target = 9326;
        int[] buttons = {17231};
        int result = new CongruenceLock().minPushes1(current, target, buttons);
        System.out.println(result);
    }
}
