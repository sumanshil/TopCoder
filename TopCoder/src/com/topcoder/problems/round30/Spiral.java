package com.topcoder.problems.round30;
//http://community.topcoder.com/stat?c=problem_statement&pm=166&rd=4000
public class Spiral {
	class MatrixNode{
		public MatrixNode(int i, int j, int direction) {
			this.i = i;
			this.j = j;
			this.direction = direction;
		}
		public MatrixNode(){
			
		}
		int i ;
		int j ;
		int direction; //0-> right, 1 -> down, 2 -> left, 3 - up
	}
    public int numberAt(int nRows, int nCols, int row, int col){
    	int[][] matrix = new int[nRows][nCols];
    	int count =1 ;
    	matrix[0][0] = count++;
    	MatrixNode mn = new MatrixNode();
    	int r = 0;
    	int c = 0;
    	mn.i = r;
    	mn.j = c;
    	mn.direction = 0;
    	while((mn = getNextPlace(mn,nRows, nCols, matrix)) != null){
    		matrix[mn.i][mn.j] = count++;
    		r = mn.i;
    		c = mn.j;
    	}
    	print(matrix);
    	return matrix[row][col];
    }
    
    public void print(int[][] matrix){
    	for(int i = 0 ; i < matrix.length ; i++){
    		for(int j = 0 ; j < matrix[0].length ; j++){
    			System.out.print(matrix[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
	private MatrixNode getNextPlace(MatrixNode mn, int nRows, int nCols, int[][] matrix) {

		if (mn.direction == 0){ //we are at right
			if (checkRight(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i, mn.j+1, mn.direction);
			}
			if (checkDown(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i+1, mn.j, 1);	
			}

		}
		if (mn.direction == 1){ // we are at down
			if (checkDown(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i+1, mn.j, mn.direction);	
			}
			if (checkLeft(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i, mn.j-1, 2);	
			}						
		}

		if (mn.direction == 2){ // we are at left
			if (checkLeft(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i, mn.j-1, mn.direction);	
			}
			if (checkUp(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i-1, mn.j, 3);	
			}						
		}

		if (mn.direction == 3){ // we are at up
			if (checkUp(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i-1, mn.j, mn.direction);	
			}
			if (checkRight(mn.i, mn.j, nRows, nCols, matrix)){
				return new MatrixNode(mn.i, mn.j+1, 0);	
			}						
		}
		
		return null;
	}
	
	private boolean checkDown(int i, int j, int nRows, int nCols, int[][] matrix) {
		// check down
		if ((i+1) < nRows && matrix[i+1][j] == 0){
			return true;
		}		
		return false;
	}

	private boolean checkRight(int i, int j, int rows, int nCols, int[][] matrix){
		if ( (j+1) <nCols && matrix[i][j+1]  == 0){
			return true;
		}				
		return false;
	}
	
	private boolean checkLeft(int i, int j, int rows, int nCols, int[][] matrix){
		if ((j-1) >= 0 && matrix[i][j-1] == 0){
			return true;
		}
		return false;
	}
	
	private boolean checkUp(int i, int j, int rows, int nCols, int[][] matrix){
		if ((i-1) >= 0 && matrix[i-1][j] == 0){
			return true;
		}
		return false;
	}	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = new Spiral().numberAt(		4, 3, 2, 1);
        System.out.println(result);
	}

}
