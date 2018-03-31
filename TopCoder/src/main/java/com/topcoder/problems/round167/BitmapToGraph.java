package com.topcoder.problems.round167;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


//http://community.topcoder.com/stat?c=problem_statement&pm=1882&rd=4701
public class BitmapToGraph
{
    boolean[][] visitedMatrix = null;
    int[][] numberMatrix = null ; // this will hold the matrix
                                  // that contain the node numbers
    
    class Coordinate 
    {
        int x;
        int y;
        public Coordinate(int newX, int newY)
        {
            this.x = newX;
            this.y = newY;
        }
        
        public boolean equals(Coordinate coordinate)
        {
            return this.x == coordinate.x 
                   && this.y == coordinate.y;
        }
    }
    
    class DestinationDistanceInfo implements Comparable<DestinationDistanceInfo>
    {
        int destination;
        int distance;
        Coordinate sourceCoordinate;
        Coordinate destinationCoordinate;
        public DestinationDistanceInfo( int destination,
                                        int distance,
                                        Coordinate sourceCoordinate,
                                        Coordinate destCoordinate)
        {
            this.destination = destination;
            this.distance = distance;
            this.sourceCoordinate = sourceCoordinate;
            this.destinationCoordinate = destCoordinate;
        }
        
        public int hashCode()
        {
            return this.destination*1000 + this.distance;    
        }
        
        public boolean equals( Object obj )
        {
            DestinationDistanceInfo other = (DestinationDistanceInfo)obj;
            if ( other.destination == this.destination 
                 && other.distance == this.distance 
                 && (( other.sourceCoordinate.equals(this.sourceCoordinate)
                      && other.destinationCoordinate.equals(this.destinationCoordinate))
                     || (other.sourceCoordinate.equals(this.destinationCoordinate)
                             && other.destinationCoordinate.equals(this.sourceCoordinate)) ))
            {
                return true;
            }
            return false;
        }
        
        public String toString()
        {
            return "Destination "+this.destination+":"+
                   "Distance "+this.distance;
        }

        @Override
        public int compareTo(DestinationDistanceInfo other)
        {            
            if ( other.destination != this.destination )
            {
                return -(other.destination - this.destination);
            }
            else if (other.distance != this.distance)
            {
                return -(other.distance - this.distance);
            }
            else
            {
                return 1;
            }
        }
        
    }
    
    class DistanceInformation
    {
        DistanceInformation next;
        Set<DestinationDistanceInfo>
             set = new HashSet<DestinationDistanceInfo>();
        
        public void add( int destination,
                         int distance,
                         Coordinate sourceCoordinate,
                         Coordinate destCoordinate)
        {
             DestinationDistanceInfo obj = new DestinationDistanceInfo(destination,
                                                                       distance,
                                                                       sourceCoordinate,
                                                                       destCoordinate);
             if ( !set.contains(obj) )
             {
                 set.add(obj);
             }
        }
        
        public String toString()
        {            
            TreeSet<DestinationDistanceInfo> sorted = new TreeSet<DestinationDistanceInfo>(set);
            StringBuilder sb = new StringBuilder();
            for( DestinationDistanceInfo dd : sorted )
            {
                sb.append(dd.destination+":"+dd.distance);
                sb.append(",");
            }
            String retVal = "";
            if ( sb.toString().length() > 0 )
            {
                retVal = sb.substring(0, sb.length()-1);
            }
            return retVal;
        }
    }
    
    
    DistanceInformation[] distanceInfoArr = null;
    public String[] parse(String[] bitmap)
    {
        numberMatrix = new int[bitmap.length][bitmap[0].length()];
        // -1 represents an edge
        // -2 reprsents an nogo
        int numberOfNodes = 0;
        for ( int i = 0 ; i < bitmap.length ; i++ )
        {
            for ( int j = 0 ; j < bitmap[i].length() ; j++ )
            {
                if ( bitmap[i].charAt(j) == 'N' )
                {
                    numberMatrix[i][j] = numberOfNodes++;
                }
                else if ( bitmap[i].charAt(j) == 'E' )
                {
                    numberMatrix[i][j] = -1;
                }
                else
                {
                    numberMatrix[i][j] = -2;
                }
            }
        }

        distanceInfoArr = new DistanceInformation[numberOfNodes];
        for ( int i = 0 ; i < distanceInfoArr.length ; i++ )
        {
            distanceInfoArr[i] = new DistanceInformation();
        }

        visitedMatrix = new boolean[numberOfNodes][numberOfNodes];
        
        for ( int i = 0 ; i < numberMatrix.length ; i++ )
        {
            for ( int j = 0 ; j  < numberMatrix[i].length; j++ )
            {
                if ( numberMatrix[i][j] >= 0 )
                {
                    for (int k = -1 ; k <= 1 ; k++)
                    {
                        for (int l = -1 ; l <=1 ; l++)
                        {
                            if ( k == 0 && l == 0)
                            {
                                continue;
                            }
                            int newX = i + k;
                            int newY = j + l;
                            if (isValidRow(newX)
                                && isValidCol(newY)
                                && numberMatrix[newX][newY] == -1)
                            {
                                calculateDistance(newX,
                                                  newY,
                                                  new Coordinate(newX, newY),
                                                  null,
                                                  i, // sourceX
                                                  j, // sourceY
                                                  k, // directionX
                                                  l, // directionY
                                                  1); // current distance
                            }
                        }
                    }
                }
            }
        }
        
        String[] retVal = new String[numberOfNodes];
        for ( int i = 0 ; i < numberMatrix.length ; i++ )
        {
            for (int  j = 0 ; j < numberMatrix[0].length ; j++ )
            {
                if ( numberMatrix[i][j] >= 0 )
                {
                    retVal[numberMatrix[i][j]] = distanceInfoArr[numberMatrix[i][j]].toString();
                }
            }
        }
        return retVal;
    }
    
    
    private void calculateDistance(int currentX,
                                   int currentY,
                                   Coordinate sourceCoordinate,
                                   Coordinate destCoordinate,
                                   int sourceX,
                                   int sourceY,
                                   int directionX,
                                   int directionY,
                                   int currentDistance)
    {
        if ( currentDistance > 0
             && numberMatrix[currentX][currentY] >= 0 )
        {
            {
                int source = numberMatrix[sourceX][sourceY];
                int destination = numberMatrix[currentX][currentY];
                distanceInfoArr[source].add(destination,
                                            currentDistance-1,
                                            sourceCoordinate,
                                            destCoordinate);
                if (source != destination)
                {
                    distanceInfoArr[destination].add(source,
                                                     currentDistance-1,
                                                     destCoordinate,
                                                     sourceCoordinate);
                }
                return ;
            }
        }
        
        if ( directionX != 0 || directionY != 0 )
        {
            int newX = currentX + directionX;
            int newY = currentY + directionY;
            if ( isValidRow(newX)
                 && isValidCol(newY)
                 && (numberMatrix[newX][newY] == -1
                    || numberMatrix[newX][newY] >= 0) )
            {
                calculateDistance(currentX+directionX,
                                  currentY+directionY,
                                  sourceCoordinate,
                                  new Coordinate(currentX, currentY),
                                  sourceX,
                                  sourceY,
                                  directionX,
                                  directionY,
                                  currentDistance+1);
                return;
            }
        }
        
        Coordinate newCoordinate =  getNewCordinate(currentX,
                                                    currentY,
                                                    directionX,
                                                    directionY);
        int newX = currentX+ newCoordinate.x;
        int newY = currentY+ newCoordinate.y;
       // System.out.println(newX +" : "+newY);
        if ( isValidRow(newX) &&
             isValidCol(newY) &&   
             (numberMatrix[newX][newY] == -1
             || numberMatrix[newX][newY] >= 0) )
        {
            calculateDistance(newX,
                              newY,
                              sourceCoordinate,
                              new Coordinate(currentX, currentY),                              
                              sourceX,
                              sourceY,
                              newCoordinate.x,
                              newCoordinate.y,
                              currentDistance+1);
        }
    }


    private Coordinate getNewCordinate(int currentX,
                                       int currentY,
                                       int directionX,
                                       int directionY)
    {     
        if ( directionX == 0 )
        {
            // it is going row wise.
            // 
            int newX = currentX - 1;
            int newY = currentY + directionY;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY) )
            {
                return new Coordinate(-1, directionY);
            }
            
            newX = currentX + 1;
            newY = currentY + directionY;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY) )
           {
               return new Coordinate(1, directionY);
           }
        }
        else if ( directionY == 0 )
        {
            // it is going column wise.
            // 
            int newX = currentX + directionX;
            int newY = currentY - 1;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY) )
            {
                return new Coordinate(directionX, -1);
            }
            
            newX = currentX + directionX;
            newY = currentY + 1 ;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY) )
           {
               return new Coordinate(directionX, 1);
           }            
        }
        else
        {
            int newX = currentX;
            int newY = currentY + directionY;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY) )
            {
                return new Coordinate(0, directionY);
            }
            
            newX = currentX + directionX;
            newY = currentY ;
            if ( isValidRow(newX)
                 && isValidCol(newY) 
                 && isValidPosition(newX, newY))
           {
               return new Coordinate(directionX, 0);
           }                        
        }
        return null;
    }
    
    private boolean isValidPosition( int x, int y)
    {
        return numberMatrix[x][y] == -1 
               || numberMatrix[x][y] >=0;
    }

    private boolean isValidCol(int col)
    {        
        return col >=0 && col < numberMatrix[0].length ;
    }


    private boolean isValidRow(int row)
    {
        return row >=0 && row < numberMatrix.length ;
    }


    public static void main(String[] args)
    {
        String[] arr = {"NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EEE..E..EEE..E..E..EEE..E..EEE..E..E..EEE..E..EEE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EEE..E..EEE..E..E..EEE..E..EEE..E..E..EEE..E..EEE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EEE..E..EEE..E..E..EEE..E..EEE..E..E..EEE..E..EEE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EEE..E..EEE..E..E..EEE..E..EEE..E..E..EEE..E..EEE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "E..E.NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN.E..E", "NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEN", "EE.E.E.E.E...E.EEE.E.E.EEE.E.E.EEE.E...E.E.E.E.EE", "NENEENEENENEENEENEENENEENEENENEENEENEENENEENEENEN", "................................................."};


        String[] str = new BitmapToGraph().parse(arr);
        for ( String s : str)
        {
            System.out.println(s);
        }
//        char[] arr = {'a', 'b', 'c'};
//        fun(arr);
    }


    private static void fun(char[] arr)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(arr+"\n");
        System.out.println(sb.toString());        
    }

}
