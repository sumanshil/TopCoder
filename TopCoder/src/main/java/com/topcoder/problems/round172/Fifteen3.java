package com.topcoder.problems.round172;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 6/29/17.
 */
public class Fifteen3 {

    public String outcome(int[] moves) {
        List<Integer> dealer = new ArrayList<>();
        List<Integer> patron = new ArrayList<>();
        boolean[] visited = new boolean[15];
        for ( int i = 0 ; i < moves.length ; i++) {
            visited[moves[i]] = true;
            if ( i % 2 == 0){
                dealer.add(moves[i]);
            } else {
                patron.add(moves[i]);
            }
        }
        int k = dfs(moves.length, dealer, patron, visited);
        if ( k > 0){
            return "WIN "+k;
        } else {
            return "LOOSE";
        }
    }

    private int dfs(int d, List<Integer> dealer, List<Integer> patron, boolean[] visited) {
        if (win(patron)){
            return 1;
        }
        if (win(dealer)){
            return 0;
        }

        if (d == 9){
            return 0;
        }

        int ret = 0;
        for ( int i = 1 ; i <= 9 ; i++ ) {
            if (!visited[i]) {
                visited[i] = true;
                if ( d % 2 == 0){
                    List<Integer> newDealer = new ArrayList<>(dealer);
                    newDealer.add(i);
                    dealer = newDealer;
                } else {
                    List<Integer> newPatron = new ArrayList<>(patron);
                    newPatron.add(i);
                    patron = newPatron;
                }

                int k = dfs(d+1, dealer, patron, visited);

                ret = k;
                visited[i] = false;
                if ( ((d & 1) > 0) && k > 0){
                    return i;
                } else if (((d & 1) == 0) && k == 0){
                    return 0;
                }
            }
        }
        return ret;
    }

    private boolean win(List<Integer> list) {
        for ( int i = 0 ; i < list.size(); i++) {
            for ( int j = i+1 ; j < list.size() ; j++) {
                for ( int k = j+1 ; k < list.size() ; k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == 15){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, 7, 8, 1}

                ;
        String str = new Fifteen3().outcome(arr);
        System.out.println(str);
    }
}
