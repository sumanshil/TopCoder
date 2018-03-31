package com.topcoder.problems.round160;


import java.util.ArrayList;
import java.util.Arrays;

public class Permutation{
   String ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public String best(int n){
      int max = 1;
      for(int i = 1; i<200000 ; i++){
         if(sum(pf(i)) <= n)max = i;
      }
      int[] p = pf(max);
      int s = sum(p);
      Arrays.sort(p);
      String ret = "";
      for(int i = 0; i<n-s; i++){
         ret+=ch.charAt(0);
         ch = ch.substring(1);
      }
      for(int i = 0; i<p.length; i++){
         String st = ch.substring(0,p[i]);
         ch = ch.substring(p[i]);
         ret += st.substring(1)+st.charAt(0);
      }
      return ret;
   }
   /**
    * returns the prime factorization of n
    */
   int[] pf(int n){
      ArrayList al = new ArrayList(10);
      for(int i = 2; i*i<=n; i++){
         int push = 1;
         while(n%i==0){
            n/=i;
            push*=i;
         }
         if(push>1)
            al.add(new Integer(push));
      }
      if(n>1)al.add(new Integer(n));
      int[] ret = new int[al.size()];
   for(int i = 0; i<ret.length; i++)ret[i]=((Integer)al.get(i)).intValue();
      return ret;
   }
   int sum(int[] n){
      int ret = 0;
      for(int i = 0; i<n.length; i++)
         ret+=n[i];
      return ret;
   }
   
   public static void main(String[] args)
	{
		String result  = new Permutation().best(7);
		System.out.println(result);
		
	}
}