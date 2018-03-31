package com.geeksforgeeks.dynamicprogramming;

public class StringDiffUtil extends LongestCommonSubSequence {

	public static void main(String[] args){
        //String original = "ABCDGH";
        //String changed = "AEDFHR";
		String original = "AGGTAB";
		String changed = "GXTXAYB";
        //String changed = "ABCEGH";
        new StringDiffUtil().findDiff(original, changed);
	}
	public void findDiff(String original, String changed){
		super.lcsDynamic(original, changed);

		int currentCount = 0;
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb = new StringBuffer();
        int iIndex = 1;
        int jIndex = 1;
        
        while(iIndex < original.length() && jIndex < changed.length()){
        	int countx = 0;
        	int county = 0;
        	int i = iIndex;
        	int j = jIndex;
        	for( ; i <= original.length() ; i++){
        		if(lcs[i][jIndex] > currentCount){
        			break;
        		} else {
        			countx++;
        		}
        	}
        	
        	for( ; j <= changed.length() ; j++){
        		if(lcs[iIndex][j] > currentCount){
        			break;
        		} else {
        			county++;
        		}
        	}
        	boolean flag = false;
        	if (countx >= original.length()){
        		flag = true;
        		printOriginalCharacter(original.charAt(iIndex-1));
        	}
        	
        	if (county >= changed.length()){
        		flag = true;
        		printChangedCharacter(changed.charAt(jIndex-1));
        	} 
        	if (!flag){
	        	if (countx < county){
                     int k = 0;
                     while(k < countx){
                    	 printOriginalCharacter(original.charAt((iIndex+k)-1));
                    	 k++;
                     }
	        		
	        	} else if (county < countx){
                    int k = 0;
                    while(k < county){
                    	printOriginalCharacter(changed.charAt((jIndex+k)-1));
                   		k++;
                    }	        		
	        	}
        	}
        }
		
		
		
		if (iIndex <= original.length()){
			for(int i1 = iIndex ; i1 < original.length() ; i1++){
				printOriginalCharacter(original.charAt(i1-1));
			}
		}
		
		if(jIndex <= changed.length()){
			for(int j1 = jIndex ; j1<= changed.length() ; j1++){
				printChangedCharacter(changed.charAt(j1-1));
			}
		}
		
	}
	
	private void printOriginalCharacter(char c){
		System.out.println(c);
	}
	
	private void printOriginalCharacter(StringBuffer sb){
		for(int i = 0 ; i < sb.length() ; i++){
			printOriginalCharacter(sb.charAt(i));
		}
	}

	private void printChangedCharacter(StringBuffer sb){
		for(int i = 0 ; i < sb.length() ; i++){
			printChangedCharacter(sb.charAt(i));
		}
	}
	
	private void printChangedCharacter(char c){
		System.out.println("  "+c);
	}
	
	private void printBothCharacter(char originalChar, char changedChar){
		System.out.println(originalChar+" "+changedChar);
	}
}
