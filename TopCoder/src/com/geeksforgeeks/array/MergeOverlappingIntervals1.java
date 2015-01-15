package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//http://www.geeksforgeeks.org/merging-intervals/
public class MergeOverlappingIntervals1 {
    static class Pair implements Comparable<Pair>{
        int low;
        int high;
        public Pair(int low, int high){
            this.low = low;
            this.high = high;
        }
        
        public String toString(){
            return "{"+low+","+high+"}";
        }

        public int compareTo(Pair o) {            
            return this.low > o.low ? 1 : -1;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Pair> list = new ArrayList<Pair>();
//        list.add(new Pair(1,3));
//        list.add(new Pair(2,4));
//        list.add(new Pair(5,7));
//        list.add(new Pair(6,8));
//        list.add(new Pair(6,8));
//        list.add(new Pair(1,9));
//        list.add(new Pair(2,4));
//        list.add(new Pair(4,7));
//        list.add(new Pair(6,8));
//        list.add(new Pair(1,3));
//        list.add(new Pair(2,4));
//        list.add(new Pair(4,7));
        list.add(new Pair(1,3));
        list.add(new Pair(7,9));
        list.add(new Pair(4,6));
        list.add(new Pair(10,13));
        
        Collections.sort(list);
        
        Stack<Pair> stack = new Stack<Pair>();
        
        for(Pair pair : list){
            if (stack.isEmpty()){
                stack.push(pair);
            } else {
                Pair pairAtTop = stack.pop();
                if (pair.high < pairAtTop.high){
                    stack.push(pairAtTop);
                } else if (pair.low < pairAtTop.high){
                    pairAtTop.high = pair.high;
                    stack.push(pairAtTop);
                } else {
                    stack.push(pairAtTop);
                    stack.push(pair);
                }
            }
        }
        
        while(!stack.isEmpty()){
            Pair pair = stack.pop();
            System.out.println(pair);
        }
        
    }

}
