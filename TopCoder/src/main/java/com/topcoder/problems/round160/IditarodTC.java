package com.topcoder.problems.round160;

import java.util.StringTokenizer;

public class IditarodTC {
	  public int avgMinutes(String[] times) {
	    int starttime=60*8+1*60*24;
	    int timetot=0;
	    for(int i=0; i<times.length; i++){
	      StringTokenizer st = new StringTokenizer(times[i],": M,");
	      String str1 = st.nextToken();
	      int time=60*(Integer.parseInt(str1)%12);
	      str1 = st.nextToken();
	      time+=Integer.parseInt(str1);
	      if(st.nextToken().equals("P")){
	        time+=60*12;
	      }
	      str1 = st.nextToken();
	      str1 = st.nextToken();
	      time+=Integer.parseInt(str1)*24*60;
	      time-=starttime;
	      timetot+=time;
	    }
	    return (int)(((timetot+0.0)/times.length)+.5);
	  }
	  
	  public static void main(String[] args)
	{
		  String[] times = {"02:00 PM, DAY 19", "02:00 PM, DAY 20", "01:58 PM, DAY 20"};
		  int result = new IditarodTC().avgMinutes(times);
		  System.out.println(result);
	}
	}
