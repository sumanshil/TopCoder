package com.topcoder.problems.round146;
//http://community.topcoder.com/stat?c=problem_statement&pm=1589&rd=4535
public class RectangularGrid 
{
    public long countRectangles(int width, int height)
    {
    	long result = 0;        
        for(int i = 1; i <= width ; i++)
        {
            long c1 = (width-i)+1;
            long c2 = 0;
        	for(int j = 1 ; j <= height ; j++)
        	{
        		if ( i != j)
        		{
        		   	c2+= (height-j)+1;
        		}
        	}
            result += (c1*c2);	
        }
        
        return result;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        int width  = 3;
        int height = 3;
        long result = new RectangularGrid().countRectangles(592, 964);
        System.out.println(result);
	}

}
