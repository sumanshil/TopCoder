package com.topcoder.problems.Invitational2001.finalcomp;
//http://community.topcoder.com/stat?c=problem_statement&pm=209&rd=57
import java.util.StringTokenizer;

public class Networking {
	 
	int[][] numconns;
	String[] cs;
	int[] frm;
	int[] to;
	double[] prob;
	int numnodes;
	 
	public double getProbability(int nodes, String[] conns) {
	  numnodes = nodes;
	  numconns = new int[nodes][nodes];
	  cs = conns;
	 
	  frm = new int[conns.length];
	  to = new int[conns.length];
	  prob = new double[conns.length];
	  for (int i=0; i<conns.length; i++) {
	    StringTokenizer st = new StringTokenizer(conns[i]);
	    frm[i] = Integer.parseInt(st.nextToken());
	    to[i] = Integer.parseInt(st.nextToken());
	    prob[i] = Double.parseDouble(st.nextToken());
	  }
	 
	  return rec(0);
	 
	}
	 
	double rec(int lev) {
	  if (lev == cs.length) {
	    boolean[] touched = new boolean[numnodes];
	    touched[0]=true;
	    boolean done = false;
	    while (!done) {
	      done = true;
	      for (int i=0; i<numnodes; i++) 
	    	if (touched[i]) 
	    	    for (int j=0; j<numnodes; j++)
	    	    	if (!touched[j]) {
		                if (numconns[i][j]>0) 
		                {
		                    touched[j]=true;
		                    done = false;
		                    if (j==numnodes-1) 
		                    {
		                       return 1.0;
		                    }
		                }
	            }
	        }
	    return 0.0;
	  } else {
	    double cprob=(1-prob[lev])*rec(lev+1);
	 
	    numconns[frm[lev]][to[lev]]++;
	    numconns[to[lev]][frm[lev]]++;
	 
	    cprob+=prob[lev]*rec(lev+1);
	 
	    numconns[frm[lev]][to[lev]]--;
	    numconns[to[lev]][frm[lev]]--;
	 
	    return cprob;
	  }
	}

	double rec1(int lev) {
		  if (lev == cs.length) {
		    boolean[] touched = new boolean[numnodes];
		    touched[0]=true;
		    boolean done = false;
		    //while (!done) {
		      done = true;
		      for (int i=0; i<numnodes; i++) 
		    	if (touched[i]) 
		    	    for (int j=0; j<numnodes; j++)
		    	    	if (!touched[j]) {
			                if (numconns[i][j]>0) 
			                {
			                    touched[j]=true;
			                    done = false;
			                    if (j==numnodes-1) 
			                    {
			                       return 1.0;
			                    }
			                }
		            }
		    //    }
		    return 0.0;
		  } else {
		    double cprob=(1-prob[lev])*rec1(lev+1);
		 
		    numconns[frm[lev]][to[lev]]++;
		    numconns[to[lev]][frm[lev]]++;
		 
		    cprob+=prob[lev]*rec1(lev+1);
		 
		    numconns[frm[lev]][to[lev]]--;
		    numconns[to[lev]][frm[lev]]--;
		 
		    return cprob;
		  }
		}
		 

	public static void main(String[] args)
	{
		double result = new Networking().getProbability(4, new String[]{"0 1 0.10", "1 3 0.20", "0 2 0.30", "2 3 0.40", "1 2 0.60"});
		System.out.println(result);
	}	 	 
	 
	}