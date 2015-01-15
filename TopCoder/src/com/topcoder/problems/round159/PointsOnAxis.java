package com.topcoder.problems.round159;

import java.util.*;

public class PointsOnAxis
{
  int N;
  int[] ret;
  public int[] findPoints(int[] distances) {
    ret = null;
    int n = distances.length;
    N = 1;
    for (;;) {
      if (N*(N - 1)/2 == n) break;
      N++;
    }
    int[] a = new int[2];
    a[0] = 0;
    Arrays.sort(distances);
    a[1] = distances[n - 1];
    int[] d = new int[n - 1];
    System.arraycopy(distances, 0, d, 0, d.length);
    go(d, a);
    if (ret == null) return new int[0];
    return ret;
  }
  boolean better(int[] a, int[] b) {
    if (b == null) return true;
    for (int i = 0; i < a.length; i++) {
      if (a[i] < b[i]) return true;
      if (b[i] < a[i]) return false;
    }
    return false;
  }
  void go(int[] d, int[] a) {
    if (d.length == 0) {
      int[] b = new int[a.length];
      System.arraycopy(a, 0, b, 0, a.length);
      Arrays.sort(b);
      if (better(b, ret)) {
        ret = b;
      }
      return;
    }
    int dis = d[d.length - 1];
    int newp = dis;
    int[] newa = new int[a.length + 1];
    System.arraycopy(a, 0, newa, 0, a.length);
    int[] newd = new int[d.length - 1];
    System.arraycopy(d, 0, newd, 0, d.length-1);
    newa[newa.length - 1] = newp;
    boolean ok = true;
    for (int i = 1; i < a.length; i++) {
      int curDis = Math.abs(newp - a[i]);
      int idx = find(newd, curDis);
      if (idx == -1) {
        ok = false;
        break;
      }
      for (int j = idx; j < newd.length - 1; j++) {
        newd[j] = newd[j+1];
      }
    }
    if (ok) {
      int n1 = a.length;
      int newdLength = d.length - n1;
      int[] newnewd = new int[newdLength];
      System.arraycopy(newd, 0, newnewd, 0, newdLength);
      Arrays.sort(newnewd);
      go(newnewd, newa);
    }
    
    newp = a[1] - dis;
    newa = new int[a.length + 1];
    System.arraycopy(a, 0, newa, 0, a.length);
    newd = new int[d.length - 1];
    System.arraycopy(d, 0, newd, 0, d.length-1);
    newa[newa.length - 1] = newp;
    ok = true;
    for (int i = 0; i < a.length; i++) {
      if (i == 1) continue;
      int curDis = Math.abs(newp - a[i]);
      int idx = find(newd, curDis);
      if (idx == -1) {
        ok = false;
        break;
      }
      for (int j = idx; j < newd.length - 1; j++) {
        newd[j] = newd[j+1];
      }
    }
    if (ok) {
      int n1 = a.length;
      int newdLength = d.length - n1;
      int[] newnewd = new int[newdLength];
      Arrays.sort(newnewd);
      System.arraycopy(newd, 0, newnewd, 0, newdLength);
      go(newnewd, newa);
    }    
  }
  
  int find(int[] a, int b) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == b) return i;
    }
    return -1;
  }
 
  void display(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
      if (i != a.length - 1) {
        System.out.print('\t');
      }
    }
    System.out.println();
  }
 
  void display(int[][] a) {
    System.out.println("---------------------------------------------------------");
    for (int i = 0; i < a.length; i++) {
      display(a[i]);
    }
    System.out.println("---------------------------------------------------------");
  }
 
  void display(boolean[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
      if (i != a.length - 1) {
        System.out.print('\t');
      }
    }
    System.out.println();
  }
 
  void display(boolean[][] a) {
    System.out.println("---------------------------------------------------------");
    for (int i = 0; i < a.length; i++) {
      display(a[i]);
    }
    System.out.println("---------------------------------------------------------");
  }
 
  public static void main(String[] args)
  {
	new PointsOnAxis().findPoints(new int[]{5, 2, 1, 6, 2, 3, 3, 4, 5, 6, 3, 9, 1, 4, 1});
  }
}
 