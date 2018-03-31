package com.topcoder.problems.round164;

public class DesertWind
{
    private static final int MAX_VALUE = 10000;
    public int daysNeeded(String[] map)
    {
        int[][] best = new int[map.length][map[0].length()];
        int rowLength = map.length;
        int colLength = map[0].length();
        for( int i = 0 ; i < rowLength ; i++)
        {
            for( int j = 0 ; j < colLength ; j++)
            {
                if ( map[i].charAt(j) == '*' )
                {
                    best[i][j] = 0;
                }
                else
                {
                    best[i][j] = MAX_VALUE;
                }
            }
        }
        
        boolean updated = true;
        while ( updated )
        {
            updated = false;
            for ( int i = 0 ; i < rowLength ; i++ )
            {
                for ( int j = 0 ; j < colLength ; j++ )
                {
                    int closest = MAX_VALUE;
                    int nextClosest = MAX_VALUE;
                    
                    for ( int k = -1 ; k < 2 ; k++ )
                    {
                        for ( int l = -1 ; l < 2 ; l++ )
                        {
                            int xx = i + k;
                            int yy = j + l;
                            
                            if ( xx < 0 
                              || yy < 0
                              || xx >= rowLength
                              || yy >= colLength
                              || map[xx].charAt(yy) == 'X' )
                            {
                                continue;                                
                            }
                            if ( best[xx][yy] < closest )
                            {
                                nextClosest = closest;
                                closest = best[xx][yy];
                            }
                            else if ( best[xx][yy] < nextClosest  )
                            {
                                nextClosest = best[xx][yy];
                            }                            
                        }
                    }
                    
                    int bestLength = Math.min(best[i][j],
                                     Math.min(closest+3, nextClosest+1));
                    if ( bestLength < best[i][j] )
                    {
                        best[i][j] = bestLength; 
                        updated = true;
                    }                    
                }
            }
        }
        
        for( int i = 0 ; i < rowLength ; i++ )
        {
            for ( int j = 0 ; j < colLength ; j++ )
            {
                if ( map[i].charAt(j) == '@' )
                {
                    return ( best[i][j] == MAX_VALUE ) ? -1 : best[i][j];
                }
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        String[] arr = {"--*","@-*","X--"};
        int result = new DesertWind().daysNeeded(arr);
        System.out.println(result);
    }

}
