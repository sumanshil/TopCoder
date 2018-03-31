package com.topcoder.problems.round30;
//http://community.topcoder.com/stat?c=problem_statement&pm=165&rd=4000
public class TwoBoxes {
	public int enclosedVolume( int[] Apoints, int[] Bpoints ){
		int xVolume = (Apoints[3]-Apoints[0])*(Apoints[4]-Apoints[1])*(Apoints[5]-Apoints[2]);
		int yVolume = (Bpoints[3]-Bpoints[0])*(Bpoints[4]-Bpoints[1])*(Bpoints[5]-Bpoints[2]);
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
