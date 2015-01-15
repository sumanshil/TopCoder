package com.topcoder.problems.round159;
//http://community.topcoder.com/stat?c=problem_statement&pm=1753&rd=4600
public class StreetParking
{
	public 	int freeParks(String street)
	{
		int retVal = 0;
		for(int i = 0 ; i < street.length() ; i++)
		{
			if (isNotDirectlyInfrontOfDriveway(i, street)
				&& isNotDirectlyInfrontOfBusStop(i, street)
				&& isNotFiveMetersBeforeABusStop(i, street)
				&& isNotTenMetersBeforeABusStop(i, street)
				&& isNotDirectlyInFrontOfSideStreet(i, street)
				&& isNotFiveMetersBeforeASideStreet(i, street)
				&& isNotFiveMetersAfterASideStreet(i, street))
			{
				retVal++;
			}
		}
		return retVal;
	}
	
	
	private boolean isNotFiveMetersAfterASideStreet(int i, String street)
	{		
		return street.charAt(i) == '-'
				&&((i-1)>=0 ? street.charAt(i-1) != 'S' : true);
	}
	
	
	private boolean isNotFiveMetersBeforeASideStreet(int i, String street)
	{
		return street.charAt(i) == '-'
				&& (i+1) < street.length()? street.charAt(i+1) != 'S' : true;
	}
	
	
	private boolean isNotDirectlyInFrontOfSideStreet(int i, String street)
	{
		return street.charAt(i) == '-';
	}
	private boolean isNotTenMetersBeforeABusStop(int i, String street)
	{
		return street.charAt(i) == '-'
				&& (i+2) < street.length() ? street.charAt(i+2) != 'B' : true;
	}
	private boolean isNotFiveMetersBeforeABusStop(int i, String street)
	{
		return street.charAt(i) == '-'
				&& (i+1) < street.length() ? street.charAt(i+1) != 'B' : true;
	}
	private boolean isNotDirectlyInfrontOfBusStop(int i, String street)
	{
		return street.charAt(i) == '-';
	}
	private boolean isNotDirectlyInfrontOfDriveway(int i, String street)
	{
		return street.charAt(i) == '-';
	}
	public static void main(String[] args)
	{
		int result = new StreetParking().freeParks("SSD-B---BD-DDSB-----S-S--------S-B----BSB-S--B-S-D");
		System.out.println(result);

	}

}
