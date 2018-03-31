package com.topcoder.problems.round26;

import java.util.HashSet;
import java.util.Set;

//http://community.topcoder.com/stat?c=problem_solution&cr=114853&rd=3025&pm=151
public class CalcBusinessDay {
	static final int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
	public int calcBusinessDays(String startDate, String endDate, String[] holidays){
		Set<Integer>  holidaysSet = new HashSet<Integer>();
		for(String holiday : holidays){
			holidaysSet.add(getDay(holiday));
		}
		
		int start = getDay(startDate);
		int end = getDay(endDate);
		int result = 0 ;
		for(int i = start + 1 ; i <= end ; i++){
			if (i % 7 != 0 && i%7 != 6 && !holidaysSet.contains(i) ){
				result ++;
			}
		}
		return result;
	}
	
	private int getDay(String date){
		//"mm/dd/yyyy"
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6));
		int fd = 0;
		for(int y = 1990 ; y < year ; y++){
			if (y%4 == 0){
				fd += 366;
			} else {
				fd += 365;
			}
		}
		
		for(int m = 1 ; m < month ; m++){
			if (m == 2 && year %4 == 0)
				fd += 29;
			else
				fd += mdays[m-1];			
		}
		fd += day;
		return fd;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = new CalcBusinessDay().calcBusinessDays("01/01/1990", "01/01/2001", new String[]{});
		System.out.println(result);

	}

}
