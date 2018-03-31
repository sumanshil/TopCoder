package com.topcoder.problems.round154;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1779&rd=4575
public class CheatCode 
{
	public int[] matches(String keyPresses, String[] codes)
	{
		int codeIndex = 0;
		List<Integer> retList = new ArrayList<Integer>();
		for(String code : codes)
		{
			char c = code.charAt(0);
			int[] indexes = getIndexes(keyPresses, c);
			for(int j = 0 ; j < indexes.length ; j++)
			{
				int count = 1;
				int keyPressCount = 0;
				int keyPressIndex = indexes[j];
				int currentIndex  = 0;
				c = code.charAt(0);
				boolean isMatch = true;
				for(int i = 1 ;  ; )
				{				
					if (i == code.length()+1)
					{
						break;
					}
					if (i < code.length() 
						&& c != code.charAt(i))
					{
						keyPressCount = getKeyPressMatch(keyPresses,
								                         keyPressIndex,
								                         c);
						
						if (keyPressCount < count
							|| keyPressCount == 0	)
						{
							isMatch = false;
							break;
						}
						else
						{
							keyPressIndex += keyPressCount;
							currentIndex = currentIndex+count;
							count = 1;
							i = currentIndex;
							c = code.charAt(i);
							i++;
						}
					}
					else if ( i == code.length())
					{
						keyPressCount = getKeyPressMatch(keyPresses, keyPressIndex, c);					
						if (keyPressCount < count
							|| keyPressCount == 0	)
						{
							isMatch = false;
							break;
						}
						i++;
					}
					else
					{
						i++;
						count++;
					}
				}
				
				if (isMatch)
				{
					retList.add(codeIndex);
					break;
				}				
			}
			codeIndex++;
		}
		int[] result = new int[retList.size()];
		for(int j = 0 ; j < retList.size() ; j++)
		{
			result[j] = retList.get(j);
		}
		return result;
	}

	
	private int[] getIndexes(String keyPresses,
			                 char c)
	{
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < keyPresses.length() ; i++)
		{
			if (keyPresses.charAt(i) == c)
			{
				list.add(i);
			}
		}
		int[] result = new int[list.size()];
		for(int i = 0 ; i < list.size() ; i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}
	
	
	private int getKeyPressMatch(String keypress, 
			                     int keyPressIndex,
			                     char c)
	{
		int retVal = 0;
	    for(int i = keyPressIndex ; i < keypress.length() ; i++)
	    {
	    	if (keypress.charAt(i) == c)
	    	{
	    		retVal ++;
	    	}
	    	else
	    	{
	    		break;
	    	}
	    }
		return retVal;
	}
	
	public int[] matches1(String keys, 
			              String[] codes)
	{
		List<Integer> list = new ArrayList<Integer>();
		int codeIndex = 0;
		for(String code : codes)
		{
			for(int i = 0 ; i < code.length() ; i++)
			{
				if (matches(keys,
						    code.substring(i),
						    '*'))
				{
					list.add(codeIndex);
					break;
				}
			}
			codeIndex++;
		}
		int[] result = new int[list.size()];
		for(int i = 0 ; i < list.size() ; i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}
	
	
	private boolean matches(String keys,
			                String code,
			                char lastChar)
	{
		if (code.length() == 0)
			return true;
		if (keys.length() == 0)
			return false;
		
		if (keys.charAt(0) == code.charAt(0))
		{
			return matches(keys.substring(1),
					       code.substring(1),
					       code.charAt(0));
			
		}
		else if (keys.charAt(0) == lastChar)
		{
			return matches(keys.substring(1),
				           code,
				           lastChar);			
		}
		return false;
	}


	public static void main(String[] args) 
	{
//	    String keyPresses = "UUDDLRRLLRBASS";
//	    String[] codes = {"UUDDLRLRBA","UUDUDLRLRABABSS","DDUURLRLAB","UUDDLRLRBASS","UDLRRLLRBASS"};
//		String keyPresses = "IDDQDDTSFHHALL";
//		String[] codes = 
//		{"FHHALL", "FHSHH", "IDBEHOLDA", "IDBEHOLDI", "IDBEHOLDL",
//				 "IDBEHOLDR", "IDBEHOLDS", "IDBEHOLDV", "IDCHOPPERS", "IDCLEV",
//				 "IDCLIP", "IDDQD", "IDDT", "IDFA", "IDKFA", "IDMYPOS", "IDMUS"};
//		String keyPresses = "AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYY";
//		String[] codes = {"ABCDE", "BCDEF", "CDEFG", "DEFGH", "EFGHI",
//				 "FGHIJ", "GHIJK", "HIJKL", "IJKLM", "JKLMN",
//				 "KLMNO", "LMNOP", "MNOPQ", "NOPQR", "OPQRS",
//				 "PQRST", "QRSTU", "RSTUV", "STUVW", "TUVWX",
//				 "UVWXY", "VWXYZ", "WXYZA", "XYZAB", "YZABC",
//				 "ZABCD"};
//		String keyPresses = "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ";
////		String[] codes = {"KGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSL"};
//		String[] codes = {"LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSS", "LAKJDGSJKGLSDKHFKDFHDGHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDHHSDKKSJDHFHJGKDKLSLSLJKASSJ",  "AKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKHHSJ",  "LAKDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDFHJGKDKLSLSLJKAHS",   "KJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDHHSDKKSJDHFHJGKDKLLSLJKAHS",    "LAKGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGJKGLSDKHFKDFHDGHHDKKSJDHFHJGKDKLSLSLJKAHS",    "LAKJDGJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHGHHSDKKSJDHFHJGKDKLSLSLJKAHS",   "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSL",
//				 "LAKJDGSJKGLSDKHFDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHS",   "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLJKAHSJ",
//				 "LAKJDGSJKGLSDHFKDFHDGHHSDKKSJDHFHJGKDKLSLSJKAHS",    "KGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSL",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJHFHJGKDKLSLSLJKAHS",   "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKSJHFHJGKDKLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHS",   "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKSJHFHJGKDKLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJKDKLSLSLJKAHSJ",
//				 "LKJDGSJKGLSDKHFKDFHDGHHSDKKJDHFHJGKDKLSLSLJKAHS",    "AKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDKLSLSLJKAHS",
//				 "LAJDGSJKGLSDKHFKDFHDGHHSDKKSJDFHJGKDKLSLSLKAHS",     "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFGKDKLSLSLJKAHSJ",
//				 "LKJDGSJKLSDKHFKDFHDGHHSDKSJDHFHJGKDKLSLSLJKAHS",     "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHHJGKDKLSLSLJKAHSJ",
//				 "AKJDGSJKGLSDKFKDFHDGHHSSJDHFJGKDKLSLSLJKAHS",        "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJDHFJGKDKLSLSLJKAHS",   "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSJHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHSDKKSDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDSJKGLSDKHFKDFHDGHHSDKKSJDHFHJGKDLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHSDKKJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDHHSDKKSJDHFHJGKDKLSLSLJKAHS",   "LAKDGSJKGLSDKHFKDFHDGHHSDKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHSDKSJDHFHJGKDKLSLSLJKAS",    "KJDGSJKGLSDKHFKDFHDGHSDKKSJDHFHJGKDKLSLSLJKAH",
//				 "LAKJDGSJKGLSDKHFKDFHDGHHDKKSJDHFHJGKDKLSLSLJKAHS",   "LAKDGSJKGLSDKFHDGHHSDKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJKGLSKHFKDFHDHHSDKKSJDHFHJGKDKLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHSKKSJDHFHJGKDKLSLSLJKAHSJ",
//				 "LAKJDGSJGLSDKHFKDFHDGHHSDKKSJDFHJGKDKLSLSLJKAHS",    "LAKJDGSJKGLSDKHFKDFHDGHHDKKSJDHFHJGKDKLSLSLJKAHSJ"};
		String keyPresses = "UUDDLRRLLRBASS";
		String[] codes = {"UUDDLRLRBA", "UUDUDLRLRABABSS", "DDUURLRLAB", "UUDDLRLRBASS", "UDLRRLLRBASS"};
	    int[] result = new CheatCode().matches1(keyPresses,
	    		                               codes);
	    for(int i : result)
	    {
	    	System.out.println(i);
	    }
	}
}
