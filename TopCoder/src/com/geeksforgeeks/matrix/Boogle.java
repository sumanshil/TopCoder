package com.geeksforgeeks.matrix;

import java.util.LinkedList;
import java.util.List;

public class Boogle
{
    char[][] matrix = 
        {
            {'G','I','Z'},
            {'U','E','K'},
            {'Q','S','E'}
        };
     
    public boolean isWord(String str)
    {
        boolean[][] visited = new boolean[matrix[0].length][matrix.length];
        for(int i = 0 ; i < matrix[0].length ; i++)
        {
            for(int j = 0 ; j < matrix.length; j++)
            {
                List<String> list = new LinkedList<String>();
                visited[i][j] = true;
                list.add(""+matrix[i][j]);
                boolean isPresent = findWordRecursive(i,
                                                      j,
                                                      list,
                                                      str,
                                                      visited); 
                list.remove(list.size()-1);
                visited[i][j] = false;
                if (isPresent)
                {
                    return isPresent;
                }
            }
        }
        return false;
    }
    
    
    
    private boolean findWordRecursive(int x,
                                      int y,
                                      List<String> list,
                                      String str,
                                      boolean[][] visited)
    {
        String stringSoFar = getString(list);
        if (stringSoFar.length() > 0
                && stringSoFar.length() < str.length())            
        {
            if (!str.substring(0, stringSoFar.length()).intern().equals(stringSoFar.toString().intern()))
            {
                return false;
            }
        }
        else if (stringSoFar.length() > str.length())
        {
            return false;
        }
        else if (stringSoFar.toString().intern().equals(str.intern()))
        {
            return true;
        }
        
        
        boolean result = false;
        for(int i = -1 ; i <= 1 ; i++)
        {
            for(int j = -1 ; j <= 1 ; j++)
            {
                if ( i== 0 && j == 0)
                    continue;
                int newX = x + i;
                int newY = y + j;
                        
                if (isValid(newX, newY, visited))
                {
                    visited[newX][newY] = true;    
                    list.add(""+matrix[newX][newY]);
                    result = findWordRecursive(newX,
                                               newY,
                                               list,
                                               str,
                                               visited);
                    list.remove(list.size()-1);
                    visited[newX][newY] = false;
                    if (result)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    private String getString(List<String> list)
    {
        StringBuffer sb = new StringBuffer();
        for(String str : list)
        {
            sb.append(str);
        }
        return sb.toString();
    }



    private boolean isValid(int i, int j, boolean[][] visited)
    {
        return (i >= 0
                && i < matrix[0].length 
                && j >= 0 
                && j < matrix.length 
                && !visited[i][j]);
    }


    public static void main(String[] args)
    {
        boolean result = new Boogle().isWord("ABRA");
        System.out.println(result);

    }

}
