package com.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=63&rd=3003
public class PegJump {
    private static int j[][] = {{ -1,-1,-1,1,-1,2,-1,-1,-1,-1},
                         { -1,-1,-1,-1,-1,-1,3,-1,4,-1},
                         { -1,-1,-1,-1,-1,-1,-1,4,-1,5},
                         { 1,-1,-1,-1,-1,4,-1,-1,-1,-1},
                         { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                         { 2,-1,-1,4,-1,-1,-1,-1,-1,-1},
                         { -1,3,-1,-1,-1,-1,-1,-1,7,-1},
                         { -1,-1,4,-1,-1,-1,-1,-1,-1,8},
                         { -1,4,-1,-1,-1,-1,7,-1,-1,-1},
                         { -1,-1,5,-1,-1,-1,-1,8,-1,-1}};

    public static int maxJump(ArrayList a) {
        int p[] = new int[10];
        int best = 0;
        for(int i=0;i<10;i++) p[i] = 0;
        for(int i=0;i<a.size();i++) p[((Integer)a.get(i)).intValue()] = 1;
        for(int s=0;s<10;s++) {
            // Check if there is an element at s position
            if(p[s] == 1) {
                for(int d=0;d<10;d++) {
                    // check if position d is empty
                    if(p[d] == 0) {
                        int e = j[s][d];
                        if(e != -1) {
                            // we can reach d from s as they are in the same direction
                            if(p[e] == 1) {
                                // there is a jumpee at e
                                 ArrayList aa = new ArrayList();
                                 for(int i=0;i<10;i++) {
                                   if(i != e) {
                                     if(i != s) {
                                       if(p[i] == 1) aa.add(new Integer(i));
                                     }
                                   }
                                 }
                                 aa.add(new Integer(d));
                                 int cur = maxJump(aa)+1;
                                 if(cur > best) best = cur;
                            }
                        }
                    }
                }
            }
        }
        return best;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //{1, 4, 9, 5, 9, 0}
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(9);
        list.add(0);
        PegJump.maxJump(list);

    }

}
