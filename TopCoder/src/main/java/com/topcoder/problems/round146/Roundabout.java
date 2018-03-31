package com.topcoder.problems.round146;
//http://community.topcoder.com/stat?c=problem_statement&pm=1605&rd=4535
import java.util.LinkedList;
import java.util.List;

public class Roundabout
{
    //TODO
	// implement the queue .i.e instead taking from the queue put it in temp area
	// implement the case when all the entry points have a member
	// Replace "" and "-" with constants
	static class RoundAboutPoint
	{
		String name;
		String currentCar;
		int    timeUpdated;
		int timeCarEnteredAtRoundAbout;
		
		String carAtEntry;
		int timeCarAtEntry;
		RoundAboutPoint antiClockwise; // anti clockwise
		RoundAboutPoint clockwise; //clockwise. This will be used to check if there is 
		                           // any immediate neighbor
		List<String> waitingCars = new LinkedList<String>();
		public RoundAboutPoint(String name)
		{
			this.name = name;
			this.currentCar = "";
			this.timeUpdated = -1;
			this.carAtEntry = "";
		}
		
		public void print(int indent)
		{
			String space= "";
			for(int i = 0 ; i < indent ; i++)
			{
				space += " ";
			}
			String str   = this.toString();
			String[] arr = str.split("\\n");
			for(int i = 0 ; i < arr.length ; i++)
			{
				System.out.println(space+arr[i]);
			}
		}
		
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("Point "+this.name+"\n");
			sb.append("Current car "+this.currentCar+"\n");
			sb.append("Entry car "+this.carAtEntry+"\n");
			sb.append("Time updated "+this.timeUpdated+"\n");
			sb.append("Cars in wait list\n");
			for(int i = 0 ; i < waitingCars.size() ; i++)
			{
				sb.append(" "+waitingCars.get(i)+"\n");
			}
			return sb.toString(); 
		}
		
	}
	
	static RoundAboutPoint northPoint;
	static RoundAboutPoint eastPoint;
	static RoundAboutPoint southPoint;
	static RoundAboutPoint westPoint;
	static
	{
		northPoint = new RoundAboutPoint("N");
		eastPoint = new RoundAboutPoint("E");
		southPoint = new RoundAboutPoint("S");
		westPoint = new RoundAboutPoint("W");
		
		northPoint.clockwise = eastPoint;
		eastPoint.antiClockwise = northPoint;
		
		eastPoint.clockwise = southPoint;
		southPoint.antiClockwise  = eastPoint;

		southPoint.clockwise = westPoint;
		westPoint.antiClockwise = southPoint;
		
		westPoint.clockwise = northPoint;
		northPoint.antiClockwise = westPoint;
	}
	
	public int clearUpTime(String north, String east, String south, String west)
	{
				
		String[] arr = new String[4];
		arr[0] = north;
		arr[1] = east;
		arr[2] = south;
		arr[3] = west;
		RoundAboutPoint current = northPoint;
		for(int i = 0 ; i < arr.length ; i++)
		{
			for(int j = 0 ; j < arr[i].length() ; j++)
			{
				current.waitingCars.add(""+arr[i].charAt(j));
			}
			current = current.clockwise;
		}
		int i=0;
		for(; ; i++)
		{
			if(roundAboutNotEmpty(i, arr))
			{
				checkForExitCars(); // check for if some cars are ready to exit
				checkForMovement(i); // check if a car can move
				checkForEntryCars(i); // check for entry cars
				printCurrentState(i);
				System.out.println("=========");
			}
			else
			{
				break;
			}
		}
		return i;
	}

	
	private void printCurrentState(int time)
	{
		RoundAboutPoint current = northPoint;
		System.out.println("Time "+time);
//		for(int i = 0 ; i < 4 ; i++)
//		{
//			System.out.println(current);
//			current = current.clockwise;
//		}
		northPoint.print(15);
		northPoint.antiClockwise.print(0);
		northPoint.clockwise.print(30);
		northPoint.clockwise.clockwise.print(15);
	}
	
	
	private void checkForEntryCars(int currentTime)
	{
	    RoundAboutPoint current = northPoint;
	    for(int i = 0 ; i < 4 ; i++)
	    {
//	    	if (canCarEnter(current))
//	    	{
//	    		if ( current.waitingCars.size() > 0)
//	    		{
//	    		    String car = current.waitingCars.get(0);
//	    		    if (!"-".equals(car))
//	    		    {
//	    		    	current.currentCar = car;
//	    		    	current.waitingCars.remove(0);
//	    		    }
//	    		    else
//	    		    {
//	    		    	current.waitingCars.remove(0);
//	    		    }
//	    		    	    
//	    		}
//	    	} 
//	    	else
//	    	{
//	    	   if (current.waitingCars.size() > 0 && "-".equals(current.waitingCars.get(0)))
//	    	   {
//	    		   current.waitingCars.remove(0);
//	    	   }
//	    	}
	    	// working code above
	    	
	    	if ("".equals(current.carAtEntry) || 
	    		"-".equals(current.carAtEntry)	)
	    	{
	    		// car should enter now
	    		if (current.waitingCars.size() > 0)
	    		{
	    			current.carAtEntry = current.waitingCars.get(0);
	    			current.waitingCars.remove(0);
	    			current.timeUpdated = currentTime;
	    		}
	    		else
	    		{
	    			current.carAtEntry ="";
	    		}
	    	}
	    	else
	    	{
	    		// car is already there at entry.
	    		// check if it can enter round about
	    		if (canCarEnter(current, currentTime))
	    		{
		    		if ( !"".equals(current.carAtEntry) || 
  	    	    		 !"-".equals(current.carAtEntry)	)
		    		{
		    		    String car = current.carAtEntry;
	    		    	current.currentCar = car;
	    		    	current.timeUpdated = currentTime;
	    		    	if (current.waitingCars.size() > 0)
	    		    	{
	    		    		current.carAtEntry = current.waitingCars.get(0);
	    		    		current.waitingCars.remove(0);
	    		    	}
	    		    	else
	    		    	{
	    		    		current.carAtEntry = "";
	    		    	}
		    		}	    			
	    		}
	    	}
	    	current = current.antiClockwise;
		}		    	
	}
	
	
	private boolean canCarEnter(RoundAboutPoint current, int currentTime)
	{
		if (!"".equals(current.currentCar))
		{
			return false;
		}
		else if ("".equals(current.clockwise.currentCar) && current.clockwise.timeUpdated <= currentTime)
		{
			if (("".equals(current.clockwise.carAtEntry)
				||"-".equals(current.clockwise.carAtEntry))
				&& (current.clockwise.timeUpdated <= currentTime))
			{
				return true;
			}
			else if ( !("".equals(current.clockwise.carAtEntry) ||"-".equals(current.clockwise.carAtEntry))
					  && current.clockwise.timeUpdated == currentTime)
			{
				return true;
			}
		}
		// this else if have added recently
		else if (!("".equals(current.clockwise.currentCar)
				  ||"-".equals(current.clockwise.currentCar))
				&& (current.clockwise.timeUpdated == currentTime))
		{
			return true;
		}		
		else if (!("".equals(current.clockwise.carAtEntry)
				  ||"-".equals(current.clockwise.carAtEntry))
				&& (current.clockwise.timeUpdated == currentTime))
		{
			return true;
		}
		else if (current.clockwise.currentCar.equals(current.clockwise.name))
		{
			return true;
		}	
		return false;
	}
	
	
	private void checkForMovement(int time)
	{
	    RoundAboutPoint current = getAnEmptyExitPoint(time);
	    if (current == null)
	    {
	    	return;
	    }
	    for(int i = 0 ; i < 4 ; i++)
	    {
		    if ("".equals(current.currentCar))
            {
		    	if ( !"".equals(current.clockwise.currentCar) && current.clockwise.timeUpdated != time)
		    	{
		    		current.currentCar  = current.clockwise.currentCar;
		    		current.clockwise.currentCar="";
		    		current.timeUpdated = time;
		    		current.clockwise.currentCar = "";
		    		if (current.name.equals(current.currentCar))
		    		{
		    			current.currentCar = "";
		    		}
		    	}
			}
			current = current.clockwise;
		}			
	}

	
	private RoundAboutPoint getAnEmptyExitPoint(int currentTime)
	{
	    RoundAboutPoint current = northPoint;	    
	    for(int i = 0 ; i < 4 ; i++)
	    {
		    if ("".equals(current.currentCar))
            {
				return current;
			}
			current = current.clockwise;
		}			
	    	// special case when all the round about has cars.
	    	//TODO so move them by one point anti clockwise
	    moveCarsByAntiClockwise(currentTime);    	
	    return null;
	}
	
	
	private void moveCarsByAntiClockwise(int currentTime)
	{
		String car = northPoint.currentCar;
		RoundAboutPoint current = westPoint;
		for(int i = 0 ; i < 3 ; i++)
		{
			String temp = current.currentCar;
			current.currentCar = car;
			if (current.name.equals(current.currentCar))
			{
				current.currentCar = "";	
				populateCurrentCar(current, currentTime);
			}
			car = temp;
			current = current.antiClockwise;
		}
		
		northPoint.currentCar = car;
		if (northPoint.name.equals(northPoint.currentCar))
		{
			northPoint.currentCar = "";
			populateCurrentCar(northPoint, currentTime);
		}
	}
	
	private boolean hasValidCarAtEntry(RoundAboutPoint currentPoint)
	{
		return !"".equals(currentPoint.carAtEntry) && !"-".equals(currentPoint.carAtEntry); 
	}
	
	private void populateCurrentCar(RoundAboutPoint currentPoint, int currentTime)
	{
		if ("".equals(currentPoint.currentCar))
		{
			if (hasValidCarAtEntry(currentPoint))
			{
				currentPoint.currentCar = currentPoint.carAtEntry;
				currentPoint.timeUpdated = currentTime;
				populateEntryCar(currentPoint, currentTime);
			}
		}
	}

	private void populateEntryCar(RoundAboutPoint currentPoint, int currentTime)
	{
		if (currentPoint.waitingCars.size() > 0)
		{
			currentPoint.carAtEntry = currentPoint.waitingCars.get(0);
			if (currentPoint.timeUpdated < currentTime)
			{
				currentPoint.timeUpdated = currentTime;	
			}			
			currentPoint.waitingCars.remove(0);
		}
	}

	private void checkForExitCars()
	{
	    RoundAboutPoint current = northPoint;
	    for(int i = 0 ; i < 4 ; i++)
	    {
		    if (current.currentCar.equals(current.name))
            {
				current.currentCar = "";
			}
			current = current.clockwise;
		}
	}
	
	
	private boolean roundAboutNotEmpty(int time, String[] arr)
	{
		RoundAboutPoint current = northPoint;
		for(int i = 0 ; i < 4 ; i++)
		{
			if (current.currentCar != "" || current.carAtEntry != "" )
			{
				return true;
			}
			else
			{
				if (getWaitingCarNos(current) > 0)
				{
					return true;
				}
			}
			current = current.clockwise;
		}
		return false;
	}
	
	
	private int getWaitingCarNos(RoundAboutPoint current)
	{
		int retVal = 0;
		
		List<String> list = current.waitingCars;
		for (String string : list)
		{
			if (!"-".equals(string))
			{
				retVal++;
			}
		}
		return retVal;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//int result = new Roundabout().clearUpTime("--", "--", "WE", "-S");
		//int result = new Roundabout().clearUpTime("--S", "---", "---", "---");
		//int result = new Roundabout().clearUpTime("WWW","NNN","---","---");
		//int result = new Roundabout().clearUpTime("E------", "-------", "-------", "-------");
		//int result = new Roundabout().clearUpTime("ES","N","E","");//NW
		//int result = new Roundabout().clearUpTime("E","-N","W","-S");
		//int result = new Roundabout().clearUpTime("S", "---", "N", "---");
		int result = new Roundabout().clearUpTime("-----------E", "-----------W", "-----------N", "-----------S");


		System.out.println(result);

	}

}
