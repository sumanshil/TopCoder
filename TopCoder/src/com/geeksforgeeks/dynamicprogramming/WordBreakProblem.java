package com.geeksforgeeks.dynamicprogramming;

public class WordBreakProblem {
    private static String[] dictionary = {"mobile","samsung","sam","sung","man","mango",
        "icecream","and","go","i","like","ice","cream"};
    
    private static boolean isPresentInDictionary(String word){
        for(int i = 0 ; i < dictionary.length ; i++){
        	if (word.equals(dictionary[i])){
        		return true;
        	}
        }
        return false;
    }
    
    public static boolean isInDictionary(String word){
    	if (word.length() == 0)
    		return true;
    	for(int i = 1; i < word.length();i++){
    		String prefix = word.substring(0,i);
    		String suffix = word.substring(i, word.length());
    		if (isPresentInDictionary(prefix) && isPresentInDictionary(suffix)){
    			return true;
    		} else if (isPresentInDictionary(prefix) && isInDictionary(suffix))
    			return true;
    	}
    	return false;
    }

    public static boolean wordBreakDP(String word){
    	boolean[] wb = new boolean[word.length()];
    	
    	for(int i = 1; i < word.length() ; i++){
    		String str1 = word.substring(0, i);
    		if (wb[i-1] == false && isPresentInDictionary(str1)){
    			wb[i-1] = true;
    		}
    		
    		if (wb[i-1] == true){
    			for(int j = i+1; j <= word.length(); j++){
    				String suffix = word.substring(i, j);
    				if (wb[j-1] == false && isPresentInDictionary(suffix)){
    					wb[j-1] = true;
    				}
    				if (wb[j-1] == true && (j-1) == (word.length()-1)){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		//String input = "ilikesamsung";
		//String input = "iiiiiiii";
		//String input = "ilikelikeimangoiii";
		//String input = "samsungandmango";
		//String input = "samsungandmangok";
		//System.out.println(wordBreakDP(input));

	}

}
