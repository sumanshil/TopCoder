package com.topcoder.problems.round158;
//http://community.topcoder.com/stat?c=problem_statement&pm=1676&rd=4598
public class StampPads
{
    private int min = Integer.MAX_VALUE;
	public int bestCombo(String[] pads, String[] wishlist)
	{
		recurse(0, // start index
				0, // colors found so far
				0, // current count
				wishlist,
				pads);
		return min == Integer.MAX_VALUE ? -1 : min;
	}
    
    
    
	private void recurse(int currentIndex,
			             int currentSelection,
			             int currentCount,
			             String[] wishlist,
			             String[] pads)
	{
		if (currentIndex == pads.length)
		{
			checkForColors(currentSelection,
					       wishlist.length,
					       currentCount);
			return;
		}
		else if (allColorsPresent(currentSelection,
                   	              wishlist.length))
		{
			if (currentCount < min)
			{
				min = currentCount;
			}
			return;
		}

		// consider this pad
		int currentSelection1 = setCurrentSelection(pads[currentIndex],
				                               wishlist,
				                               currentSelection);
		recurse(currentIndex+1,
				currentSelection1,
				currentCount+1,
				wishlist,
				pads);
		
		
		// don't consider this pad
		recurse(currentIndex+1,
				currentSelection,
				currentCount,
				wishlist,
				pads);
		
	}


	private void checkForColors(int currentSelection,
			                    int wishlistLength,
			                    int currentCount)
	{
		if (allColorsPresent(currentSelection,
	                         wishlistLength))
		{
			if (currentCount < min)
			{
				min = currentCount;
			}
		}			
	}



	private boolean allColorsPresent(int currentSelection,
			                         int wishListLength)
	{
		boolean retVal = true;
		for(int i = 0; i < wishListLength ; i++)
		{
			if (!isBitSet(currentSelection, i))
			{
				retVal = false;
				break;
			}
		}
		return retVal;
	}



	private int setCurrentSelection(String currentPad,
			                        String[] wishlist,
			                        int currentSelection)
	{
		String[] padColors = currentPad.split("\\s+");
		for(int i = 0 ; i < wishlist.length; i++)
		{
			for(String padColor : padColors)
			{
				if (wishlist[i].equals(padColor))
				{
					if (!isBitSet(currentSelection, i))
					{
						currentSelection = setBit(currentSelection, i);
					}
				}
			}
		}
		return currentSelection;
	}



	private int setBit(int currentSelection,
			           int index)
	{
		int mask = 1 << index;
		currentSelection = currentSelection | mask;
		return currentSelection;
	}



	private boolean isBitSet(int currentSelection,
			                 int index)
	{
        int mask = 1 << index;
        boolean retVal = false;
        int maskedResult = currentSelection & mask;
        if (maskedResult > 0)
		    retVal = true;
        return retVal;
	}



	public static void main(String[] args)
	{
//		String[] pads= {"yellow red purple blue cyan",
//		        		"red green orange magenta yellow",
//		                "brown black orange yellow tan"};
//		String[] wishList = {"orange", "yellow", "red", "blue", "magenta", "tan"};
//		String[] pads = {"yellow red purple blue cyan",
//				         "red green orange magenta yellow",
//		                 "brown black orange yellow tan"};
//		String[] wishList = {"orange", "yellow", "red", "blue", "tan"};
//		String[] pads = {"yellow black blue green red",
//				 "yellow brown cyan magenta tan",
//				 "black grey fire maroon silver",
//				 "blue white neon tangerine rust",
//				 "green orange soot turquoise mint",
//				 "red cream opal chrome sky"};
//		String[] wishList = {"yellow", "black", "blue", "green", "red",
//				 "brown", "grey", "white", "orange", "cream"};
//		String[] pads = {"red green orange magenta yellow"};
//		String[] wishList = {"silver"};
		String[] pads = {"a i y d o", "t s k g e", "j u w i k", "u k l s j", "q s a c y",
				 "q m d x a", "m s l h r", "s x q l n", "u r j s k", "e w v d p",
				 "o l a b q", "f z g a m", "o g k b a", "c h g k t", "z v s n x",
				 "z n b w c", "h p o u k", "t z o x m", "a w i v z", "u t v m y"};
		String[] wishList = {"x", "b", "u", "c", "h", "j", "t", "v", "d", "g",
				 "k", "w", "y", "z", "a", "i", "m", "l", "n", "e"};
		int result = new StampPads().bestCombo(pads, wishList);
		System.out.println(result);
	}

}
