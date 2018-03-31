package com.topcoder.problems.round158;
//http://community.topcoder.com/stat?c=problem_statement&pm=1767&rd=4598s
public class Gems
{

	public int numMoves(String[] board)
	{
		int rowLength = board.length;
		int colLength = board[0].length();
		
		char[][] charArray = new char[rowLength][colLength];
		
		for(int i = 0 ; i < board.length ; i++)
		{
			for(int j = 0 ; j < board[i].length() ; j++)
			{
				charArray[i][j] = board[i].charAt(j);
			}
		}
		
		boolean[][][][] visited = new boolean[50][50][50][50];
		
		int retVal = 0 ;
		for(int i = 0 ; i < rowLength ; i++)
		{
			for(int j = 0 ; j < colLength ; j++)
			{
				for(int x = -1 ; x <=1 ; x++)
				{
					for(int y = -1 ; y <=1 ; y++)
					{
						
						// check if it is going outside the board
						// TODO isolate the logic to a different function
						if (i + x < 0 
							|| i + x >=rowLength
							|| j + y < 0
							|| j + y >= colLength)
						{
							continue;
						}
						
						// check if it is a valid move, diagonal moves are not allowed
						
						if (x+y == 0
							|| x+y == 2
							|| x+y == -2)
						{
							continue;
						}
						
						// check if already visited
						if (visited[i][j][i+x][j+y])
						{
							continue;
						}
					
						swapCharacters(charArray,
								       i,
								       j,
								       i+x,
								       j+y);
								       
						
						if (check(charArray))						
						{
						    retVal++;	
						}
						
						swapCharacters(charArray,
							       i,
							       j,
							       i+x,
							       j+y);
						
						
                        visited[i][j][i+x][j+y] = true;
                        visited[i+x][j+y][i][j] = true;
					}
				}
			}
		}
		return retVal;
	}
	
	private void swapCharacters(char[][] charArray,
			                    int i,
			                    int j,
			                    int x,
			                    int y)
	{
		char c1 = charArray[i][j];
		charArray[i][j] = charArray[x][y];
		charArray[x][y] = c1;				
	}

	private boolean check(char[][] charArray)
	{
		int rowLength = charArray.length;
		int colLength = charArray[0].length;
		
		for(int i = 0 ; i < rowLength ; i++)
		{
			for(int j = 0 ; j < colLength ; j++)
			{
				
				char c = charArray[i][j];
				boolean retVal = true;
				for(int x = 0 ; x < 3 ; x++)
				{
					if (i+x >= rowLength)
					{
						retVal = false;
						break;
					}
					
					if (charArray[i+x][j] != c)
					{
						retVal = false;
						break;
					}					
				}
				if (retVal)
				{
					return true;
				}
				
				retVal = true;
				
				for(int y = 0 ; y < 3 ; y++)
				{
					if (j+y >= colLength)
					{
						retVal =  false;
						break;
					}
					if (charArray[i][j+y] != c)
					{
						retVal =  false;
						break;						
					}
				}
				
				if (retVal)
				{
					return true;
				}
			}			
		}
		return false;
		
	}
	public static void main(String[] args) 
	{
		String[] str = 	{"ABA", "BAB", "ABA"};
		int result = new Gems().numMoves(str);
		System.out.println(result);

	}

}
