package com.topcoder.problems.round151;

public class BirthDay 
{
	public String getNext(String date, String[] birthdays)
	{
		String[] arr = date.split("/");
		int currentMonth = Integer.parseInt(arr[0]);
		int currentDay   = Integer.parseInt(arr[1]);
		int min = Integer.MAX_VALUE;
		String result = null;
		for(String birthday : birthdays)
		{
			String[] arr1 = birthday.split("\\s+");
			String[] arr2 = arr1[0].split("/");
			int     month = Integer.parseInt(arr2[0]);
			int     day   = Integer.parseInt(arr2[1]);
			int countDays = 0;
			if (month >= currentMonth)
			{
				countDays += (month-currentMonth)*30;
			}
			else
			{
				countDays += ((12-currentMonth)+(month))*30;
			}
			if (countDays == 0)
			{
				// same month 
				if (day >= currentDay)
				{
					countDays += (day - currentDay);
				}
				else
				{
					countDays += 365+(day - currentDay);
				}
			}
			else
			{
				if (day > currentDay)
				{
					countDays += (day-currentDay);
				}
				else
				{
					countDays -= (currentDay-day);
				}
			}
			if (countDays < min)
			{
				min = countDays;
				result = arr1[0];
			}
		}
		return result;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String result = new BirthDay().getNext("09/13", new String[]{"09/14 Loraine", "09/13 Gunther", "09/12 Gunar"});
		System.out.println(result);

	}

}
