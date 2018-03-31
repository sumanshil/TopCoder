package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=60&rd=3002
public class DigitArith {
    public int best = 10000;
    public int goal, retv;
    public int closestToTarget(int source, int target) {
      best = 100000;
      goal = target;
      go(source, 0);
      return retv;
    }
    private void go(int here, int level) {
      if (here <0 || here >=10000) return;
      if (level == 3) {
        if (Math.abs(here - goal) < best) {
          best = Math.abs(here-goal);
          retv = here;
        }
        return;
      }
      int i = 0;
      int t = here;
      while (t > 0) {
         go(here + t%10, level+1);
         go(here - t%10, level+1);
         go(here * (t%10), level+1);
         if (t%10 != 0)  go(here / (t%10), level+1);
         t = t /10;
      }
    }
    
    public static void main(String[] args){
        int result = new DigitArith().closestToTarget(4, 50);
        System.out.println(result);
    }
}
