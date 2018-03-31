package com.topcoder.problems.round170;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 4/4/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1915&rd=4655
public class RecurrenceRelation {
    Map<Integer, Long> map = new HashMap<>();

    public int moduloTen(int[] coefficients, int[] initial, int N){
        int index = initial.length;
        for ( int j = index ; j <= N ; j++){
            calculateRecursive(coefficients, j, coefficients.length, initial);
        }
        long result;
        if (N > initial.length) {
            result = map.get(N);
        } else {
            result = initial[N];
        }
        if (result > 0) {
            result = result % 10;
        } else {
            result = ((10 - ((-1*result) % 10)) % 10);
        }
        System.out.println(result);
        return (int)result;
    }

    private long calculateRecursive(int[] coefficients, int currentN, int k, int[] initial) {
        if (map.containsKey(currentN)){
            return map.get(currentN);
        }
        if (currentN < initial.length){
            return initial[currentN];
        }
        int index = 1;
        long result = 0;
        for ( int i = 0 ; i < k  ; i++ ){
            int kIndex = k - index;
            int nIndex = currentN - index;
            if (nIndex < 0){
                break;
            }
            long x = calculateRecursive(coefficients, nIndex, k, initial);
            result += coefficients[kIndex]*x;
            index++;
        }
        map.put(currentN, result);
        return result;
    }

    public static void main(String[] args) {
        int[] coefficients = {9,8,7,6,5,4,3,2,1,0};
        int[] initial = {1,2,3,4,5,6,7,8,9,10};
        int N = 100000;
        int result = new RecurrenceRelation().moduloTen(coefficients, initial, N);
        System.out.println(result);
    }
}
