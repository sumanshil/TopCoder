package com.topcoder.problems.round153;

import java.util.*;
public class PickTeamTC {
  int cmp[][];
  String names[];
  String bestTeam[];
  int bestSum = Integer.MIN_VALUE;
  int ct[];
  
  public String[] pickPeople(int teamSize, String[] people)
  {
    cmp = new int[people.length][people.length];
    names = new String[people.length];
    ct = new int[teamSize];
    Arrays.fill(ct, -1);
    
    bestTeam = new String[teamSize];
    bestSum = Integer.MIN_VALUE;
    
    for (int i = 0; i < people.length; i++){
      StringTokenizer st = new StringTokenizer(people[i], " ");
      names[i] = st.nextToken();
      
      for (int i2 = 0; st.hasMoreTokens(); i2++){
        cmp[i][i2] = Integer.parseInt(st.nextToken());
      }
    }
    //"ALICE 0  1 -1  3",
    //"BOB   1  0  2 -4",
    //"CAROL-1  2  0  2",
    //"DAVID 3 -4  2  0"
    
    createTeam(0, 0, teamSize);
    Arrays.sort(bestTeam);
    
    return bestTeam;
  }
  
  void createTeam(int ctInc, int mp, int ts){
    if (ctInc == ts){
      //for (int i = 0; i < ct.length; i++){System.out.print("" + ct[i] + " ");}
      //System.out.print("\n");
      int tempSum = 0;
      
      for (int i = 0; i < ts; i++){
        for (int i2 = i + 1; i2 < ts; i2++){
          tempSum += cmp[ct[i]][ct[i2]];
        }
      }
      //System.out.println("" + tempSum + " vs " + bestSum);
      if (tempSum > bestSum){
        //System.out.println("YEAH");
        for (int i = 0; i < ct.length; i++){
          bestTeam[i] = names[ct[i]];
        }
        
        bestSum = tempSum;
      }
      
      return;
    }
    
    for (int i = mp; i < cmp.length; i++){
      boolean used = false;
 
    //  if (!used){
        ct[ctInc] = i;
        createTeam(ctInc + 1, i + 1, ts);
        ct[ctInc] = -1;
    //  }
    }
  }
  
	public static void main(String[] args) 
	{
		String[] arr = {"ALICE 0 1 -1 3",
				 "BOB 1 0 2 -4",
				 "CAROL -1 2 0 2",
				 "DAVID 3 -4 2 0"};
		
//		String[] arr = {"ALICE 0 1 -1 3",
//				 "BOB 1 0 2 -4",
//				 "CAROL -1 2 0 2",
//				 "DAVID 3 -4 2 0"};

//		String[] arr = {"A 0 -2 -2","B -2 0 -1","C -2 -1 0"};
//		String[] arr = {"A 0 2 8 7 1 -4 -3 1 8 2 8 2 1 -3 7 1 3 9 -6 -5",
//				 "A 2 0 0 7 -7 -5 8 -8 -9 -9 6 -9 -4 -8 8 1 7 0 3 3",
//				 "A 8 0 0 -5 -5 -7 1 -3 1 9 -6 6 1 5 6 -9 8 6 -8 -8",
//				 "A 7 7 -5 0 7 -5 3 9 8 3 -6 -5 -5 2 -6 2 -2 -1 -2 8",
//				 "B 1 -7 -5 7 0 7 -2 -9 3 7 0 -2 1 1 -9 -3 -2 2 1 -2",
//				 "B -4 -5 -7 -5 7 0 4 8 6 0 -1 4 1 -2 -9 4 0 -7 6 -2",
//				 "B -3 8 1 3 -2 4 0 8 -1 1 -2 -7 5 0 -6 9 0 -3 1 3",
//				 "B 1 -8 -3 9 -9 8 8 0 0 -2 5 0 5 8 3 5 2 4 5 4",
//				 "C 8 -9 1 8 3 6 -1 0 0 8 -3 8 -6 -4 7 -4 1 -5 5 4",
//				 "C 2 -9 9 3 7 0 1 -2 8 0 -9 -6 6 5 -8 -3 -8 2 2 4",
//				 "C 8 6 -6 -6 0 -1 -2 5 -3 -9 0 2 7 8 2 -6 -4 -6 4 4",
//				 "C 2 -9 6 -5 -2 4 -7 0 8 -6 2 0 0 -3 6 7 -1 2 -4 -2",
//				 "D 1 -4 1 -5 1 1 5 5 -6 6 7 0 0 -7 -4 8 -6 -3 4 -6",
//				 "D -3 -8 5 2 1 -2 0 8 -4 5 8 -3 -7 0 7 -3 5 -8 5 1",
//				 "D 7 8 6 -6 -9 -9 -6 3 7 -8 2 6 -4 7 0 9 -5 -5 -8 3",
//				 "D 1 1 -9 2 -3 4 9 5 -4 -3 -6 7 8 -3 9 0 -2 -7 8 -7",
//				 "E 3 7 8 -2 -2 0 0 2 1 -8 -4 -1 -6 5 -5 -2 0 6 0 5",
//				 "E 9 0 6 -1 2 -7 -3 4 -5 2 -6 2 -3 -8 -5 -7 6 0 4 8",
//				 "E -6 3 -8 -2 1 6 1 5 5 2 4 -4 4 5 -8 8 0 4 0 1",
//				 "E -5 3 -8 8 -2 -2 3 4 4 4 4 -2 -6 1 3 -7 5 8 1 0"};
		//String[] arr =  {"YAX 0 -272 98 -974 -548", "VHT -272 0 -294 -844 783", "CGNA 98 -294 0 131 -740", "UYTPP -974 -844 131 0 -125", "GVTWWJ -548 783 -740 -125 0"};
		//String[] arr =  {"GTY 0 -310 619 719 415", "EHVEV -310 0 -148 438 -858", "TXOEJJ 619 -148 0 640 -999", "AN 719 438 640 0 -222", "KPPYQ 415 -858 -999 -222 0"};
		//String[] arr =  {"UV 0 -489 -444 660", "RWJ -489 0 119 -967", "P -444 119 0 142", "YRWPWT 660 -967 142 0"};
		//String[] arr = {"YAX 0 -272 98 -974 -548", "VHT -272 0 -294 -844 783", "CGNA 98 -294 0 131 -740", "UYTPP -974 -844 131 0 -125", "GVTWWJ -548 783 -740 -125 0"};
		//String[] arr = {"D 0 99 106 952", "PU 99 0 696 -133", "HUCY 106 696 0 771", "KXBB 952 -133 771 0"}	;
		//String[] arr =  {"VKTPK 0 656 198 -822 -144 -224 -242", "WXHFXU 656 0 -436 -950 51 794 -729", "VGH 198 -436 0 988 204 287 266", "LUK -822 -950 988 0 -547 -11 -518", "QDQ -144 51 204 -547 0 312 804", "WKXF -224 794 287 -11 312 0 -580", "RBT -242 -729 266 -518 804 -580 0"};
		//String[] arr = {"JN 0 272 -200 971 -210 331 492", "V 272 0 -654 683 -548 -564 413", "MPJFIC -200 -654 0 538 -101 -128 -228", "RXMF 971 683 538 0 897 915 298", "FPX -210 -548 -101 897 0 -974 882", "JZ 331 -564 -128 915 -974 0 -625", "PF 492 413 -228 298 882 -625 0"};
		//String[] arr = {"RPV 0 -219 -908 115 694 -632 236 2 -905", "AFKZJ -219 0 399 498 995 -288 156 -57 504", "ZJYFQ -908 399 0 728 -311 753 218 327 -951", "PY 115 498 728 0 893 233 -219 -368 307", "EOSCH 694 995 -311 893 0 -424 771 311 -817", "JEOQ -632 -288 753 233 -424 0 462 -268 491", "NM 236 156 218 -219 771 462 0 945 -77", "CIFYASF 2 -57 327 -368 311 -268 945 0 -243", "NML -905 504 -951 307 -817 491 -77 -243 0"};
		//String[] arr =  {"QOM 0 -3 5 3 10 3 8 6 0 -7 0 4 6 -6 7 5 14 11 4 14", "OC -3 0 2 3 1 12 7 0 0 10 14 3 1 7 -2 10 10 5 -4 3", "UT 5 2 0 12 5 8 4 5 -4 4 8 6 13 12 -3 4 -1 5 0 14", "ORNV 3 3 12 0 9 11 12 2 6 -3 5 -2 0 5 9 1 2 13 0 1", "WYM 10 1 5 9 0 2 -4 8 12 6 -7 8 2 8 2 0 3 7 -4 8", "OMJVC 3 12 8 11 2 0 6 -4 2 8 3 18 1 1 6 5 9 7 -2 4", "TXQB 8 7 4 12 -4 6 0 3 3 0 4 -5 1 4 4 10 4 -4 2 13", "P 6 0 5 2 8 -4 3 0 -3 -6 -1 0 16 14 1 12 4 6 -2 -7", "BL 0 0 -4 6 12 2 3 -3 0 2 15 -2 2 8 6 14 4 9 -1 9", "LUUGR -7 10 4 -3 6 8 0 -6 2 0 2 1 11 7 2 1 7 3 2 3", "GI 0 14 8 5 -7 3 4 -1 15 2 0 5 2 8 1 9 7 9 11 4", "BH 4 3 6 -2 8 18 -5 0 -2 1 5 0 -8 6 7 0 -8 9 -7 6", "SPS 6 1 13 0 2 1 1 16 2 11 2 -8 0 7 5 8 9 12 9 6", "ZEMG -6 7 12 5 8 1 4 14 8 7 8 6 7 0 0 3 17 3 7 9", "ESU 7 -2 -3 9 2 6 4 1 6 2 1 7 5 0 0 4 5 15 13 5", "RZTW 5 10 4 1 0 5 10 12 14 1 9 0 8 3 4 0 4 0 16 1", "BMJ 14 10 -1 2 3 9 4 4 4 7 7 -8 9 17 5 4 0 -3 1 11", "JG 11 5 5 13 7 7 -4 6 9 3 9 9 12 3 15 0 -3 0 1 9", "R 4 -4 0 0 -4 -2 2 -2 -1 2 11 -7 9 7 13 16 1 1 0 8", "FQF 14 3 14 1 8 4 13 -7 9 3 4 6 6 9 5 1 11 9 8 0"}	;
	    String[] result = new PickTeamTC().pickPeople(3, arr);
	    for(String res : result)
	    {
	    	System.out.println(res);
	    }

	}

  
}