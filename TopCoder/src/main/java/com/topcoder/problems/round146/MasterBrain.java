package com.topcoder.problems.round146;
//http://community.topcoder.com/stat?c=problem_statement&pm=1541&rd=4535
public class MasterBrain 
{
	  boolean ddd = false ;
	  String check(String code, String res) 
	  {
	      char[] b1 = code.toCharArray() ;
	      char[] b2 = res.toCharArray() ;
	      int w = 0, b = 0 ;
	      for (int i=0; i<4; i++) 
	      {
	          if (b1[i] == b2[i]) 
	          {
	              b1[i] = 100 ;
	              b2[i] = 101 ;
	              b++ ;
	          }
	      }
	      for (int i=0; i<4; i++) 
	      {
	          for (int j=0; j<4; j++) 
	          {
	              if (b1[i] == b2[j]) 
	              {
	                  b1[i] = 100 ;
	                  b2[j] = 101 ;
	                  w++ ;
	              }
	          }
	      }
	    return "" + b + "b " + w + "w" ;
	  }
	  
	  
	  public int possibleSecrets(String[] guesses, String[] results) 
	  {
	      int i, j, k ;
	      int p0, p1, p2, p3 ;
	      int s = 0 ;
	      for (p0=0; p0<7; p0++)
	    	  for (p1=0; p1<7; p1++)
	    		  for (p2=0; p2<7; p2++)
	    			  for (p3=0; p3<7; p3++) 
	    			  {
	                      int lies = 0 ;
	                      String code = "" + (p0 + 1) + "" + (p1 + 1) + "" + (p2 + 1) + "" + (p3 + 1) ;
	                      for (i=0; i<guesses.length; i++) 
	                      {
	                          if (!check(code, guesses[i]).equals(results[i])) 
	                          {
	                              lies++ ;
	                          }
	                      }
	                      if (lies == 1) 
	                      {
	                          s++ ;
	                      }
	                  }
	      return s ;
	  }

	  public static void main(String[] args)
	  {
		  int result = new MasterBrain().possibleSecrets(new String[]{"6172","6162","3617"}, new String[]{"3b 0w","2b 1w","0b 3w"});
		  System.out.println(result);
	  }
}
