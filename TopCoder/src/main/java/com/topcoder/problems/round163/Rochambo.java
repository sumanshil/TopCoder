package com.topcoder.problems.round163;

import java.util.HashMap;
import java.util.Map;

//http://community.topcoder.com/stat?c=problem_statement&pm=1810&rd=4620
public class Rochambo
{
    
//    public int wins(String opponent)
//    {
//        Map<String, String> loserToWinner = new HashMap<String, String>();
//        loserToWinner.put("S", "R");
//        loserToWinner.put("P", "S");
//        loserToWinner.put("R", "P");
//        int result = 0;
//        for ( int i = 0 ; i < 2 ; i++ )
//        {
//            if ( isWinningMove(""+opponent.charAt(i),
//                               "R",
//                               loserToWinner) )
//            {
//                result++;
//            }
//        }
//        for ( int i = 2 ; i < opponent.length() ; i++ )
//        {
//            String nextOpponentMove = getNextMove(opponent,
//                                                  i-1,
//                                                  i-2);
//            if (nextOpponentMove.intern()
//                    .equals((""+opponent.charAt(i)).intern()))
//            {
//                result++;
//            }
//        }
//        return result;
//    }
    
      
      public int wins(String opponent)
      {
          int result = 0;
          
          for ( int i = 0 ; i < 2 ; i++ )
          {
              if ( opponent.charAt(i) == 'S')
              {
                  result++;
              }
          }
          
          for ( int i = 2 ; i < opponent.length() ; i++)
          {
              if ( opponent.charAt(i-1) == opponent.charAt(i-2)
                      && opponent.charAt(i-1) == opponent.charAt(i))
              {
                  result++;
              }
              else if ( opponent.charAt(i-1) != opponent.charAt(i-2)
                      && opponent.charAt(i) != opponent.charAt(i-1)
                      && opponent.charAt(i) != opponent.charAt(i-2) )
              {
                  result++;
              }
          }
          return result;
      }
    private String getNextMove(String opponent,
                               int i,
                               int j)
    {
        if ( opponent.charAt(i) == opponent.charAt(j))
        {
            return ""+opponent.charAt(i);
        }
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("R", false);
        map.put("S", false);
        map.put("P", false);
        
        map.put(""+opponent.charAt(i), true);
        map.put(""+opponent.charAt(j), true);
        
        for(Map.Entry<String, Boolean> entry : map.entrySet())
        {
            if (entry.getValue().equals(false))
            {
                return entry.getKey();
            }
        }
        return null;
    }
    
    
    private boolean isWinningMove(String opponent,
                                  String myMove,
                                  Map<String, String> looserToWinner)
    {
        if (looserToWinner.containsKey(opponent))
        {
            String winnerMove = looserToWinner.get(opponent);
            return (winnerMove.intern().equals(myMove.intern()));
        }
        return false;        
    }
    
    
    public static void main(String[] args)
    {
        int result = new Rochambo().wins("RPPPRRPSSSRRRSRSPPSSPRRPSRRRRSPPPPSSPSSSSSRSSSRPRR");
        System.out.println(result);
    }

}
