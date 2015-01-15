package com.topcoder.problems.round24;
//http://community.topcoder.com/stat?c=problem_statement&pm=144&rd=3023
public class FriendlyIntParser {
    public int parseInt(String n){
    	int l = 0;
    	for(int i = 0 ;i < n.length() ; i++){
    		int oldValue = l;
    		if (n.charAt(i)>='0' && n.charAt(i)<='9'){
    			l = (l*10)+(n.charAt(i)-48);
    		} else if (n.charAt(i) == 'l'){
    			l = (l*10)+1;
    		} else if (n.charAt(i) == 'o' || n.charAt(i) =='O' ){
    			l = l*10;
    		}
    		if (oldValue > 0 && l <0){
    			return -1 ;
    		}
    	}
    	return l;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(Integer.MAX_VALUE);
        //System.out.println(Long.MAX_VALUE);
        //System.out.println((int)'0');
        //System.out.println((int)'9');
		String str = "2200000000";
		System.out.println(new FriendlyIntParser().parseInt(str));
	}

}
