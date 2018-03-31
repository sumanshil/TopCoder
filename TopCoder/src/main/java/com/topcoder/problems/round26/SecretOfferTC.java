package com.topcoder.problems.round26;

import java.util.*;
 
public class SecretOfferTC
{
  public static void main(String[] args){
	 String message = "fisivex";
	 long result = new SecretOfferTC().translate(message);
	 System.out.println(result);
  }
	
  public long translate(String s)
  {
    String result = "0";
    s = s.toLowerCase();
    while (true) {
      X x = new X();
 
      int pos = 0;
      boolean found = false;
      while (pos < s.length()) {
        char ch = s.charAt(pos);
        x.letter(ch, pos);
        int done = x.done();
        if (done != -1) {
          result += done;
          for (int j = (x.pos[done].size()-1); j >= 0; j--) {
            int xx = ((Integer) x.pos[done].get(j)).intValue();
            s = s.substring(0, xx) + s.substring(xx+1);
//System.out.println(s);
          }
          found = true;
          break;
        }
        pos++;
      }
      if (!found) break;
    }
 
    return (new Long(result)).longValue();
 
  }
 
  class X
  {
    public String[] nums = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    public ArrayList[] pos = new ArrayList[10];
 
    public X()
    {
      for (int i = 0; i < 10; i++) {
        pos[i] = new ArrayList();
      }
    }
 
    public int done()
    {
      for (int i = 0; i < 10; i++) {
        if (nums[i].length() == 0) return i;
      }
      return -1;
    }
 
    public void letter(char c, int posn)
    {
      for (int i = 0; i < 10; i++) {
        if (nums[i].length() > 0) {
        if (nums[i].charAt(0) == c) {
          nums[i] = nums[i].substring(1);
          pos[i].add(new Integer(posn));
        }
        }
      }
    }
  }
}
