package com.topcoder.problems;

import java.util.ArrayList;

//http://community.topcoder.com/stat?c=problem_statement&pm=59&rd=3002
public class Population {
    int[][] l = new int[10][10];
    int x;
    int y;
    int total;
	
	public int getPop(int a, java.util.ArrayList<String> al){
		for(String str : al){
			x = str.charAt(0)-48;
			y = str.charAt(2)-48;
			l[x][y] = 1;
		}
		
		
		for(int i = 0 ; i < a ; i++){
			int[][] n = new int[10][10];
			
			for(int j = 0 ; j < 10 ; j++){
				for(int k = 0 ; k < 10 ; k++){
					if ((getNeighborCount(j, k) == 3) 
						|| ((l[j][k] == 1) &&
						    (getNeighborCount(j, k) == 2))){
						n[j][k] = 1;
					}
				}
			}
			l = n;
		}
		
		for(int i =  0 ; i<10 ; i++){
			for(int j = 0 ; j < 10 ; j++){
				if (l[i][j] == 1){
					total++;
				}
			}
		}
		return total;
	}
	
	private int getNeighborCount(int x, int y){
		int count = 0;
		for(int w =( x == 0 ? 0 : -1); w < ( x == 9 ? 1 : 2) ; w++){
			for(int v = (y == 0 ? 0 : -1) ; v < (y == 9 ? 1 : 2) ; v++){
				if ( v != 0 && w != 0){
					if (l[w+x][v+y] == 1){
						count++;
					}
				}
			}
		}
		return count;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList a = new ArrayList();
		a.add("1,1");
        int result = new Population().getPop(5, a);
        System.out.println(result);
	}

}
