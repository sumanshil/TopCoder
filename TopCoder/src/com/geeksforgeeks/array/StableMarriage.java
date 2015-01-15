package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/stable-marriage-problem/
public class StableMarriage {

    public static int N =4;
    public static int[][] matrix ={
//            {2,3},
//            {2,3},
//            {0,1},
//            {0,1}
        {7, 5, 6, 4},
        {5, 4, 6, 7},
        {4, 5, 6, 7},
        {4, 5, 6, 7},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},        
    } ;
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] output = new int[2*N][1];
        boolean[] free = new boolean[2*N];
        //boolean[] freeMan = new boolean[N];
        for(int i = 0 ; i < 2*N ; i++){
            free[i] = true;
        }
        int freeCount = 2*N;
        while(freeCount>0){
            for(int man = 0 ; man < N ; man++){
                if (free[man]){
                    int[] womanPreferences = matrix[man];
                    // get all the candidates
                    
                    for(int woman = 0 ; woman < womanPreferences.length ; woman++){
                        int candidate = womanPreferences[woman];
                        if (free[candidate]){
                            output[candidate][0] = man;
                            free[man] = false;
                            free[candidate] = false;
                            freeCount= freeCount-2;
                            break;
                        } else {
                            int[] manArray= matrix[woman];
                            int selectedMan = output[candidate][0];
                            int selectedManIndex  = 0;
                            int candidateManIndex  = 0;
                            for(int i = 0 ; i < manArray.length ; i++){
                                if (manArray[i] == selectedMan)
                                    selectedManIndex = i;
                                else if (manArray[i] == man)
                                    candidateManIndex= i;
                            }
                            if (candidateManIndex < selectedManIndex){
                                output[candidate][0] = man;
                                free[man] = false;
                                free[selectedMan] = true;
                                //freeCount--;
                                break;  
                            }
                        }
                    }
                }
            } //end for (man
        }
        for(int i = N ; i < 2*N ; i++){
            System.out.println("Match for woman "+i+" is man "+output[i][0]);
        }
    }
    private static boolean allManEngaged(boolean[] freeMan) {
        for(boolean isEngaged : freeMan){
            if (!isEngaged)
                return isEngaged;
        }
        return true;
    }
}
