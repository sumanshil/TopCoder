package com.topcoder.problems.round167;

import java.util.*;
public class BitmapToGraphTC
{
  
  int[] dx = {-1,-1,-1, 0, 1, 1, 1,  0};
  int[] dy = {-1, 0, 1, 1, 1, 0,-1, -1};
  int m, n;
  String[] s;
  
  public char safelookup(int x, int y) 
  {
    if (x < 0 || y < 0 || x >= m || y >= n) return ' '; else return s[x].charAt(y);
  }
  
  public String[] parse(String[] bitmap) 
  {
    s = bitmap;
    int[][] lookup = new int[bitmap.length][bitmap[0].length()];
    n = bitmap[0].length();
    m = bitmap.length;
    int nodes=0;
    for (int i=0; i<m; i++) for (int j=0; j<n; j++) 
    if (bitmap[i].charAt(j) == 'N') lookup[i][j] = nodes++;
    String[] result = new String[nodes];
    nodes = 0;
    for (int i=0; i<m; i++) for (int j=0; j<n; j++) 
    if (bitmap[i].charAt(j) == 'N') {
      result[nodes] = "";
      TreeSet edges = new TreeSet();
      for (int d0 = 0; d0 < 8; d0++)
      if (safelookup(i+dx[d0], j+dy[d0])=='E')
      {
        int d = d0;
        int px = i+dx[d];
        int py = j+dy[d];
        int count = 1;
        int r=0;
        boolean boo = true;
        while (boo)
        {
          if (safelookup(px+dx[d], py+dy[d])=='E')
          { px += dx[d]; py += dy[d]; count++; }
          else if (safelookup(px+dx[d], py+dy[d])=='N')
          { px += dx[d]; py += dy[d]; boo=false;}
          else if (safelookup(px+dx[(d+1)%8], py+dy[(d+1)%8])=='E')
          { d = (d+1)%8; px += dx[d]; py += dy[d]; count++; }
          else if (safelookup(px+dx[(d+7)%8], py+dy[(d+7)%8])=='E')
          { d = (d+7)%8; px += dx[d]; py += dy[d]; count++; }
          else if (safelookup(px+dx[(d+1)%8], py+dy[(d+1)%8])=='N')
          { d = (d+1)%8; px += dx[d]; py += dy[d]; boo=false; }
          else if (safelookup(px+dx[(d+7)%8], py+dy[(d+7)%8])=='N')
          { d = (d+7)%8; px += dx[d]; py += dy[d]; boo=false; }
          else return new String[0];
        }
        edges.add(new Integer(lookup[px][py]*80000 + count*8+d0));
 
      }
      Iterator q = edges.iterator();
      boolean blah = false;
      while (q.hasNext())
      {
        int z = ((Integer)q.next()).intValue(); 
        int index = z/80000;
        int lengt = (z%80000)/8;
        if (index==nodes) q.next();
        if (blah) result[nodes] += ',';
        result[nodes] += index+":"+lengt;
        blah = true;
      }
      nodes++;
    }
    return result;
  }
  
  public static void main(String[] args)
  {
       String[] str = {".EE.",
                       "N..N",
                       ".EE."};
       String[] result= new BitmapToGraphTC().parse(str);
       for ( String str1 : result)
       {
           System.out.println(str1);
       }
  }
}
 