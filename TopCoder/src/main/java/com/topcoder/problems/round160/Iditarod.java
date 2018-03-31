package com.topcoder.problems.round160;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1647&rd=4605
public class Iditarod
{
	static class TimeUnit
	{
		int hour;
		String amOrPm;
		int min;
		int day;
		
		public TimeUnit(int hour,
				        String amOrPm,
				        int min,
				        int day)
		{
			this.hour = getAffectiveHour(hour, amOrPm);
			this.min = min;
			this.day = day;			
		}
		
		public int getDay()
		{
			return day;			
			
		}

		private int getAffectiveHour(int hour, String amOrPm)
		{
			if ("AM".equals(amOrPm))
			{
				return hour == 12 ? 0 : hour;
			}
			else
			{
				return (hour == 12) ? 12 : (12+hour);
			}			
		}
		
		
	}
    public int avgMinutes(String[] times)
    {
    	List<TimeUnit> list = new ArrayList<TimeUnit>();
    	for(String time : times)
    	{
    		String[] strArr = time.split("\\s+");
    		String timeStamp = strArr[0];
    		String[] hourAnsMin = timeStamp.split(":");
    		String amOrPm = strArr[1].substring(0, strArr[1].length()-1);
    		int day = Integer.parseInt(strArr[strArr.length-1]);
    		list.add(new TimeUnit(Integer.parseInt(hourAnsMin[0]),
    				              amOrPm,
    				              Integer.parseInt(hourAnsMin[1]),
    				              day));
    	}
    	
    	int hour = 8;
    	int min = 0;
    	int currentDay = 1;
    	int result = 0;
    	for(TimeUnit unit : list)
    	{
    	    if (unit.day == currentDay)
    	    {
    	        int diff = Math.abs(unit.hour-hour)*60;
    	        if (unit.hour > hour)
    	        {
    	        	diff = Math.abs(diff + Math.abs(unit.min-min));
    	        }
    	        else
    	        {
    	        	diff = Math.abs(diff - Math.abs(unit.min-min));
    	        }
    	        result += diff;
    	    }
    	    else
    	    {
    	        int diff = Math.abs(unit.day-currentDay)*24*60;
    	        int diff1 = Math.abs(unit.hour-hour)*60;
    	        if (unit.hour < hour)
    	        {
    	        	diff1 = Math.abs(diff1 - Math.abs(unit.min-min));
    	        }
    	        else
    	        {
    	        	diff1 = Math.abs(diff1 + Math.abs(unit.min-min));
    	        }
    	        
    	        if (unit.hour > hour)
    	        {
    	        	diff += diff1;    	        	
    	        }
    	        else
    	        {
    	        	diff -= diff1;
    	        }
    	        result += diff;
    	    }
    	    //currentDay = unit.day;
    	    //hour = unit.hour;
    	    //min = unit.min;
    	}
    	return (Math.round((float)result/(float)list.size()));
    }
    
    
	public static void main(String[] args)
	{
		String[] times = null;
		int result = new Iditarod().avgMinutes(times);
		System.out.println(result);

	}

}
