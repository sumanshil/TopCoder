package com.leetcode.linkedlist;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

//https://leetcode.com/problems/baseball-game/
public class BaseballGame {
    public int calPoints(String[] ops) {
        int result = 0;
        List<Integer> list = new LinkedList<>();
        for (String str : ops) {
            try {
                int rt = Integer.parseInt(str);
                result += rt;
                list.add(rt);
            } catch (Exception e) {
                if ("+".equals(str)) {
                    int value = 0;
                    if (list.size() > 0) {
                        value += list.get(list.size()-1);
                    }
                    if (list.size() > 1) {
                        value += list.get(list.size()-2);
                    }
                    result += value;
                    list.add(value);
                } else if ("D".equals(str)) {
                    if (list.size() > 0) {
                        int value = list.get(list.size()-1);
                        result += value*2;
                        list.add(value*2);
                    }
                } else if ("C".equals(str)) {
                    if (list.size() > 0) {
                        int value = list.get(list.size()-1);
                        result -= value;
                        list.remove(list.size()-1);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] str = {"5","-2","4","C","D","9","+","+"};
        int result = new BaseballGame().calPoints(str);
        System.out.println(result);
    }
}
