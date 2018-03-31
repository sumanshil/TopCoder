package com.topcoder.problems;

import java.util.ArrayList;

public class ObstaclesTopCoder {

	public int visited[][];
	public int best;
	
	public void getLongestPath(ArrayList<Integer> a){
		visited = new int[5][5];
		for(int i = 0 ; i < 5 ; i++){
			for(int j = 0 ;  j < 5; j++){
				visited[i][j] = 0;
			}
		}
		
		for(int index : a){
			visited[index/5][index%5] = 1;
		}
		
		travel(0,0,0,1,1);
		travel(0,0,1,0,1);
		System.out.println("Result "+best);
	}
	
	private void travel(int row, int col, int dr, int dc, int len) {
         if(visited[row][col] == 1)
        	 return;
		 visited[row][col] = 1;
         int good = 0;
         
         if(isSafe(row+dr, col+dc)){
        	 good = 1;
        	 travel(row+dr, col+dc, dr, dc, len+1);
         }
         
         if (good ==0){
        	 if (dc == 0){ // going on column
        		 // turn left
        		 if (isSafe(row, col+1)){ 
        			 good = 1;
        			 travel(row, col+1, 0, 1, len+1);
        		 }
        		 // try right
        		 if (isSafe(row, col-1)){
        			 good = 1;
        			 travel(row, col-1, 0, -1, len+1);
        		 }
        	 } else if (dr == 0){
        		 // turn left
        		 if (isSafe(row+1, col)){ 
        			 good = 1;
        			 travel(row+1, col, 1, 0, len+1);
        		 }
        		 // try right
        		 if (isSafe(row-1, col)){
        			 good = 1;
        			 travel(row-1, col, -1, 0, len+1);
        		 }        		 
        	 }
         
	         if (good == 0){
	        	 // you are screwed
	        	 if (len > best){
	        		 best = len;
	        	 }
	         }
         }
         
         visited[row][col] = 0;
	}

	public boolean isSafe(int x, int y){
		return (x >= 0 && x <=4 && y >=0 && y <=4) && !(visited[x][y]==1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList l = new ArrayList();
		l.add(4);
		l.add(10);
		l.add(11);
		l.add(14);

		new ObstaclesTopCoder().getLongestPath(l);
	}

}
