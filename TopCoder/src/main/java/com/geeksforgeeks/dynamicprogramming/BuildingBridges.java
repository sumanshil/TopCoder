package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

///http://www.geeksforgeeks.org/dynamic-programming-set-14-variations-of-lis/
public class BuildingBridges {
    static class Bridge implements Comparable<Bridge>{
        int source;
        int destination;
        
        public Bridge(int source, int destination){
            this.source  = source;
            this.destination = destination;
        }

        public int compareTo(Bridge bridge) {           
            return this.source > bridge.source ? 1:0;
        }
    }
    
    public void lis(List<Bridge> list){
        Collections.sort(list);
        int[] lis = new int[list.size()];
        for(int i = 0 ; i < lis.length ; i++){
            lis[i] = 1;
        }
        for(int i = 1 ; i < list.size() ; i++){
            for(int j = 0 ; j < i ; j++){
                if (list.get(j).destination < list.get(i).destination){
                    if (lis[j]+1 > lis[i]){
                        lis[i] = lis[j]+1;
                    }
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        
        for(int i = 0 ; i< lis.length; i++){
            if (lis[i] > max){
                max = lis[i];
            }
        }
        
        System.out.println(max);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Bridge> list = new ArrayList<Bridge>();        
        list.add(new Bridge(1, 2));
        list.add(new Bridge(2, 6));
        list.add(new Bridge(3, 4));
        list.add(new Bridge(4, 3));
        
        list.add(new Bridge(5, 5));
        list.add(new Bridge(6, 7));
        list.add(new Bridge(7, 8));
        list.add(new Bridge(8, 1));
        new BuildingBridges().lis(list);
    }

}
