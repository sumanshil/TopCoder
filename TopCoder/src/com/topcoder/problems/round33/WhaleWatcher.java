package com.topcoder.problems.round33;


//http://community.topcoder.com/stat?c=problem_statement&pm=175&rd=4003
public class WhaleWatcher 
{

	int[][] matrix = new int[10][10];
	boolean[][] visibilityMatrix = new boolean[10][10];
	
	private final int factor = 9;
	private int[] oneX = {-1, 0, 1, -1, -1, 0, 1, 1, 0};
	private int[] oneY = { 1, 1, 1,  0, -1, 1, 1, 0, 0};

	private int[] twoX = {-2, 0, 2, -2, -2, 0, 2, 2, 0};
	private int[] twoY = { 2, 2, 2,  0, -2, 2, 2, 0, 0};

	private int[] threeX = {-3, 0, 3, -3, -3, 0, 3, 3, 0};
	private int[] threeY = { 3, 3, 3,  0, -3, 3, 3, 0, 0};

	/**
	 * @param args
	 */
	static class CoOrdinate
	{
		public CoOrdinate(int x2, int y2) 
		{
			this.x = x2;
			this.y = y2;
		}
		int x;
		int y;
	}
	public int getWhaleSightings(String[] WhalePositions, int Passengers, int
			StartPosition, int TripDistance, String Weather)
	{
		for( String position : WhalePositions )
		{
			CoOrdinate co = getPosition(position);
			int x = 9-co.x;
			int y = co.y;
			matrix[x][y] = 1;
		}
		
		int x = 9;
		int result = 0;
		for( int i = 0 ; i <= TripDistance ; i++ )
		{
			//int currentPosition = matrix[StartPosition][x--];
			int currentY = StartPosition;
			int currentX = x--;
			int visibility = getVisibilityDistance(Weather);
			for( int j = 1 ; j <= visibility ; j++ )
			{				
		    	int[] xArr = getXArr(j);
		    	int[] yArr = getYArr(j);
		    	for(int k = 0 ; k < xArr.length ; k++)
		    	{
		    		int newX = currentX + xArr[k];
		    		int newY = currentY + yArr[k];
		    		if (isValid(newX, newY) && !sighted(newX, newY) && matrix[newX][newY] == 1)
		    		{
		    	       result++; 		
		    	       visibilityMatrix[newX][newY] = true;
		    		}
		    	}
			}
		}
		return result*Passengers;
	}
	
	private int[] getXArr(int j)
	{	
	    switch(j)
	    {
	        case 1 :
	        	return oneX;	        	
	        case 2 :
	        	return twoX;
	        case 3 :
	        	return threeX;
	    }
	    return null;
	}

	private int[] getYArr(int j)
	{
	    switch(j)
	    {
	        case 1 :
	        	return oneY;	        	
	        case 2 :
	        	return twoY;
	        case 3 :
	        	return threeY;
	    }
	    return null;
	}

	private boolean sighted(int newX, int newY)
	{
		return visibilityMatrix[newX][newY] == true;
	}

	private boolean isValid(int newX, int newY)
	{
	    if (newX >= 0 && newX <= 9 && newY >=0 && newY <=9)
	    	return true;
	    return false;
	}

	private int getVisibilityDistance(String weather)
	{
       if ("Foggy".equals(weather))
       {
    	   return 1;
       }
       else if ("Cloudy".equals(weather))
       {
    	   return 2;
       }
       else
       {
    	   return 3;
       }
	}
	private CoOrdinate getPosition(String position)
	{
		String[] str = position.split(",");
		int y = Integer.parseInt(str[0]);
		int x = Integer.parseInt(str[1]);
		return new CoOrdinate(x, y);
	}
	public static void main(String[] args)
	{
		String[] WhalePositions =  {"0,0", "0,9"};
		int Passengers = 1;
		int StartPosition = 1;
		int TripDistance = 9;
		String Weather =  "Foggy";
        int result = new WhaleWatcher().getWhaleSightings(WhalePositions, Passengers, StartPosition, TripDistance, Weather);
        System.out.println(result);
	}

}
