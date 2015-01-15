package com.topcoder.problems.round16;

import java.util.*;

 
public class BasePerm {
  boolean eq(int a[], int b[]) {
    for(int i = 0; i < 16; i++)
      if(a[i] != b[i]) return false;
    return true;
  }
 
  int map(int x, int b, int map[]) {
    if(x == 0) { map[0]++; return 1; }
    int n = 0;
    while(x > 0) {
      int y = x % b;
      map[y]++;
      x /= b;
      n++;
    }
    return n;
  }
 
  public int getNthSame(int nth, int b1, int b2) {
    int x = 0;
    for(int i = 0; i < nth - 1; i++) {
      while(true) {
        x++;
        int map1[] = new int[16];
        int map2[] = new int[16];
        int n1 = map(x, b1, map1);
        int n2 = map(x, b2, map2);
        if(eq(map1, map2)) break;
        n1 -= n2; 
        if(n1 < 0) n1 = -n1;
        if(n1 >= 2) return -1;
      }
    }
    return x;
  }
  
  public static void main(String[] args){
      int result = new BasePerm().getNthSame(20, 5, 6);
      System.out.println(result);
  }
}
