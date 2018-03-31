package com.topcoder.problems.round17;
//http://community.topcoder.com/stat?c=problem_statement&pm=124&rd=3017
public class AlienInvasion
{
  int adj[][] = new int[ 8 ][ 8 ];
  char grid[][] = new char[ 25 ][ 25 ];
  int nr, nc;
  int rloc[] = new int[ 8 ], cloc[] = new int[ 8 ];
  int npts;
  int dist[][] = new int[ 25 ][ 25 ];
  int dr[] = {-1, 0, 1, 0};
  int dc[] = {0, 1, 0, -1};
  boolean visited[] = new boolean[ 8 ];
  int best = 100000;

boolean isvalid( int r, int c )
{
  return (r >= 0 && r < nr && c >= 0 && c < nc) && grid[ r ][ c ] != 'W';
}

public int invasionTime( String[] maze, int sx, int sy )
{
  getinput( maze );
  rloc[ 0 ] = sy; cloc[ 0 ] = sx;
  npts = 1;
  findpts();
  calcdist();

  for (int i = 0; i < npts; i++)
     visited[ i ] = false;

//  for (int i = 0; i < npts; i++)
//     for (int j = 0; j < npts; j++)
//        System.out.println( "(" + rloc[ i ] + "," + cloc[ i ] + ")->(" + rloc[ j ] + "," + cloc[ j ] + ") = " + adj[ i ][ j ] );
  visited[ 0 ] = true;
  search( 0, 0, 1 );

  if (best == 100000) return -1;
  return best;
}

void search( int cur, int sofar, int nvisited )
{
  if (nvisited == npts) {
     if (sofar < best) best = sofar;
     return;
  }

  for (int i = 0; i < npts; i++) {
     if (visited[ i ]) continue;
     if (adj[ cur ][ i ] < 0) continue;
     visited[ i ] = true;
     search( i, sofar + adj[ cur ][ i ], nvisited + 1 );
     visited[ i ] = false;
  }
} 

void getinput( String[] maze )
{
  nr = maze.length;
  nc = ((String) maze[0]).length();

  for (int i = 0; i < nr; i++) {
     String str = (String) maze[i ];
     for (int j = 0; j < nc; j++)
        grid[ i ][ j ] = str.charAt( j );
  }
}

void findpts()
{
  for (int r = 0; r < nr; r++)
     for (int c = 0; c < nc; c++)
        if (grid[ r ][ c ] == 'X') {
           rloc[ npts ] = r; cloc[ npts ] = c;
           npts++;
        }
}

void calcdist()
{
  for (int i = 0; i < npts; i++) {
     for (int r = 0; r < nr; r++)
        for (int c = 0; c < nc; c++)
           dist[ r ][ c ] = -1;
     flood( rloc[ i ], cloc[ i ] );
     for (int j = 0; j < npts; j++)
        adj[ i ][ j ] = dist[ rloc[ j ] ][ cloc[ j ] ];
  }
}

void flood( int sr, int sc )
{
  dist[ sr ][ sc ] = 0;
  for (int t = 0; t < nr * nc; t++)
     for (int r = 0; r < nr; r++)
        for (int c = 0; c < nc; c++) {
           if (dist[ r ][ c ] != t) continue;
//           System.out.println( t + " " + r + " " + c );
           for (int i = 0; i < 4; i++) {
              int rp = r + dr[ i ];
              int cp = c + dc[ i ];
//              System.out.println( rp + "," + cp + " " + grid[ rp ][ cp ] );
              if (isvalid( rp, cp ) && dist[ rp ][ cp ] == -1)
                 dist[ rp ][ cp ] = t + 1;
           }
        }
}

//    "WWWWWWWWWWWWWWWWWWWW",
//    "W W",
//    "W WWW WWWWWWWWWW W",
//    "W W W W",
//    "W W X W W",
//    "W WWWWW W X W",
//    "W W WWWWWWWW",
//    "W WWWWWWWW W XW",
//    "W X W W",
//    "WWWWWWWWWWWWWWWWWWWW"
    public static void main(String[] args) throws InterruptedException{
        int result = new AlienInvasion().invasionTime(new String[]
               {
                "WWWWWWWWWWWWWWWWWWWW",
                "W X                W",
                "W WWW  WWWWWWWWWW  W",
                "W W         W      W",
                "W W X       W      W",
                "W WWWWW     W X    W",
                "W W         WWWWWWWW",
                "W WWWWWWWW  W    X W",
                "W      X W         W",
                "WWWWWWWWWWWWWWWWWWWW"
                }, 1, 1);
        System.out.println(result);
    }
}
