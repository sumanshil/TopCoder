package com.topcoder.problems.round20;

import java.util.ArrayList;
 
public class RepsTC
{
   int[] reps;
   double[] share;
 
   public ArrayList representatives( ArrayList a, int x )
   {
      reps = new int[a.size()];
      share = new double[a.size()];
 
      double total_pop = 0.0;
      
      for ( int i = 0; i < a.size(); ++i )
      {
         total_pop += ((Integer)a.get(i)).intValue();
      }
 
      for ( int i = 0; i < a.size(); ++i )
      {
         share[i] = 1.0 * x * ((Integer)a.get(i)).intValue() / total_pop;
      }      
 
      for ( int i = 0; i < x; ++i )
      {
         reps[i] = (int)Math.floor(share[i]);
         x -= reps[i];
      }
 
      for ( int i = 0; i < x; ++i )
      {
         addRep();
      }
 
      ArrayList ret = new ArrayList();
      for ( int i = 0; i < a.size(); ++i )
      {
         ret.add( new Integer( reps[i] ) );
      }
 
      return ret;
   }
 
   void addRep()
   {
      double max_gain = -100.0;
      int max = -1;
 
      for ( int i = 0; i < reps.length; ++i )
      {
         double o = Math.abs( reps[i] - share[i] );
         double n = Math.abs( reps[i] + 1 - share[i] );
 
         double gain = o - n;
 
         if ( gain > max_gain )
         {
            max_gain = gain;
            max = i;
         }
      }
 
      reps[max]++;
   }
 
   static int cmp( int a, int b )
   {
     if ( a < b ) return -1;
     if ( a > b ) return 1;
     else return 0;
   }
 
   public static void main( final String[] args )
   {
      final RepsTC x = new RepsTC();
 
      ArrayList a1 = new ArrayList();
      a1.add( new Integer( 10 ) );
      a1.add( new Integer( 20 ) );
      a1.add( new Integer( 30 ) );
      System.out.println( x.representatives( a1, 6 ) );
 
      ArrayList a2 = new ArrayList();
      a2.add( new Integer( 10 ) );
      a2.add( new Integer( 20 ) );
      a2.add( new Integer( 30 ) );
      System.out.println( x.representatives( a2, 7 ) );
 
      ArrayList a3 = new ArrayList();
      a3.add( new Integer( 10 ) );
      a3.add( new Integer( 20 ) );
      a3.add( new Integer( 30 ) );
      System.out.println( x.representatives( a3, 60 ) );
 
      ArrayList a4 = new ArrayList();
      a4.add( new Integer( 6 ) );
      a4.add( new Integer( 28 ) );
      a4.add( new Integer( 496 ) );
      a4.add( new Integer( 8128 ) );
      a4.add( new Integer( 33550336 ) );
      System.out.println( x.representatives( a4, 6972593 ) );
 
      ArrayList a5 = new ArrayList();
      a5.add( new Integer( 10 ) );
      a5.add( new Integer( 10 ) );
      a5.add( new Integer( 10 ) );
      System.out.println( x.representatives( a5, 5 ) );
 
      ArrayList a6 = new ArrayList();
      a6.add( new Integer( 1 ) );
      a6.add( new Integer( 1 ) );
      a6.add( new Integer( 1000 ) );
      System.out.println( x.representatives( a6, 5 ) );
   }
}