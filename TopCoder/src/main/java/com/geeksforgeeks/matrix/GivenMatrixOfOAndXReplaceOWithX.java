package com.geeksforgeeks.matrix;

import java.util.ArrayList;
import java.util.List;
//http://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/
public class GivenMatrixOfOAndXReplaceOWithX
{
    char[][] matrix = {{'X', 'O', 'X', 'X', 'X', 'X'},
                       {'X', 'O', 'X', 'X', 'O', 'X'},
                       {'X', 'X', 'X', 'O', 'O', 'X'},
                       {'O', 'X', 'X', 'X', 'X', 'X'},
                       {'X', 'X', 'X', 'O', 'X', 'O'},
                       {'O', 'O', 'X', 'O', 'O', 'O'},
                      };
    class Node
    {
        int i;
        int j;
        Node(int x, int y)
        {
            this.i = x;
            this.j = y;
        }
    }
    
    public void replace()
    {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        List<Node> visitedNodes = new ArrayList<Node>();
        for(int i = 0 ; i < matrix.length ; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++)
            {
                if ( i == 0 || j == 0 )
                {
                    continue;
                }
                
                if ( matrix[i][j] == 'O'
                   && !visited[i][j] )
                {
                    visited[i][j] = true;
                    visitedNodes.add(new Node(i, j));
                    boolean canBeReplaced = findUtil(i,
                                                     j,
                                                     visited,
                                                     visitedNodes );
                    if (canBeReplaced)
                    {
                        for(Node node : visitedNodes)
                        {
                            matrix[node.i][node.j] = 'X';
                            visited[node.i][node.j] = true;
                        }
                    }
                    else
                    {
                        visited[i][j] = false;
                        visitedNodes = new ArrayList<Node>();
                    }
                }
            }
        }
        
        for ( int i = 0 ; i < matrix.length ; i++)
        {
            for ( int j = 0 ; j < matrix[0].length ; j++)
            {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }
    
    
    private boolean findUtil(int x,
                             int y,
                             boolean[][] visited,
                             List<Node> visitedNodes)
    {
        if (isX(x, y))
        {
            return true;
        }
        
        if (isOAtEdge(x, y, visited))
        {
            return false;
        }
        
        boolean result = true;
        for(int i = -1 ; i <= 1 ; i++)
        {
            for(int j = -1 ; j <= 1 ; j++)
            {
                if ( (i == 0 && j == 0)
                        || (i != 0 && j != 0))
                {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                
                
                if ( !isValid(newX, newY ) )
                {
                    continue;               
                }
                if (visited[newX][newY])
                {
                    continue;
                }
                
                visited[newX][newY] = true;
                visitedNodes.add(new Node(newX, newY));
                result = findUtil(newX, newY, visited, visitedNodes);
                visited[newX][newY] = false;
                if (!result)
                {
                    break;
                }                
            }
            if (!result)
            {
                break;
            }
        }
        
        return result;
    }
    
    
    private boolean isValid(int i, int j)
    {
        return ( i >= 0 && i < matrix.length )
                && ( j >=0 && j < matrix[0].length );
    }
    
    
    private boolean isX(int i, int j)
    {        
        return matrix[i][j] == 'X';
    }
    
    
    private boolean isOAtEdge(int i, int j, boolean[][] visited)
    {
        return (i == 0 || i == matrix.length-1 || j == 0 || j == matrix[0].length)
                && matrix[i][j] == 'O';
    }

    
    public void replace1()
    {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        
        for ( int i = 0 ; i < rowLength ; i++ )
        {
            if (matrix[i][0] == 'O')
            {
                floodFill(i,
                          0,
                          rowLength,
                          colLength,
                          'O',
                          '-');
            }
            
            if (matrix[i][colLength-1] == 'O')
            {
                floodFill(i,
                          colLength-1,
                          rowLength,
                          colLength,
                          'O',
                          '-');
            }
        }
        
        for(int j = 0 ; j < colLength ; j++)
        {
            if ( matrix[0][j] == 'O' )
            {
                floodFill(0,
                          j,
                          rowLength,
                          colLength,
                          'O',
                          '-');
            }
            
            if ( matrix[rowLength-1][j] == 'O' )
            {
                floodFill(rowLength-1,
                          j,
                          rowLength,
                          colLength,
                          'O',
                          '-');
            }
        }
        
        for ( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++)
            {
                if ( matrix[i][j] == 'O' )
                {
                    matrix[i][j] = 'X';
                }
            }
        }
        
        for ( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++)
            {
                if ( matrix[i][j] == '-' )
                {
                    matrix[i][j] = 'O';
                }
            }
        }

        for ( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++)
            {
                System.out.print(matrix[i][j] +" ");
            }
            System.out.println();
        }

    }
    
    
    private void floodFill(int row, 
                           int col,
                           int rowLength,
                           int colLength,
                           char sourceChar,
                           char targetChar)
    {
        if (!isValid(row, col))
        {
            return;
        }
        
        if (matrix[row][col] != sourceChar)
        {
            return;
        }
        
        matrix[row][col] = targetChar;
        
        for(int i = -1 ; i <= 1 ; i++)
        {
            for(int j = -1 ; j <= 1 ; j++)
            {
                if ( (i == 0 && j == 0)
                      || ( i != 0 && j != 0))
                {
                    continue;
                }
                int newRow = row + i;
                int newCol = col + j;
                floodFill(newRow, 
                          newCol,
                          rowLength,
                          colLength,
                          sourceChar,
                          targetChar);

            }
        }
    }


    public static void main(String[] args)
    {
        new GivenMatrixOfOAndXReplaceOWithX().replace1();
    }

}
