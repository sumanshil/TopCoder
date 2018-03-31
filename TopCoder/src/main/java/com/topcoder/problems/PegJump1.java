package com.topcoder.problems;

import java.util.ArrayList;

import javax.naming.directory.SearchControls;

public class PegJump1 {
    int moves[][] = { { 1,  2, -1, -1, -1, -1},
            { 3,  4,  2,  0, -1, -1},
            { 4,  5, -1, -1,  0,  1},
            { 6,  7,  4,  1, -1, -1},
            { 7,  8,  5,  2,  1,  3},
            { 8,  9, -1, -1,  2,  4},
            {-1, -1,  7,  3, -1, -1},
            {-1, -1,  8,  4,  3,  6},
            {-1, -1,  9,  5,  4,  7},
            {-1, -1, -1, -1,  5,  8} };

    boolean filled[] = new boolean[ 10 ];
    int best = 0;

//          0
//       /    \
//      1  -   2
//    /  \   /   \
//   3 -   4   -  5
// /  \  /  \   /  \
//6 -  7  -   8   - 9
    public int maxJump( ArrayList a){
        int nfilled = 0;
        for(int i=0; i<10 ;i++){
            filled[i] =false;
        }
        for(int i = 0 ;i < a.size(); i++){
            filled[((Integer)(a.get(i))).intValue()] = true;            
        }
        for(int i = 0 ; i < 10 ; i++){
            if (filled[i]){
                nfilled++;
            }
        }
        search(0, nfilled);
        return best;
    }
    
    public void search(int nmoves, int left){
        if (nmoves > best){
            best = nmoves;                
        }
        if (nmoves + (left - 1) < best)
            return;
        for(int i = 0 ; i < 10 ; i++){
            if (!filled[i])
                continue;
            for(int j = 0 ; j < 6 ; j++){
                int x = moves[i][j];
                if (x < 0 || !filled[x])
                    continue;
                int y = moves[x][j];
                if (y < 0 || filled[y])
                    continue;
                filled[i] = false; filled[x] = false; filled[y] = true;
                search(nmoves+1, left-1);
                filled[i] = true; filled[x] = true; filled[y] = false;
            }
        }
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(6);
        a.add(9);
        int result = new PegJump1().maxJump(a);
        System.out.println(result);
    }

}
