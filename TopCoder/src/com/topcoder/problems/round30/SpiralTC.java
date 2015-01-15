package com.topcoder.problems.round30;
//http://community.topcoder.com/stat?c=problem_statement&pm=166&rd=4000
public class SpiralTC {
    int[] x = {0,1,0,-1};
    int[] y = {1,0,-1,0};
    
    public int numberAt(int nRows, int nCols, int row, int col){
        int d = 0;
        int count = 0;
        int r = 0;
        int c = 0;
        int[][] matrix = new int[nRows][nCols];
        while(r>=0 && c>=0 && r <nRows && c <nCols && matrix[r][c] == 0){
        	matrix[r][c] = ++count;
        	r += x[d];
        	c += y[d];
        	if (r <0 || c <0 || r>= nRows || c>= nCols || matrix[r][c] != 0){
        		r -= x[d];
        		c -= y[d];
            	d = (d+1)%4;
            	r += x[d];
            	c += y[d];        		
        	}
        }
        return matrix[row][col];
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = new SpiralTC().numberAt(10, 20, 9, 19);
		System.out.println(result);

	}

}
