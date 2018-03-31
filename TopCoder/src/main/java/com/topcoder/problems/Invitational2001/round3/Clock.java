package com.topcoder.problems.Invitational2001.round3;
//http://community.topcoder.com/stat?c=problem_statement&pm=189&rd=52
public class Clock {

	public int[] getAngles(String time)
	{
		String[] strArr = time.split(":");
		int hour   = Integer.parseInt(strArr[0]);
		float min    = Float.parseFloat(strArr[1]);
		float second = Float.parseFloat(strArr[2]);
		int[] retVal = new int[3];
		int a = (360/12) * hour;
		float b = ((float)((30*min)/60));
		float c = ((float)((6*second)/60));
		retVal[0] = a+ (int)b+ (int)c;
		retVal[1] = (int)(((360 * min)/60))+(int)(((float)((30*second)/60)));
		retVal[2] = (int)(((360* second)/60) );
		return retVal;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] result = new Clock().getAngles("06:30:30");
		for(int i : result)
		{
			System.out.println(i);
		}

	}

}
