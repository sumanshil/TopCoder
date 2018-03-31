package com.topcoder.problems.round28;
//http://community.topcoder.com/stat?c=problem_statement&pm=156&rd=3027
public class Numbers {

	public String biggestPower(int n, int p) {
		int result = factorial(n);
		
		int index = 0;
		while(true){
			double r = Math.pow(p, index);
			if (result % r != 0){
				return ""+(index-1);
			}
			index++;
		}
	}
	
	private int factorial(int n){
		if ( n == 0 )
			return 1;
		
		return n * factorial(n-1);
			
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int n = 9;
        int p = 3;
        String result = new Numbers().biggestPower(n, p);
        System.out.println(result);

	}

}
