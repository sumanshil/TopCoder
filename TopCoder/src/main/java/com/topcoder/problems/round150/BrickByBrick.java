package com.topcoder.problems.round150;
//http://community.topcoder.com/stat?c=problem_solution&cr=282718&rd=4555&pm=1751
public class BrickByBrick 
{
	public int timeToClear(String[] map) 
	{ /***************************************************************************/
	    int i , j , k , l , m , n , count ;
	    int ans ; String [ ] arg = map ;
	    int [][] dd = new int [ arg.length + 2 ] [ arg[0].length()+2] ;
	    int pad = 1 ;
	    int bc = 0 ;
	    for ( i = 0 ; i < arg.length + pad*2 ; i ++ )
	        for ( j = 0 ; j < arg[0].length() + pad *2; j ++ )
	            dd [ i ] [ j ] = 2 ;
	    for ( i = 0 ; i < arg.length ; i ++ )
	        for ( j = 0 ; j < arg[0].length() ; j ++ )
	        {
	            if (arg[i].charAt(j)=='.')
	            	dd [ i + pad] [ j + pad ] = 0 ;
	            if (arg[i].charAt(j)=='B')
	            {
	                dd[i+pad][j+pad]= 1 ;
	            	bc ++ ;
	            }
	            if (arg[i].charAt(j)=='#')
	            {
	            	dd[i+pad][j+pad]= 2 ;
	            }	      
	        }
	 
	    int x , y ;
	    int t = 0 ;
	    x = 1 ; y = 1 ;
	    int dx , dy ;
	    dx = 1 ; dy = 1 ;
	    int idle ;
	    ans = 0;
	 
	    idle = 0 ;
	    while ( bc > 0 && idle < 100000 )
	    {
	        if ( dd [ x ] [ y + dy ] == 0 )
	        	y += dy ;
	        else if ( dd [ x ] [ y + dy ] == 1 ) 
	        {
	        	bc -- ;
	        	dd [ x ] [ y + dy ] = 0 ;
	        	dy *= -1 ;
	        	idle = 0 ;
	        }
	        else if ( dd [ x ] [ y + dy ] == 2 )
	        	dy *= -1 ;
	        ans ++ ;
	   
	        if( bc == 0 ) break ;
	        if ( dd [ x +dx] [ y ] == 0 )
	        	x += dx ;
	        else if ( dd [ x +dx] [ y  ] == 1 ) 
	        {
	        	bc -- ;
	        	dd [ x +dx ] [ y ] = 0 ;
	        	dx *= -1 ;
	        	idle = 0 ;
	        }
	        else if ( dd [ x +dx] [ y ] == 2 )
	        	dx *= -1 ;
	 
	        idle ++ ;
	        ans ++ ;
	    }
	 
	    if ( bc > 0  )
	    	return -1 ;
	 
	    return ans ;
	} /***************************************************************************/
	  /*   end of class:  BrickByBrick ****************************************/
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int result = new BrickByBrick().timeToClear(new String[]{"..BB",
	    		                                                 "BB.B",
	    		                                                 "BBB."});
        System.out.println(result);
	}

}
