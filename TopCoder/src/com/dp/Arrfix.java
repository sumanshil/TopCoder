package com.dp;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sshil on 8/13/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=14361
public class Arrfix {

    public void find(int[] A, int[] B, int[] F) {
        boolean[] used = new boolean[F.length];
        AtomicInteger fLeft = new AtomicInteger(used.length);
        int difference = 0;
        for ( int i = 0 ; i < A.length ; i++ ){
            if (A[i] != B[i]) {
                Optional<Integer> optInt = findIndex(used, B[i], F);
                optInt.ifPresent(e -> {
                    used[e] = true;
                    fLeft.decrementAndGet();});
                if (!optInt.isPresent()){
                    difference++;
                }
            }
        }

        if (fLeft.get() > 0) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) {
                    Optional<Integer> optInt = findIndex(used, B[i], F);
                    optInt.ifPresent(e -> {
                        used[e] = true;
                        fLeft.decrementAndGet();});
                }
                if (fLeft.get() == 0){
                    break;
                }
            }
        }
        System.out.println(Math.max(difference, fLeft.get()));
    }

    private Optional<Integer> findIndex(final boolean[] used, int element, int[] f) {
        AtomicInteger integer =  new AtomicInteger(-1);

        OptionalInt optional = Arrays.stream(f)
                                     .filter(e -> !used[integer.incrementAndGet()])
                                     .filter(e -> e == element).findFirst();
        if (optional.isPresent()) {
            used[integer.incrementAndGet()] = true;
            return Optional.of(optional.getAsInt());
        } else {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {

    }
}


