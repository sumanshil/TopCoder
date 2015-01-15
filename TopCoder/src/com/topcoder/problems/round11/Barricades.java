package com.topcoder.problems.round11;


//http://community.topcoder.com/stat?c=problem_solution&cr=147102&rd=3011&pm=99
public class Barricades {

    int mincost;  
    int from[], to[], cost[];
    int nnodes, nedges;
    boolean adj[][];
   
    boolean canpass() {
      boolean d[][] = new boolean[nnodes][nnodes];
      int a, b, c;
   
      for(a = 0; a < nnodes; a++)
        for(b = 0; b < nnodes; b++)
          d[a][b] = adj[a][b];
   
      for(b = 0; b < nnodes; b++)
        for(a = 0; a < nnodes; a++)
          for(c = 0; c < nnodes; c++)
            if(d[a][b] && d[b][c])
              d[a][c] = true;
      return d[0][nnodes - 1];
    }
   
    void search(int i, int c) {
      if(i == nedges) {
        if(!canpass()) {
          if(c < mincost)  mincost = c;
        }
        return;
      }
      search(i + 1, c);  
   
      int a = from[i], b = to[i];
      adj[a][b] = adj[b][a] = false;
      search(i + 1, c + cost[i]);
      adj[a][b] = adj[b][a] = true;
      
    }
   
    public int minBarricadeWidth(int nnodes, String[] paths) {
      System.out.println(paths.length);
      mincost = 100000000;
      this.nnodes = nnodes;
      adj = new boolean[nnodes][nnodes];
      nedges = paths.length;
      from = new int[nedges];
      to = new int[nedges];
      cost = new int[nedges];
      for(int i = 0; i < nedges; i++) {
        String s = (String)paths[i];
        int pi = s.indexOf(" ");
        int pj = s.lastIndexOf(" ");
        from[i] = Integer.valueOf(s.substring(0, pi)).intValue();
        to[i] = Integer.valueOf(s.substring(pi + 1, pj)).intValue();
        cost[i] = Integer.valueOf(s.substring(pj + 1)).intValue();
        System.out.println(from[i] + " " + to[i]);
        adj[from[i]][to[i]] = adj[to[i]][from[i]] = true;
      }
      search(0, 0);
      return mincost;
    }
  
    
    public static void main(String[] args){
        int nNodes = 5;
        String[] str = {"0 1 5","0 2 2","0 3 3","1 2 1","2 3 1","2 4 10","3 4 2"};
        int result= new Barricades().minBarricadeWidth(nNodes, str)  ;
        System.out.println(result);
    }
}
