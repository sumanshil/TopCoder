package com.topcoder.problems.round149;

import java.util.*;
public class Pricing {
  public int maxSales(int[] p) {
    Arrays.sort(p);
    int e = p.length;
    int ret = -1;
    for (int a=0;a<p.length;a++) {
      for (int b=a;b<p.length;b++) {
        for (int c=b;c<p.length;c++) {
          for (int d=c;d<p.length;d++) {
            if (a != b && b != c && c != d) {
              ret = Math.max(ret,(p[d]*(e-d))+(p[c]*(d-c))+(p[b]*(c-b))+(p[a]*(b-a)));
            }
          }  
        }  
      }      
    }
    if (e < 4) {
      for (int f=0;f<e;f++) {
        ret += p[f];
      }
      ret++;
    }
    return ret;
  }
  
  public static void main(String[] args)
  {
	  int result = new Pricing().maxSales(new int[]{130,110,90,13,6,5,4,3,0});
	  System.out.println(result);
  }
  
}
