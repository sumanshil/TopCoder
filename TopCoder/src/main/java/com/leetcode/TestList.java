package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class TestList {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(Arrays.asList(1,2,3,4));

        for (int i = 0 ; i < list.size() ; i++) {
            if ((list.get(i) % 2 == 0)){
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
