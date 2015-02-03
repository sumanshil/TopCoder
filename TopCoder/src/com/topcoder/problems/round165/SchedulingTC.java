package com.topcoder.problems.round165;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
public class SchedulingTC {
  int[][] before;
  int[] times;
  int mask;
  long startedT;
  int check = 10000;
  boolean bail;
  
  public int fastest(String[] dag) {
    startedT = System.currentTimeMillis();
    before = new int[dag.length][];
    times = new int[dag.length];
    for (int i = 0; i < dag.length; ++i) {
      String[] aa = dag[i].split("[:,]");
      before[i] = new int[aa.length - 1];
      times[i] = Integer.parseInt(aa[0]);
      for (int j = 1; j < aa.length; ++j) {
        before[i][j-1] = Integer.parseInt(aa[j]);
      }
    }
    mask = (1 << dag.length) - 1;
    return time(0, 0, -1, -1);
  }
  
  Map cache = new HashMap();
  
  int time(int started, int finished, int parI, int parT) {
    String key = started + " " + finished + " " + parI + " " + parT;
    Integer oo = (Integer) cache.get(key);
    if (oo == null) {
      if (check-- == 0) {
        long now = System.currentTimeMillis();
        if (now - startedT > 7000) {
          System.out.println("bailing after " + (now - startedT));
          bail = true;
        }
        check = 10000;
      }
      if (bail) return 1000;
      oo = new Integer(time2(started, finished, parI, parT));
      cache.put(key, oo);
    }
    return oo.intValue();
  }
  
  int time2(int started, int finished, int parI, int parT) {
    if (finished == mask) return 0;
    
    int best = Integer.MAX_VALUE;
    if (parI >= 0) {
      LOOP: for (int i = 0; i < before.length; ++i) {
        if (((started >> i) & 1) != 0) continue;
        for (int j = before[i].length - 1; j >= 0; --j) {
          if (((finished >> before[i][j]) & 1) == 0) continue LOOP;
        }
        int j = i;
        int bit = 1 << j;
        int finish = times[j];
        if (finish < parT) {
          best = Math.min(best, finish + time(started | bit, finished | bit, parI, parT - finish));
        }
        else if (finish == parT) {
          best = Math.min(best, finish + time(started | bit, started | bit, -1, -1));
        }
        else {
          best = Math.min(best, parT + time(started | bit, started, j, finish - parT));
        }
      }
      
      // consider leaving CPU2 empty
      best = Math.min(best, parT + time(started, started, -1, -1));
    }
    else {
      LOOP: for (int i = 0; i < before.length; ++i) {
        if (((started >> i) & 1) != 0) continue;
        for (int j = before[i].length - 1; j >= 0; --j) {
          if (((finished >> before[i][j]) & 1) == 0) continue LOOP;
        }
        int j = i;
        int bit = 1 << j;
        int finish = times[j];
        best = Math.min(best, time(started | bit, started, j, finish));        
      }
    }
    return best;
  }
    
//  public final static void main(String[] args)
//  {
//    try {
//      validateExample("1", new Scheduling().fastest(new String[] {"3:","2:","4:","7:0,1,2"}), 12, "fastest(new String[] {\"3:\",\"2:\",\"4:\",\"7:0,1,2\"})");
//      validateExample("2", new Scheduling().fastest(new String[] {"9:","8:","6:","4:","7:","7:0,1,2,3,4","3:2,3"}), 24, "fastest(new String[] {\"9:\",\"8:\",\"6:\",\"4:\",\"7:\",\"7:0,1,2,3,4\",\"3:2,3\"})");
//      validateExample("3", new Scheduling().fastest(new String[] { "10:", "5:", "5:1", "5:1", "5:2,3" }), 20, "fastest(new String[] { \"10:\", \"5:\", \"5:1\", \"5:1\", \"5:2,3\" })");
//      validateExample("4", new Scheduling().fastest(new String[] {"1:","2:","4:","8:","6:","3:","7:","5:","9:","5:","10:","3:"}), 32, "fastest(new String[] {\"1:\",\"2:\",\"4:\",\"8:\",\"6:\",\"3:\",\"7:\",\"5:\",\"9:\",\"5:\",\"10:\",\"3:\"})");
//    } catch (Exception e) {
//      System.out.println(e);
//      e.printStackTrace(System.out);
//    }
//  }
 
  private static void validateExample(String exampleNum, int returned, int expected, String methodInvocation) {
    print(exampleNum);
    print("  ");
    if (!eq(returned,expected)) {
      print("FAIL");
    } else {
      print("SUCCESS");
    }
    print(" ");  
    print(methodInvocation);
    print("\tGot:[");
    print(returned);
    print("]\t");
    print("Expected:[");
    print(expected);
    print("]");
    print("\n");
  }
 
  public static void print(boolean a) {
    System.out.print(a);
  }
 
  public static void print(long a) {
    System.out.print(a);
  }
 
  public static void print(int a) {
    System.out.print(a);
  }
 
  public static void print(char a) {
    System.out.print(a);
  }
 
  public static void print(String a) {
    System.out.print(a);
  }
 
  public static void print(int[] a) {
    if (a==null) {System.out.print("null"); return;} 
    for (int i=0; i<a.length; i++) System.out.print(a[i]+(i<a.length-1?",":"")); 
  }
 
  public static void print(int[][] a) {
    if (a==null) {System.out.print("null"); return;} 
    for (int i=0; i<a.length; i++) { print(a[i]); print("\n"); }
  }
 
  public static void print(String[] a) {
    if (a==null) {System.out.print("null"); return;} 
    for (int i=0; i<a.length; i++) System.out.print(a[i]+(i<a.length-1?",":"")); 
  }
 
  public static boolean eq(boolean a, boolean b) {
    return a==b;
  }
 
  public static boolean eq(long a, long b) {
    return a==b;
  }
 
  public static boolean eq(int a, int b) {
    return a==b;
  }
 
  public static boolean eq(char a, char b) {
    return a==b;
  }
 
  public static boolean eq(String a, String b) {
    return a.equals(b);
  }
 
  public static boolean eq(int[] a, int[] b) { return Arrays.equals(a,b); }
  public static boolean eq(String[] a, String[] b) { return Arrays.equals(a,b); }
  public static void main(String[] args)
  {
      new SchedulingTC().fastest(new String[]{"3:", "2:", "4:", "7:0,1,2"});  
  }
}