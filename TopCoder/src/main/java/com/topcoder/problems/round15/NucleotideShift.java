package com.topcoder.problems.round15;
//http://community.topcoder.com/stat?c=problem_statement&pm=111&rd=3014&rm=&cr=114443
public class NucleotideShift
{
  private final String nucs = "TCAG";
  private final String ugc = "FFLLSSSSYY..CC.WLLLLPPPPHHQQRRRRIIIMTTTTNNKKSSRRVVVVAAAADDEEGGGG";
  private final String ghgh = "TCGA";
  public int cost(char A, char B) { // cost from A to B
     return (ghgh.indexOf(B) + 4 - ghgh.indexOf(A)) % 4;
//     return (nucs.indexOf(B) + 4 - nucs.indexOf(A)) % 4;
  }
  public int nuc(char c) { 
    return nucs.indexOf(c);
  }
  public char acid(String s) {
    return    ugc.charAt((nuc(s.charAt(0)) * 4 + nuc(s.charAt(1))) * 4 + nuc(s.charAt(2)));
  }
  public int mmin(int a, int b) {
    if (a==-1) return b;
    if (b==-1) return a;
    return Math.min(a,b);
  }
  public int dist(String codon, char goal) {
    System.out.println(codon + " " +acid(codon)+ " " + goal);
    if (acid(codon) == goal) return 0;
    int i, best = -1;
    for (i=0;i<64;i++) if (ugc.charAt(i) == goal) {
        int a1 = cost(codon.charAt(0), nucs.charAt(i / 16));
        int a2 = cost(codon.charAt(1), nucs.charAt((i % 16) / 4));
        int a3 = cost(codon.charAt(2), nucs.charAt(i % 4));
      //best = mmin(best, cost(codon.charAt(0), nucs.charAt(i / 16)) +
      //                  cost(codon.charAt(1), nucs.charAt((i % 16) / 4)) +
        best = mmin(best, (a1+a2+a3));
      //cost(codon.charAt(2), nucs.charAt(i % 4)));
    }
    System.out.println(best);
    return best;
  }
  public int score(String DNA, String AA) {
    if (DNA.length()==0 && AA.length()==0) return 0;
    
    int x = dist(DNA.substring(0,3), AA.charAt(0));
    return x + score(DNA.substring(3), AA.substring(1));
  }
  public int findMinimumShifts(String s, String goal) {
    if (goal.length() * 3 > s.length()) return -1;
    int best = -1;
    int i;
    for (i=0;i<=s.length() - goal.length()*3;i++) {
       String t = s.substring(i, goal.length()*3 + i); 
       System.out.println(t);
       int g = score(t, goal);
       System.out.println(g);
       best = mmin(best, g);
    }
    return best;
  }
  
  public static void main(String[] args){
      int result = new NucleotideShift().findMinimumShifts("TAGCATGAGCATTTTCCCGGGAAA", "WESTERN");
      System.out.println(result);
  }
}
