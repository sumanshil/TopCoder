package com.topcoder.problems.round18;

public class AlienInvasion {
	private int[] xloc = new int[8];
	private int[] yloc = new int[8];
	private int[] x = {0,0,1,-1};
	private int[] y = {1,-1,0,0};
	private int row ;
	private int column;
	private String[] map ;
	private int minCost = Integer.MAX_VALUE;
	private boolean[] visited ;
	
	public int invasionTime(String[] map, int x, int y){
	    this.map = map;
	    this.visited = new boolean[8];
		int[][] adj = new int[8][8];
		int[][] dist = new int[map.length][map[0].length()];
		this.row = map.length;
		this.column = map[0].length();
		xloc[0] = x;
		yloc[0] = y;
		int index = 1;
		int xCount = 0;
		for(int k = 0 ; k < row ; k++){
			for(int l = 0 ; l < column ; l++){
				dist[k][l] = -1;
				if(map[k].charAt(l) == 'X'){
					xloc[index] = k;
					yloc[index] = l;
					index++;
					xCount++;
				}
			}
		}
		
		for(int i = 0 ; i < xCount ; i++){
			for(int j = 0 ; j < row; j++ ){
				for(int k = 0 ; k < column ; k++){
					dist[j][k] = -1;
				}
			}
			dist[xloc[i]][yloc[i]] = 0;
			floodfill(dist,row,column);
			for(int j = 0 ; j < xCount ; j++){
				if (i != j){
					adj[i][j] = dist[xloc[j]][yloc[j]];
				}
			}
		}
		search(adj, 0, xCount, 0, 0 );
		return minCost;
	}
	
	private void search(int[][] adj, int index, int xCount, int currentCount, int currentCost) {
        if (currentCount == xCount){
        	if (currentCost < minCost){
        		minCost = currentCost;
        	}
        	return;
        }
        
        for(int j = 0 ; j < xCount ; j++){
        	if (visited[j])
        		continue;
        	if (adj[index][j] == -1)
        		continue;
        	visited[j] = true;
        	search(adj, j, xCount, currentCount+1, currentCost+adj[index][j]);
        	visited[j] = false;
        }
		
	}
	private void floodfill(int[][] dist, int row, int column) {
        for(int t = 0 ; t < row*column ; t++){
        	for(int u = 0 ; u < row ; u++){
        		for(int v = 0 ; v < column ; v++){
        			if (dist[u][v] == t){
        	            for(int i = 0; i < 4 ; i++){
        	            	int newX = u + x[i];
        	            	int newY = v + y[i];
        	            	if (isValid(newX, newY) && dist[newX][newY] == -1){
        	            		dist[newX][newY]=t+1;
        	            	}
        	            }
        			}
        		}
        	}
        }
		
	}
	private boolean isValid(int newX, int newY) {
		return (newX>= 0 && newX < row && newY >=0 && newY < column) && map[newX].charAt(newY)!='W';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] map = {"WWWWWWWWWWWWWWWWWWWW",
				        "W                  W",
				        "W WWW  WWWWWWWWWW  W",
				        "W W         W      W",
				        "W W X       W      W",
				        "W WWWWW     W X    W",
				        "W W         WWWWWWWW",
				        "W WWWWWWWW  W    X W",
				        "W      X W         W",
				        "WWWWWWWWWWWWWWWWWWWW"};
		int x = 1;
		int y = 1;
		int result = new AlienInvasion().invasionTime(map, x, y);
		System.out.println(result);

	}

}
