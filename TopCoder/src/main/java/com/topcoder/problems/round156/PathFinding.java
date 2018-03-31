package com.topcoder.problems.round156;
//http://community.topcoder.com/stat?c=problem_statement&pm=1110&rd=4585
public class PathFinding
{
char[][] grid = new char[24][24];
int nr, nc;
int dist[][] = new int[24*24][24*24];
int queue[]= new int[24*24*24*24];
int qptr = 0, qtail = 0;
int dr[] = {0,-1,-1,-1,0,0,1,1,1};
int dc[] = {0,-1,0,1,-1,1,-1,0,1};

static class Position
{
    int ar;
    int ac;
    int br;
    int bc;
    int stepCount;
    Position(int ar,
             int ac,
             int br,
             int bc,
             int stepCount)
    {
        this.ar = ar;
        this.ac = ac;
        this.br = br;
        this.bc = bc;
        this.stepCount = stepCount;
    }
    
}

public int minTurns(String[] bd)
{
    int rowLength = bd.length;
    int colLength = bd[0].length();
    boolean[][][][] visited= new boolean[20][20][20][20];
    
    int startARow = 0;
    int startACol = 0;
    int startBRow = 0;
    int startBCol = 0;
    
    int ar = 0;
    int ac = 0;
    int br = 0;
    int bc = 0;
    char[][] matrix = new char[rowLength][colLength];
    int best = Integer.MAX_VALUE;
    
    for(int i = 0 ; i < bd.length ; i++)
    {
        for(int j = 0 ; j < bd[i].length() ; j++)
        {
            if (bd[i].charAt(j) == 'A')
            {
                ar = i;
                startARow = ar;
                ac = j;
                startACol = ac;
            }
            else if (bd[i].charAt(j) == 'B')
            {
                br = i;
                startBRow = br;
                bc = j;        
                startBCol = bc;
            }
            else if (bd[i].charAt(j) == 'X')
            {
                matrix[i][j] = 'X';
            }
            else if (bd[i].charAt(j) == '.')
            {
                matrix[i][j] = '.';
            }            
        }
    }    
    
    
    Position[] queue = new Position[24*24*24*24];
    int qstart = 0;
    int qend   = 0;         
    queue[qend++] = new Position(ar, ac, br, bc, 1);
    visited[ar][ac][br][bc] = true;
    while(qstart != qend)
    {
        Position pos = queue[qstart++];
        ar = pos.ar;
        ac = pos.ac;
        br = pos.br;
        bc = pos.bc;
        int stepCount = pos.stepCount;
        for(int aRow = -1 ; aRow <=1 ; aRow++)
        {
            int arr = ar + aRow;
            if ( !(arr >= 0 && arr < rowLength))
            {
                continue;
            }
            
            for(int aCol = -1 ; aCol <= 1 ; aCol ++)
            {            
                int acc = ac + aCol;
                if ( !(acc >= 0 && acc < colLength))
                {
                    continue;
                }
                
                if (matrix[arr][acc] == 'X')
                    continue;
                
                for(int bRow = -1 ;  bRow <=1 ; bRow ++)
                {
                    int brr = br + bRow;
                    if ( !(brr >= 0 && brr < rowLength))
                    {
                        continue;
                    }
                    
                    for(int bCol = -1 ;  bCol <=1 ; bCol ++)
                    {
                        int bcc = bc + bCol;

                        if ( !(bcc >= 0 && bcc < colLength))
                        {
                            continue;
                        }
                        
                        if (matrix[brr][bcc] == 'X')
                            continue;
                        
                        // check for path cross
                        if (ar == brr &&
                            ac == bcc &&
                            br == arr &&
                            bc == acc)
                        {
                            continue;
                        }
                        // check if already visited
                        if (visited[arr][acc][brr][bcc])
                            continue;
                        // check for overlap
                        if (arr == brr
                            && acc == bcc)
                        {
                            continue;
                        }
                        
                        if (arr == startBRow
                            && acc == startBCol
                            && brr == startARow
                            && bcc == startACol)
                        {
                            if (stepCount < best)
                            {
                                best = stepCount;
                            }
                        }
                        queue[qend++] = new Position(arr,
                                                     acc,
                                                     brr,
                                                     bcc,
                                                     stepCount+1);
                        visited[arr][acc][brr][bcc] = true;
                    }                    
                }
            }
        }        
    }   
    return best == Integer.MAX_VALUE ? -1 : best;
}

public int minTurns1(String[] bd)
{
   int sa = 0, sb = 0;
 
   for (int r = 0; r < 24; r++) for (int c = 0; c < 24; c++) grid[r][c] = 'X';
   nr = bd.length; nc = bd[0].length();
   for (int r = 0; r < nr; r++)
      for (int c = 0; c < nc; c++) {
         grid[r+1][c+1] = bd[r].charAt(c);
         if (grid[r+1][c+1] == 'A') {
            sa = 24 * (r+1) + (c+1);
            grid[r+1][c+1] = '.';
         } else if (grid[r+1][c+1] == 'B') {
            sb = 24 * (r+1) + (c+1);
            grid[r+1][c+1] = '.';
         }
      }
 
   for (int i = 0; i < 24*24; i++) for (int j = 0; j < 24*24; j++) dist[i][j] = -1;
   dist[sa][sb] = 0;
   queue[qptr++] = sa * 24 * 24 + sb;
 
   while (qptr != qtail) {
      int cur = queue[qtail++];
      int a = cur / (24 * 24);
      int b = cur % (24 * 24);
      int ar = a / 24, ac = a % 24;
      int br = b / 24, bc = b % 24;
 
//      System.out.println(dist[a][b] + ": " + ar + "," + ac + " " + br + "," + bc);
      for (int i = 0; i < 9; i++) {
         int arr = ar + dr[i], acc = ac + dc[i];
         if (grid[arr][acc] == 'X') continue;
 
         for (int j = 0; j < 9; j++) {
            int brr = br + dr[j], bcc = bc + dc[j];
            if (grid[brr][bcc] == 'X') continue;
 
//            System.out.println(" * " + arr + "," + acc + " " + brr + "," + bcc);
            // check for collision
            if (arr == brr && acc == bcc) continue;
 
            // check for swap positions
            if (ar == brr && ac == bcc && br == arr && bc == acc) continue;
 
            // check for duplicate position
            int aa = arr * 24 + acc;
            int bb = brr * 24 + bcc;
            if (dist[aa][bb] >= 0) continue;
 
            // new position
            dist[aa][bb] = dist[a][b] + 1;
            queue[qptr++] = aa * 24 * 24 + bb;
         }
      }
   }
   return dist[sb][sa];
}
public static void main(String[] args)
{
    int result = new PathFinding().minTurns(new String[]{"AB.................X",
            "XXXXXXXXXXXXXXXXXXX.",
            "X..................X",
            ".XXXXXXXXXXXXXXXXXXX",
            "X..................X",
            "XXXXXXXXXXXXXXXXXXX.",
            "X..................X",
            ".XXXXXXXXXXXXXXXXXXX",
            "X..................X",
            "XXXXXXXXXXXXXXXXXXX.",
            "X..................X",
            ".XXXXXXXXXXXXXXXXXXX",
            "X..................X",
            "XXXXXXXXXXXXXXXXXXX.",
            "X..................X",
            ".XXXXXXXXXXXXXXXXXXX",
            "X..................X",
            "XXXXXXXXXXXXXXXXXXX.",
            "...................X",
            ".XXXXXXXXXXXXXXXXXXX"}
);
    System.out.println(result);
}
}