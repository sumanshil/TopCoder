package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=57&rd=3001
//solution:  http://community.topcoder.com/stat?c=problem_solution&cr=114443&rd=3001&pm=57 
public class Obstacles {

	public static int[][] obstacles= 
	{{0,1,2,3,4},
	 {5,6,7,8,9},
	 {10,11,12,13,14},
	 {15,16,17,18,19},
	 {20,21,22,23,24}
	};
	
	
	public static int currentCount = 1;
	public static int maxCount = 0;
	public static int[][] changes = {{1,0},{0,1},{0,-1},{-1,0}};
	public static Boolean[][] visited = new Boolean[5][5];
	
	static {
		for(int i = 0 ; i < 5 ; i++){
			for(int j = 0 ; j < 5 ; j++){
				visited[i][j]= false;
			}
		}
		visited[0][0] = true;
	}
	
	public static void printArray(int[] param){
		for(int i =0 ; i < param.length ;i++){
			System.out.print(param[i]);
		}
		System.out.println();
	}
	public static int getLongestPath(int[] param){
		
        start(0, 0, param );
		return maxCount;
	}
	
	private static void start(int indexX, int indexY, int[] param) {
        
		for(int i = 0 ; i < 4 ; i++){
			int newX = indexX + changes[i][0];
			int newY = indexY + changes[i][1];
			System.out.println("Trying position " + newX +" "+ newY );
			System.out.println("obstacles ");
			printArray(param);
			if (validPosition(newX, newY, param)){
				System.out.println("Valid position " + newX +" "+ newY);
				visited[newX][newY] = true;
				currentCount++;
				start(newX, newY, param);
				if (currentCount > maxCount){
					maxCount = currentCount;
				}
				visited[newX][newY] = false;
				currentCount--;
			}
			if (newX >=0 && newX <=4 && newY >=0 && newY <=4){
			  
			}
		}
    
		
	}

	private static boolean validPosition(int newX, int newY, int[] param) {
		
		boolean isValid =  (newX >=0 && newX <5 && newY >=0 && newY < 5) && (!visited[newX][newY]);

		if (!isValid)
			return false;
		
		for(int obstacle : param){
			if (obstacles[newX][newY] == obstacle){
				return false;
			}
		}
        return true;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] param = {21, 9, 12, 6};
		int result = getLongestPath(param);
		System.out.println(result);

	}

}
