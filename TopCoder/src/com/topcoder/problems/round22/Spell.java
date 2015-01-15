package com.topcoder.problems.round22;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=139&rd=3021
public class Spell {
	public String[] getAlternatives(String[] dict, String word, int min){
		List<String> list = new ArrayList<String>();
	    for(String dictWord : dict){
	    	if (isWithinDistance(dictWord, word, min)){
	    		list.add(dictWord);
	    		System.out.println();
	    	}
	    }
	    return (String[])list.toArray(new String[0]);
	}
	
	public boolean isWithinDistance(String target, String word, int distance){
		int[][] matrix = new int[word.length()+1][target.length()+1];
		matrix[0][0] = 0;
		for(int i = 1 ; i <= word.length() ; i++){
			matrix[i][0] = i;
		}
		
		for(int j = 1 ; j <= target.length() ; j++){
			matrix[0][j] = j;
		}
		
		for(int i = 1 ; i <= word.length() ; i++){
			for(int j = 1 ; j <= target.length(); j++ ){
				int x = matrix[i-1][j]+1;
				int y = matrix[i][j-1]+1;
				int z = matrix[i-1][j-1]+(word.charAt(i-1) == target.charAt(j-1)? 0 : 1);
				matrix[i][j] = Math.min(Math.min(x, y), z);								
			}
		}
		
		return (matrix[word.length()][target.length()] <= distance )? true : false;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String[] dict = {"dispel", "impel", "mill", "misdeal", "misdeed", "misspell", "sell", "spell", "topcoder"};
        String word = "mispell";
        int distance = 2;
        String[] result = new Spell().getAlternatives(dict, word, distance);
        for(String str : result){
        	System.out.println(str);
        }

	}

}
