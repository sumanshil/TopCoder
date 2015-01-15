package com.topcoder.problems.round157;
//http://community.topcoder.com/stat?c=problem_statement&pm=1781&rd=4590
import java.util.ArrayList;
import java.util.List;

public class Table
{
    static class Cell
    {
        int rowSpan;
        int colSpan;
        String c;
        public Cell( int colSpan,
                     int rowSpan,
                     String c)
        {
            this.rowSpan = rowSpan;
            this.colSpan = colSpan;
            this.c       = c;
        }
    }

    public String[] layout(String[] tbl)
    {
        int rowLength = tbl.length;
        int colLength = 0 ;
        
        String str = tbl[0];
        
        int start = 0;
        for(int i = start ; i < str.length() ; i++)
        {
            if (i == -1)
                break;
            int open = str.indexOf('(',i);
            int close = str.indexOf(')', open+1);
            String l1 = str.substring(open+1, close);
            String[] arr= l1.split(",");
            int colNumber = Integer.parseInt(arr[0]);
            colLength += colNumber;
            start = str.indexOf('(', close+1);
            i = start-1;
        }
        
        char[][] matrix = new char[rowLength][colLength];
        for(int i = 0 ; i < rowLength ; i++)
        {
            for(int j= 0; j < colLength ; j++)
            {
                matrix[i][j] = ' ';
            }
        }
        
        for(int i = 0 ; i < rowLength ; i++)
        {
            String str1 = tbl[i];
            int colIndex = 0;
            Cell[] cells = getCellInfo(str1);
            int cellIndex = 0;
            for(int j = 0 ; j < colLength ; j++)
            {
                if (matrix[i][j] != ' ')
                    continue;
                else
                {
                    Cell cell = cells[cellIndex++];
                    int rowSpan = cell.rowSpan;
                    int colSpan = cell.colSpan;
                    String s = cell.c;
                    for(int k = i ; k < i+rowSpan ; k++)
                    {
                        for(int l = j ; l < j+colSpan ; l++)
                        {
                            matrix[k][l] = s.charAt(0);
                        }
                    }
                }
            }
        }
        
//        for(int i = 0 ; i < rowLength ; i++)
//        {
//            for(int j = 0 ; j < colLength ; j++)
//            {
//                System.out.print(matrix[i][j]+" ");
//            }
//            System.out.println();
//        }
//        
        String[] retVal = new String[rowLength];
        int index = 0;
        for(int i = 0 ; i < rowLength ; i++)
        {
            StringBuffer sb = new StringBuffer();
            for(int j = 0 ;j < colLength ; j++)
            {
                sb.append(matrix[i][j]);
            }
            retVal[index++] = sb.toString();
        }
        return retVal;
    }
    
    
    
    private Cell[] getCellInfo(String str)
    {
        int start  = 0;
        List<Cell> list = new ArrayList<Cell>();
        for(int i = start ; i  < str.length() ;  i++)
        {
            int open = str.indexOf('(', i);
            int close = str.indexOf(')', open+1);
            String st = str.substring(open+1, close);
            String[] arr = st.split(",");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            String s = arr[2];
            list.add(new Cell(a, b, s)); 
            i = close;
        }
        return (Cell[])list.toArray(new Cell[list.size()]);
    }



    public static void main(String[] args)
    {
        String[] result = new Table().layout(new String[]
                {"(1,3,N)(3,2,E)(3,1,M)(1,1,Q)",
                "(1,1,T)(3,1,U)",
                "(1,1,Y)(4,5,A)(1,2,V)(1,2,W)",
                "(1,3,G)(1,3,Z)",
                "(1,2,S)(1,3,D)",
                "",
                "(1,2,P)(1,2,F)(1,3,J)",
                "(1,1,L)(3,3,K)(1,1,R)",
                "(3,2,B)(1,1,D)",
                "(2,1,A)",
                "(2,3,O)(4,1,X)(1,1,I)(1,1,B)",
                "(3,2,H)(3,2,C)",
                ""});
        for(String str : result)
        {
            System.out.println(str);
        }
    }

}
