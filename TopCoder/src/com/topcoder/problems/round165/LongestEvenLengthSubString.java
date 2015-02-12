package com.topcoder.problems.round165;
//http://www.geeksforgeeks.org/longest-even-length-substring-sum-first-second-half/
public class LongestEvenLengthSubString
{
    public int simpleSolution(String str)
    {
        int maxLength = Integer.MIN_VALUE;
        // O(N^3)
        for ( int i = 0 ; i < str.length()-1; i++ )
        {
            for ( int j = i+1 ; j < str.length() ; j++ )
            {
                if ( ((j-i)+1) % 2 == 0 )
                {
                    int mid = ((j-i)/2);
                    int leftSum = 0;
                    for ( int k = 0 ; k <= mid ; k++ )
                    {
                        leftSum += Integer.parseInt(""+str.charAt(k));
                    }
                    
                    int rightSum = 0;
                    for ( int k = mid+1 ; k <= j ; k++ )
                    {
                        rightSum += Integer.parseInt(""+str.charAt(k));
                    }
                    if (leftSum == rightSum)
                    {
                        if (((j-i)+1) > maxLength)
                        {
                            maxLength = (j-i)+1;
                        }
                    }
                }
            }
        }
        return maxLength;
    }
    
    //"123123"
    public int dynamicProgrammin( String str)
    {
        System.out.println(str);
        int rowLength = str.length();
        int colLength = str.length() + 1;
        int[][] leftMatrix = new int[rowLength][colLength];
        int[][] rightMatrix = new int[rowLength][colLength];
        
        leftMatrix[0][1] = Integer.parseInt(""+str.charAt(0));
        
        
        for ( int i = 1 ; i < str.length() ; i++)
        {
            int number = Integer.parseInt(""+str.charAt(i));
            int rowIndex = i;
            int colIndex = 1;
            for ( int j = i ; j >=0 ; j--)
            {
                leftMatrix[rowIndex][colIndex] = number ;
                number = leftMatrix[rowIndex][1] + leftMatrix[rowIndex-1][colIndex];
                colIndex++;
            }
        }
        
        for ( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++)
            {
                System.out.print(" "+leftMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("=====================");
        rightMatrix[0][1] = Integer.parseInt(""+str.charAt(str.length()-1));

        int rowIndex = 1;
        for (int i = str.length()-2 ; i >= 0; i--)
        {
            int colIndex = 1;
            int number = Integer.parseInt(""+str.charAt(i));
            for ( int j = i ; j < str.length() ; j++ )
            {
                rightMatrix[rowIndex][colIndex] = number;
                number = rightMatrix[rowIndex][1] + rightMatrix[rowIndex-1][colIndex];
                colIndex++;
            }
            rowIndex++;
        }

        for ( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++)
            {
                System.out.print(" "+rightMatrix[i][j]);
            }
            System.out.println();
        }

        int maxLength = Integer.MIN_VALUE;
        for ( int i = 0 ; i < rowLength-1 ; i++)
        {
            int row1 = i;
            int row2 = i+1;
            int colIndex = 1;
            while ( colIndex < (colLength/2)+1 && 
                    leftMatrix[row1][colIndex] != 0 &&
                    rightMatrix[row2][colIndex] != 0)
            {
                if ( leftMatrix[row1][colIndex] == rightMatrix[row2][colIndex])
                {
                    if ( colIndex > maxLength )
                    {
                        maxLength = colIndex;
                    }
                }
                colIndex++;
            }
        }
        return maxLength;
    }
    
    public static void main(String[] args)
    {
        String str = "1538023";
        int result = new LongestEvenLengthSubString().dynamicProgrammin(str);
        System.out.println(result);
    }

}
