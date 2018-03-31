package com.geeksforgeeks.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindWordInMatrix
{	
	char[][] matrix = 
		{
			{'a', 'b' , 'c', 'd'},
			{'e', 'e' , 'g', 'h'},
			{'i', 'j' , 'k', 'l'},
			{'a', 'e' , 'f', 'm'},
			{'x', 'e' , 'c', 'd'},
			{'y', 'f' , 'c', 'd'},
		};

	static class MatrixPosition
	{
		int x;
		int y;
		public MatrixPosition(int x,
				              int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node
	{
		public Node(String word, int i, int j)
		{
		    this.word = word;
		    this.matrixPosition = new MatrixPosition(i, j);
		}
		String word;
		MatrixPosition matrixPosition;
		Node prevNode = null;
		
		
		public boolean isNodeAlreadyPresent(int x, int y)
		{
			boolean result = find(this, x, y);
			return result;
		}
		
		
		private boolean find(Node node, int x, int y)
		{
			if (node == null)
			    return false;
			if (node.matrixPosition.x == x &&
				node.matrixPosition.y == y	)
			{
				return true;
			}
			
			return find (node.prevNode, x, y);			
		}
	}
		
	private int rowLength;
	private int colLength;
	
	public void findWord(String word)
	{
		this.rowLength = matrix.length;
		this.colLength = matrix[0].length;
		
		List<Node> list = getStartingPoints(rowLength,
				                            colLength,
				                            word.charAt(0));
		
		for (Node node : list)
		{
		    int row = node.matrixPosition.x;
		    int col = node.matrixPosition.y;
			boolean result = search(node, word);
			if (result)
			    System.out.println("Word found at "+ row +" "+col);
		}
		
	}
	
	int[] xDist = {1,1,1,0,0,-1,-1,-1};
	int[] yDist = {1,0,-1,-1,1,1,0,-1};		
	boolean[][] visited = null;
	private boolean search(Node node, String word)
	{
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while(!queue.isEmpty())
		{
			Node currentNode = queue.remove();
			if (currentNode.word.length() == word.length())
			{
				// we found a match
				if (currentNode.word.equals(word))
				{
					return true;
				}
			}
			else if (currentNode.word.length() < word.length()
					&& isPartialMatch(currentNode, word))
			{
				int currentX = currentNode.matrixPosition.x;
				int currentY = currentNode.matrixPosition.y;
				for(int i = 0 ; i < 8 ; i++)
				{
					int newX = currentX + xDist[i];
					int newY = currentY + yDist[i];
					if (isValid(newX, newY, currentNode))
					{
						Node newNode = new Node(currentNode.word+
								                            matrix[newX][newY],
								                            newX,
								                            newY);
						newNode.prevNode = currentNode;
						queue.add(newNode);
					}
				}
			}
		}
		return false;
	}

    // we will check if substring matches. If not then there is no
	// point in going ahead
	private boolean isPartialMatch(Node currentNode, String word)
	{
	    return currentNode.word.equals(word.substring(0,
                                                   currentNode.word.length()));
	}


	private boolean isValid(int newX, int newY, Node node)
	{
		return     isValidRow(newX)
				&& isValidCol(newY)
				&& !isVisited(newX,newY, node);
		
	}


	private boolean isValidCol(int newY)
	{
		return newY >=0 && newY < colLength;
	}


	private boolean isVisited(int newX,
			                  int newY,
			                  Node currentNode)
	{
		return currentNode.isNodeAlreadyPresent(newX, newY);
	}


	private boolean isValidRow(int newX)
	{
		return newX >=0 && newX < rowLength;
	}


	private List<Node> getStartingPoints(int rowLength,
			                             int colLength,
			                             char charToFind)
	{
		List<Node> retVal = new ArrayList<Node>();
		for(int i = 0 ; i < rowLength ; i ++)
		{
			for(int j = 0; j < colLength ; j++)
			{
				if (isMatchingChar(i, j, charToFind))
				{
					retVal.add(new Node(""+charToFind, i, j));
				}
			}
		}
		return retVal;
	}


	private boolean isMatchingChar(int i, int j, char charToFind)
	{
		return (matrix[i][j] == charToFind);		
	}

	public void searchRecursive(String word)
	{
		this.rowLength = matrix.length;
		this.colLength = matrix[0].length;
		List<Node> startingPoints = getStartingPoints(rowLength,
				                                      colLength,
				                                      word.charAt(0));
		visited = new boolean[rowLength][colLength];
		for(Node node : startingPoints)
		{
			boolean result = searchUtil(node.matrixPosition.x,
					                    node.matrixPosition.y,
					                    ""+word.charAt(0),
					                    word);
			if (result)
			{
				System.out.println("Match found at "+node.matrixPosition.x +
						           " "+node.matrixPosition.y);
			}
		}
	}

//	char[][] matrix = 
//		{
//			{'a', 'b' , 'c', 'd'},
//			{'e', 'e' , 'g', 'h'},
//			{'i', 'j' , 'k', 'l'},
//			{'a', 'e' , 'f', 'm'},
//			{'x', 'e' , 'c', 'd'},
//			{'y', 'f' , 'c', 'd'},
//		};

	
	private boolean searchUtil(int x,
			                   int y,
			                   String currentWord,
			                   String word)
	{

		if (word.length() == currentWord.length())
		{
			if (word.equals(currentWord))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (word.length() > currentWord.length())
		{
			if (!currentWord.equals(word.substring(0, currentWord.length())))
			{
				return false;
			}
		}
		
		visited[x][y] = true;
		for(int i = 0 ; i < 8 ; i++)
		{
			int newX = x+xDist[i];
			int newY = y+yDist[i];
			
			if (!isValidRow(newX) ||
				!isValidCol(newY) ||
				 visited[newX][newY])
			{
				continue;
			}

			boolean result = searchUtil(newX, 
					                    newY, 
					                    currentWord+matrix[newX][newY], 
					                    word);
			if (result)
			{
				return true;
			}
		}
		visited[x][y] = false;
		return false;
	}
	

	public static void main(String[] args)
	{
		new FindWordInMatrix().searchRecursive("cfkl");
	}
	
}
