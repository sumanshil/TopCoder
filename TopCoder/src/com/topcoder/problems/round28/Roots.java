package com.topcoder.problems.round28;

import java.util.HashSet;
import java.util.Set;

//http://community.topcoder.com/stat?c=problem_statement&pm=157&rd=3027
public class Roots {

	public int isPrimitiveRoot(int X, int N){
		int phi= phi(N);
		Set<Double> set = new HashSet<Double>();
		for(int i = 1 ; i <= phi ; i++){
			double r = Math.pow(X, i) % N;
			if ( set.contains(r) ){
				return 0;
			} else {
				set.add(r);
			}
		}
		return 1;
	}
	
	private int phi(int n){
		int result = 0;
		for(int i = 1; i <=n ; i++ ){
			if (gcd(i, n) == 1){
				result++;
			}
		}
		return result;
	}
	
	public int gcd(int a, int b){
		if (b == 0)
			return a;
		return gcd (b, a % b);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = new Roots().isPrimitiveRoot(2, 7);
		System.out.println(result);

	}

}
