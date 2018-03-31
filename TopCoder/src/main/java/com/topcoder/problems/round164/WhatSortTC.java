package com.topcoder.problems.round164;

import java.util.Arrays;
import java.util.Comparator;

public class WhatSortTC implements Comparator 
{ 
    String[] name; 
    int[] age,wt; 
    int[] sort = {0,0,0}; 
    boolean[] used = new boolean[3]; 
    int n; 
    String ord = "NAW"; 
    String res = null; 
    public int compare( Object ao, Object bo ) 
    { 
      int t = 0; 
      int a = ((Integer)ao).intValue(); 
      int b = ((Integer)bo).intValue(); 
      for (int i=0;i<3;i++) { 
        char s = ord.charAt(sort[i]); 
        if (s == 'N') 
          t = name[a].compareTo(name[b]); 
        else if (s == 'A') 
          t = age[a] - age[b]; 
        else if (s == 'W') 
          t = wt[b] - wt[a]; 
        if (t != 0) return t; 
      } 
      return 0; 
    } 
    void doit(int pos) 
    { 
      if (pos == 3) { 
        Integer[] test = new Integer[n]; 
        for (int i=0;i<n;i++) 
          test[i] = new Integer(i); 
        Arrays.sort(test,this); 
        boolean ok = true; 
        for (int i=0;i<n;i++) 
         if (test[i].intValue() != i) ok = false; 
        if (ok) { 
          if (res != null) 
            res = "IND"; 
          else 
          { 
            res = ""; 
            for (int i=0;i<3;i++) 
              res += ord.charAt(sort[i]); 
          } 
        } 
        return; 
      } 
      for (int t=0;t<3;t++) { 
        if (!used[t]) { 
          used[t] = true; 
          sort[pos] = t; 
          doit(pos+1); 
          used[t] = false; 
        } 
      } 
    } 
    public String sortType(String[] name, int[] age, int[] wt) { 
            n = name.length; 
            this.name = name; 
            this.age=age; 
            this.wt=wt; 
            doit(0); 
            return res == null ? "NOT" : res; 
    } 

    public static void main(String[] args)
    {
        String[] name = {"BOB","BOB","DAVE","DAVE"};
        int[]  age = {22, 35, 35, 30};
        int[] weight= {122, 122, 195,  190};
        String result = new WhatSortTC().sortType(name, age, weight);
        System.out.println(result);
    }
  } 
