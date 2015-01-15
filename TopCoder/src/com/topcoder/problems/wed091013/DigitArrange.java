package com.topcoder.problems.wed091013;

import java.util.ArrayList;

public class DigitArrange
{
    public int targetDiff(int source, int target){
        String s1 = "" + source;
        int x = 0;
        while (s1.charAt(x) == '0') x++;
        String s = s1.substring(x);
     
        int best = -1;
        ArrayList l = new ArrayList();
        arrange("", s, l);
        for (int i = 0; i < l.size(); i++) {
          for (int j = 0; j < l.size(); j++) {
            int a = Integer.parseInt((String) l.get(i));
            int b = Integer.parseInt((String) l.get(j));
            if (a != b) {
              int diff = a-b;
              if (diff <= target) {
                if (diff > best) best = diff;
              }
            }
          }
         }
         return best;
  }
 
  private void arrange(String number, String left, ArrayList list)
  {
    if (left.length() == 0)
        list.add(number);
    else {
      for (int i = 0; i < left.length(); i++) {
        String n2 = number + left.charAt(i);
        String l2 = "";
        for (int j = 0; j < i; j++) {
          l2 += left.charAt(j);
        }
        for (int j = i+1; j < left.length(); j++) {
          l2 += left.charAt(j);
        }
        arrange(n2, l2, list);
      }
    }
  }
  
  public static void main(String[] args){
      new DigitArrange().targetDiff(450,9);
  }
}
