package com.topcoder.problems.round172;

import java.util.*;

/**
 * Created by sshil on 6/24/17.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1850&rd=4665
public class Fifteen {
    public String outcome(int[] moves){
        List<Integer> patron = new ArrayList<>();
        Set<Integer> alreadyCovered = new HashSet<>();
        for (int i = 0 ; i < moves.length ; i++) {
            if (i % 2 != 0){
                patron.add(moves[i]);
            }
            alreadyCovered.add(moves[i]);
        }

        Set<Integer> remaining = new HashSet<>();
        for ( int i = 1 ; i <= 9 ; i++) {
            if (!alreadyCovered.contains(i)){
                remaining.add(i);
            }
        }
        Collections.sort(patron);

        for (int i : remaining) {
            int needed = 15 - i;
            if (patronHasNeededPairFor(needed, patron)){
                return "WIN "+i;
            }
        }
        return "LOSE";
    }

    private boolean patronHasNeededPairFor(int needed, List<Integer> patron) {
        int indexFirst = 0;
        int indexLast = patron.size()-1;
        while (indexFirst < indexLast) {
            int currentSum = patron.get(indexFirst) + patron.get(indexLast);
            if (currentSum == needed){
                return true;
            } else if (currentSum > needed){
                indexLast -= 1;
            } else {
                indexFirst += 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] arr = {6, 3, 7, 8, 1};
        String result = new Fifteen().outcome(arr);
        System.out.println(result);

    }
}
