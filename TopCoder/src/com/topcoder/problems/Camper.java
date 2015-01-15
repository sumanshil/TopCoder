package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_solution&cr=105922&rd=2009&pm=72
public class Camper {
    private static int [][] data = 
      { { 10, 100, 170, 40, 68, 92, 220, 30, 60, 85, 92, 109, 230, 60, 65, 72, 80, 82, 120, 130, 180, 222, 400, 800 }, //calories
        {  5,  20,  10, 12, 20, 40,  70, 30, 40, 30, 30,  60, 100, 51, 52, 53, 54, 70,  20,  40,  40, 90,  200, 600 }, //weight
        {  3,  20,  90, 90, 80, 12,  50,  1, 70, 20, 20,  30, 120, 40, 40, 40, 20, 80,  90, 100,  60, 30,  200,  50 } }; //volume
    
    
    public int maxCalories(int maxw, int maxv){
        boolean taken[] = new boolean[30];
        //return mch1(maxw, maxv,23);
        return mch(0, maxw, maxv);
    }
    private int mch(int at, int maxw, int maxv) {
        if (at >= 24) {
            return 0;
        }
       
        int best = mch(at+1, maxw , maxv);
        
        if (data[1][at]<=maxw && data[2][at] <=maxv){
            int result = mch(at+1,maxw-data[1][at], maxv-data[2][at])+data[0][at];
            if (result> best)
                best = result;
        }
        return best;
    }
    
    private int mch1(int maxw, int maxv, int n){
        if (n <=0 || maxv <0 || maxw <0)
            return 0;
        
        if (data[1][n]>maxw || data[2][n] >maxv){
           return  mch1(maxw, maxv, n-1);
        } else {
            int with = data[0][n]+ mch1(maxw-data[1][n], maxv-data[2][n], n-1);
            int without =  mch1(maxw, maxv, n-1);
            return Math.max(with, without);            
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = new Camper().maxCalories(100, 100);
        System.out.println(result);

    }

}
